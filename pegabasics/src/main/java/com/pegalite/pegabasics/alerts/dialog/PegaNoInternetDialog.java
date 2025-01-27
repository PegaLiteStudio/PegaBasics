package com.pegalite.pegabasics.alerts.dialog;


import android.app.Activity;

import com.pegalite.pegabasics.alerts.utils.DialogData;
import com.pegalite.pegabasics.alerts.utils.PegaFatherDialog;
import com.pegalite.pegabasics.databinding.NoInternetDialogBinding;

public class PegaNoInternetDialog extends PegaFatherDialog {

    public PegaNoInternetDialog(Activity context, DialogData data) {
        super(context, data);
    }

    public void show() {
        NoInternetDialogBinding binding = NoInternetDialogBinding.inflate(getActivityContext().getLayoutInflater());
        setContentView(binding.getRoot());

        showPegaDialog();
    }
}
