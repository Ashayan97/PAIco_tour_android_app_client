package com.paico.paico_tour;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninFragment extends Fragment {

    private EditText email;
    private EditText password;
    private ImageButton submit;
    private FirebaseAuth mAuth;

    private Finisher finisher;

    public SigninFragment(Finisher finisher) {
        this.finisher = finisher;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_in, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        mAuth = FirebaseAuth.getInstance();
        onClick();
    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();

            }
        });
    }

    private void startSignIn() {

        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        if (TextUtils.isEmpty(sEmail) || TextUtils.isEmpty(sPassword)) {
            Toast.makeText(getContext(), "You need To Fill both Fields", Toast.LENGTH_LONG).show();

        } else {
            mAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getContext(), "LogIn Problem!", Toast.LENGTH_LONG).show();
                    }else {
                        startActivity(new Intent(getContext(), DrawerActivity.class));
                        finisher.finishActivity();
                    }
                }
            });
        }

    }

    private void findView(View view) {
        email = view.findViewById(R.id.sign_in_email);
        password = view.findViewById(R.id.sign_in_password);
        submit = view.findViewById(R.id.sign_in_btn);
    }
}
