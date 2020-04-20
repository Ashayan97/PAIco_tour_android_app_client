package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.paico.paico_tour.intefaces.Finisher;

public class SignupFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText phoneNumber;
    private EditText birthDay;
    private Button confirmBtn;
    private Finisher finisher;

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
        onClick();
    }

    private void onClick() {
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNotNull(username, "username") && checkNotNull(password, "password") && checkNotNull(firstName, "first name") && checkNotNull(lastName, "last name") &&
                        checkNotNull(email, "email") &&
                      //  checkNotNull(birthDay, "birth day") &&
                        checkNotNull(phoneNumber, "phone number")) {
                    //TODO

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
        username = view.findViewById(R.id.sign_up_username);
        password = view.findViewById(R.id.sign_up_password);
        email = view.findViewById(R.id.sign_up_email);
        phoneNumber = view.findViewById(R.id.sign_up_phone_number);
      //  birthDay = view.findViewById(R.id.sign_up_birth_day);
        confirmBtn = view.findViewById(R.id.sign_up_confirm_btn);
    }
}
