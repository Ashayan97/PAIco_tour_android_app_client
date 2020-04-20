package com.paico.paico_tour;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ConfrmationCodeDialogBox extends Dialog {
    private CountDownTimer countDownTimer;
    private EditText confirmationCode;
    private Button confirmBtn;
    private TextView resendBtn;
    private TextView counterView;
    private Finisher finisher;

    public ConfrmationCodeDialogBox(@NonNull Context context, Finisher finisher) {

        super(context);
        this.finisher = finisher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_confirm);
        findView();
        onClick();
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counterView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                resendBtn.setClickable(true);
                resendBtn.setTextColor(getContext().getResources().getColor(R.color.blue));
            }
        };
        countDownTimer.start();

    }

    private void onClick() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                getContext().startActivity(new Intent(getContext(), MapsActivity.class));
                finisher.finishActivity();
            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Log.d("check", "clicked");
                countDownTimer.start();
                resendBtn.setClickable(false);
                resendBtn.setTextColor(getContext().getResources().getColor(R.color.black));
            }
        });
    }

    private void findView() {

        confirmationCode = (EditText) findViewById(R.id.confirm_code_edit_text);
        confirmBtn = (Button) findViewById(R.id.confirm_code_btn);
        resendBtn = (TextView) findViewById(R.id.resend_code_text_btn);
        counterView = (TextView) findViewById(R.id.resend_timer);

    }

}
