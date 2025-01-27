package com.pegalite.pegabasics.viewmanagers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.pegalite.pegabasics.R;
import com.pegalite.pegabasics.alerts.dialog.PegaNoInternetDialog;
import com.pegalite.pegabasics.alerts.utils.DialogData;
import com.pegalite.pegabasics.alerts.utils.PegaFatherDialog;
import com.pegalite.pegabasics.helpers.ConnectivityReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sahil The Geek;
 * Date : 02-04-2024.
 *
 * <p>This {@link  PegaAppCompatActivity} adds extra functionality in the activity</p>
 */
public class PegaAppCompatActivity extends AppCompatActivity {

    private static int MAIN_COLOR = R.color.azure, SECOND_COLOR = R.color.white;
    private final List<Dialog> dialogList = new ArrayList<>();
    private final List<PegaFatherDialog> pegaDialogList = new ArrayList<>();
    public boolean isInternetAvailable = true, receiverRegistered = false;
    private boolean isLastActivity = false, backWithRightAnim = false;
    OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            onPegaBackPressed();
        }
    };
    private AlertDialog maintenanceAlert;
    private ConnectivityReceiver connectivityReceiver;
    private BroadcastReceiver maintenanceReceiver;
    private PegaNoInternetDialog pegaNoInternetDialog;

    public static void setMainColor(int mainColor) {
        MAIN_COLOR = mainColor;
    }

    public static void setSecondColor(int secondColor) {
        SECOND_COLOR = secondColor;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connectivityReceiver = new ConnectivityReceiver(isConnected -> {

            onConnectivityChanged(isConnected);
            isInternetAvailable = isConnected;

            if (!isConnected) {
                pegaNoInternetDialog = new PegaNoInternetDialog(this, DialogData.UN_CANCELABLE);
                pegaNoInternetDialog.show();
                PegaAnimationManager.showToast(this, "No Internet Connection");
                addDialogToDestroyList(pegaNoInternetDialog);
            } else {
                if (pegaNoInternetDialog != null && pegaNoInternetDialog.isShowing()) {
                    removeDialogFromDestroyList(pegaNoInternetDialog);
                    pegaNoInternetDialog.dismiss();
                }
            }
        });

        maintenanceReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isMaintenance = intent.getBooleanExtra("isMaintenance", false);
                String maintenanceMessage = intent.getStringExtra("maintenanceMessage");
                if (isMaintenance) {
                    showMaintenanceAlert(maintenanceMessage);
                    return;
                }
                hideMaintenanceAlert();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    private void hideMaintenanceAlert() {
        if (maintenanceAlert != null && maintenanceAlert.isShowing()) {
            maintenanceAlert.dismiss();
            maintenanceAlert = null;
        }
    }


    private void showMaintenanceAlert(String msg) {
        if (maintenanceAlert != null) {
            return;
        }

        maintenanceAlert = new AlertDialog.Builder(this, R.style.alertDialog).setTitle("Maintenance Break!").setMessage(msg).setPositiveButton("OK", (dialog, which) -> finishAffinity()).setCancelable(false).show();
    }

    public Activity getActivity() {
        return this;
    }

    public Window getDefaultWindow() {
        /* For Window Color Adjustments */
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        return window;
    }

    public void setWindowThemeMain() {
        setWindowColors(MAIN_COLOR);
    }

    public void setWindowThemeSecond() {
        setWindowColors(SECOND_COLOR);
    }

    public void setWindowThemeThird() {
        /* For Window Color Adjustments */
        Window window = getDefaultWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, MAIN_COLOR));
        window.setNavigationBarColor(ContextCompat.getColor(this, SECOND_COLOR));
    }

    private void setWindowColors(int color) {
        Window window = getDefaultWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, color));
        window.setNavigationBarColor(ContextCompat.getColor(this, color));
        manageStatusBarColor(window, color);
    }

    private void manageStatusBarColor(Window window, int color) {
        if (checkIfDarkFamily(color) && color != R.color.white) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public boolean checkIfDarkFamily(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        double brightness = (0.299 * red + 0.587 * green + 0.114 * blue) / 255;
        double threshold = 0.5;
        return brightness < threshold;
    }

    public void setBackWithRightAnim() {
        backWithRightAnim = true;
    }

    public boolean isInternetAvailable() {
        return isInternetAvailable;
    }

    public void onConnectivityChanged(boolean isConnected) {
    }

    public void addDialogToDestroyList(Dialog dialog) {
        dialogList.add(dialog);
    }

    public void addDialogToDestroyList(PegaFatherDialog dialog) {
        pegaDialogList.add(dialog);
    }

    public void removeDialogFromDestroyList(Dialog dialog) {
        dialogList.remove(dialog);
    }

    public void removeDialogFromDestroyList(PegaFatherDialog dialog) {
        pegaDialogList.remove(dialog);
    }

    private void registerReceiver() {
        if (!receiverRegistered) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(connectivityReceiver, intentFilter);

            IntentFilter filter = new IntentFilter("com.tiger.team.khanfire.MAINTENANCE");
            LocalBroadcastManager.getInstance(this).registerReceiver(maintenanceReceiver, filter);

            receiverRegistered = true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiverRegistered) {
            unregisterReceiver(connectivityReceiver);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(maintenanceReceiver);
            receiverRegistered = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        if (pegaNoInternetDialog != null && pegaNoInternetDialog.isShowing()) {
            pegaNoInternetDialog.dismiss();
        }
        if (receiverRegistered) {
            unregisterReceiver(connectivityReceiver);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(maintenanceReceiver);
            receiverRegistered = false;
        }
        for (int i = 0; i < dialogList.size(); i++) {
            Dialog dialog = dialogList.get(i);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        for (int i = 0; i < pegaDialogList.size(); i++) {
            PegaFatherDialog dialog = pegaDialogList.get(i);
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        super.onDestroy();
    }

    /**
     * When the Activity will be set as last activity if user press back button the app will ask confirmation to exit
     */
    public void setAsLastActivity() {
        isLastActivity = true;
    }

    public void unsetAsLastActivity() {
        isLastActivity = false;
    }

    public void finishAfterPegaTransition() {
        new Handler().postDelayed(this::finish, 1000);
    }


    public void openActivityWithRightAnim(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openResultActivityWithRightAnim(ActivityResultLauncher<Intent> activityResultLauncher, Class<?> cls) {
        openResultActivityWithRightAnim(activityResultLauncher, new Intent(this, cls));
    }

    public void openResultActivityWithRightAnim(ActivityResultLauncher<Intent> activityResultLauncher, Intent intent) {
        activityResultLauncher.launch(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openActivityWithRightAnim(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openActivityWithLeftAnim(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void onPegaBackPressed() {
        if (isLastActivity) {
            new AlertDialog.Builder(getActivity(), R.style.alertDialog).setTitle("Are you sure?").setMessage("Do you really want to exit").setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).setPositiveButton("Exit", (dialog, which) -> {
                dialog.dismiss();
                finish();
            }).show();
            return;
        }
        finishAfterTransition();
        if (backWithRightAnim) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    @Override
    protected void onStop() {
        new Instrumentation().callActivityOnSaveInstanceState(this, new Bundle());
        super.onStop();
    }
}
