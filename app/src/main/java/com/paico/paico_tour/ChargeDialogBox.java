package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ChargeDialogBox extends Dialog {
    private EditText addMoneyAmount;
    private TextView balanceAmount;
    private Button chargeButton;

    public ChargeDialogBox(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.window_dialog_background);
        setContentView(R.layout.charge_dialog_box);
        findView();
        setView();

    }

    private void setView() {
        //TODO setup elements and basic needs
    }

    private void findView() {
        addMoneyAmount = findViewById(R.id.charge_dialog_box_add_money);
        balanceAmount = findViewById(R.id.charge_dialog_box_amount_of_money);
        chargeButton = findViewById(R.id.charge_dialog_box_button);
    }

}
