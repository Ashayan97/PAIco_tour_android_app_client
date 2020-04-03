package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class OpinionDialogBox extends Dialog {
    private Opinion opinion;
    public OpinionDialogBox(@NonNull Context context,Opinion opinion) {
        super(context);
        this.opinion=opinion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion_dialog_box);
    }
}
