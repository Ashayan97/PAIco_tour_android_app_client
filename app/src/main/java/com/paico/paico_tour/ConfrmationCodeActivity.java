package com.paico.paico_tour;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConfrmationCodeActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private EditText confirmationCode;
    private Button confirmBtn;
    private TextView resendBtn;
    private TextView counterView;
    private String phoneNumber;
    private String codeSent;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_code_activity);
        phoneNumber = getIntent().getStringExtra("phone");
        mAuth = FirebaseAuth.getInstance();
        findView();
        onClick();
        verifyPhoneNumber();

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counterView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                resendBtn.setClickable(true);
                resendBtn.setTextColor(ConfrmationCodeActivity.this.getResources().getColor(R.color.blue));
            }
        };
        countDownTimer.start();

    }

    private void verifyPhoneNumber() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
                signInWithPhoneAuthCredential(credential);

            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(ConfrmationCodeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
            Log.d("phone_info", s);
        }
    };


    private void onClick() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = confirmationCode.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
                signInWithPhoneAuthCredential(credential);
            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyPhoneNumber();

                Log.d("check", "clicked");
                countDownTimer.start();
                resendBtn.setClickable(false);
                resendBtn.setTextColor(ConfrmationCodeActivity.this.getResources().getColor(R.color.black));
            }
        });
    }

    private void findView() {

        confirmationCode = (EditText) findViewById(R.id.confirm_code_edit_text);
        confirmBtn = (Button) findViewById(R.id.confirm_code_btn);
        resendBtn = (TextView) findViewById(R.id.resend_code_text_btn);
        counterView = (TextView) findViewById(R.id.resend_timer);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(ConfrmationCodeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            setBasicData(user);

                        } else {
                            Log.w("fail", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(ConfrmationCodeActivity.this, "Invalid code", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void setBasicData(final FirebaseUser user) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("User");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(user.getUid())) {
                        User userData = new User();
                        userData.setPhoneNumber(user.getPhoneNumber());
                    UserHolder.getInstance().setUser(userData);
                    FirebaseDatabase.getInstance().getReference("User").
                                child(user.getUid()).setValue(userData);
                    startActivity(new Intent(ConfrmationCodeActivity.this, DrawerActivity.class));
                    finish();
                } else {
                    User userData = dataSnapshot.child(user.getUid()).getValue(User.class);
                    UserHolder.getInstance().setUser(userData);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d("user_info", databaseError.getMessage());
            }
        });


    }

}
