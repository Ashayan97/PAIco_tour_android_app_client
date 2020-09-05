package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

public class SettingFragment extends Fragment {
    private EditText fullName;
    private EditText password;
    private EditText phone;
    private EditText date;
    private EditText email;

    private Button submit;
    private Button edit;

    private ImageView gallery;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        setView();
        onClick();

    }

    private void setView() {
        User user = UserHolder.getInstance().getUser();
        if (user.getName().equals(null))
            fullName.setHint("No Name Exist");
        else
            fullName.setText(user.getName());
        phone.setText(user.getPhoneNumber());


    }

    private void onClick() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                edit.setClickable(true);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    private void findView(View view) {
        fullName = view.findViewById(R.id.setting_fragment_name);
        phone = view.findViewById(R.id.setting_fragment_phone);
        email = view.findViewById(R.id.setting_fragment_email);
        submit = view.findViewById(R.id.setting_fragment_submit_btn);
        edit = view.findViewById(R.id.setting_fragment_edit_btn);
        gallery = view.findViewById(R.id.setting_fragment_gallery);
    }
}
