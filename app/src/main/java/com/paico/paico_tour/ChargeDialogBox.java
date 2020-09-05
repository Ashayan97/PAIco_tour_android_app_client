package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
        balanceAmount.setText(UserHolder.getInstance().getUser().getBalance());

        chargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addMoneyAmount.getText() != null && !addMoneyAmount.getText().toString().equals("")) {
                    String newBalance = String.valueOf(Integer.valueOf(UserHolder.getInstance().getUser().getBalance()) + Integer.valueOf(addMoneyAmount.getText().toString()));
                    balanceAmount.setText(newBalance);
                    User user = UserHolder.getInstance().getUser();
                    if (user.getTransactions() == null)
                        user.setTransactions(new ArrayList<Transactions>());
                    if (user.getCharges() == null)
                        user.setCharges(new ArrayList<Transactions>());
                    Transactions transaction = new Transactions();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                    transaction.setId(FirebaseAuth.getInstance().getUid().substring(0, 2) + timeStamp);
                    transaction.setAmount("+ " + addMoneyAmount.getText().toString());
                    String time= timeStamp.substring(0,4)+"/"+timeStamp.substring(4,6)+"/"+timeStamp.substring(6,8);
                    transaction.setDate(time);
                    transaction.setTitle("Charge");
                    user.getTransactions().add(transaction);
                    user.getCharges().add(transaction);
                    user.setBalance(newBalance);
                    FirebaseDatabase.getInstance().getReference("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).
                            setValue(user);
                }
            }
        });
    }

    private void findView() {
        addMoneyAmount = findViewById(R.id.charge_dialog_box_add_money);
        balanceAmount = findViewById(R.id.charge_dialog_box_amount_of_money);
        chargeButton = findViewById(R.id.charge_dialog_box_button);
    }

}
