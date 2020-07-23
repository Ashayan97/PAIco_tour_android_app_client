package com.paico.paico_tour;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText email;
    private EditText phoneNumber;
    private Button confirmBtn;
    private Finisher finisher;
    private FirebaseAuth mAuth;


    public SignupFragment(Finisher finisher) {
        this.finisher = finisher;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_up, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        mAuth = FirebaseAuth.getInstance();
        onClick();
    }

    private void onClick() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNotNull(password, "password") && checkNotNull(firstName, "first name") && checkNotNull(lastName, "last name") &&
                        checkNotNull(email, "email") &&
                        checkNotNull(phoneNumber, "phone number")) {
                    String sEmail = email.getText().toString();
                    String sPassword = password.getText().toString();
                    String sPhoneNumber= phoneNumber.getText().toString();
                    final String sFirstName=firstName.getText().toString();
                    String sLastName=lastName.getText().toString();
                    mAuth.createUserWithEmailAndPassword(sEmail, sPassword).
                            addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("userCreation", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(sFirstName)
                                                .build();

                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {

                                                        }
                                                    }
                                                });
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("userCreation", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    ConfrmationCodeDialogBox confrmationCodeDialogBox = new ConfrmationCodeDialogBox(getActivity(),finisher);
                    confrmationCodeDialogBox.show();

                }
            }
        });
    }

    private boolean checkNotNull(EditText editText, String str) {
        if (editText.getText().toString() .equals("")) {
            Toast.makeText(getActivity(), "You must enter your " + str, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void findView(View view) {
        firstName = view.findViewById(R.id.sign_up_first_name);
        lastName = view.findViewById(R.id.sign_up_last_name);
        password = view.findViewById(R.id.sign_up_password);
        email = view.findViewById(R.id.sign_up_email);
        phoneNumber = view.findViewById(R.id.sign_up_phone_number);
        confirmBtn = view.findViewById(R.id.sign_up_confirm_btn);
    }
}
