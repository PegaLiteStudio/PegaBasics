package com.pegalite.pegabasics.alerts.dialog;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;

import com.pegalite.pegabasics.alerts.utils.DialogData;
import com.pegalite.pegabasics.alerts.utils.PegaFatherDialog;
import com.pegalite.pegabasics.databinding.SuccessDialogBinding;

public class PegaSuccessDialog extends PegaFatherDialog {

    Activity activity;

    public PegaSuccessDialog(Activity context, DialogData data) {
        super(context, data);
        activity = context;
    }

    public void show(String successMsg) {
        SuccessDialogBinding binding = SuccessDialogBinding.inflate(getActivityContext().getLayoutInflater());
        setContentView(binding.getRoot());
        binding.successMsg.setText(successMsg);
        binding.lottieAnimation.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dismiss();
                activity.finish();
            }
        });
        showPegaDialog();
    }
}
