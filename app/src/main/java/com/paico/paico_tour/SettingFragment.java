package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {
    private EditText fullName;
    private EditText Password;
    private EditText phone;
    private EditText Date;
    private EditText Email;

    private Button submit;
    private Button edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClick(view);

    }

    private void onClick(View view) {
//        fullName=view.findViewById();
    }
}
