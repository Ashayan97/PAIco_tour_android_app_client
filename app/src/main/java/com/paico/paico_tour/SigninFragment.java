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
    private EditText phone;
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
        String sPhone = phone.getText().toString();
        if (TextUtils.isEmpty(sPhone)) {
            Toast.makeText(getContext(), "You need To Fill Field", Toast.LENGTH_LONG).show();

        } else {
//            ConfrmationCodeDialogBox codeDialogBox = new ConfrmationCodeDialogBox(getActivity(),finisher,"+98"+sPhone,getActivity());
//            codeDialogBox.show();

        }

    }

    private void findView(View view) {
        phone = view.findViewById(R.id.sign_in_phone);
        submit = view.findViewById(R.id.sign_in_btn);
    }
}
