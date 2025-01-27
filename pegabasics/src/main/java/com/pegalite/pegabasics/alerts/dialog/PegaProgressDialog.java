package com.pegalite.pegabasics.alerts.dialog;


import android.app.Activity;

import androidx.annotation.Nullable;

import com.pegalite.pegabasics.alerts.utils.DialogData;
import com.pegalite.pegabasics.alerts.utils.PegaFatherDialog;
import com.pegalite.pegabasics.databinding.ProgressDialogBinding;

public class PegaProgressDialog extends PegaFatherDialog {
    public PegaProgressDialog(Activity context, DialogData data) {
        super(context, data);
    }

    public void show() {
        show(null);
    }

    public void show(@Nullable String message) {
        ProgressDialogBinding binding = ProgressDialogBinding.inflate(getActivityContext().getLayoutInflater());
        setContentView(binding.getRoot());

        if (message != null) {
            binding.msg.setText(message);
        }

        showPegaDialog();
    }

}
