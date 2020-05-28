package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ChargeFragment extends Fragment {
    private EditText addMoneyAmount;
    private TextView balanceAmount;
    private Button chargeButton;
    private RecyclerView chargeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.charge_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        onClick();
        setupView();
    }

    private void setupView() {
        //ToDo setup view information
    }

    private void onClick() {
        chargeRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add charging api
            }
        });
    }

    private void findView(View view) {
        addMoneyAmount = view.findViewById(R.id.charge_fragment_add_money);
        balanceAmount = view.findViewById(R.id.charge_fragment_amount_of_money);
        chargeButton = view.findViewById(R.id.charge_fragment_button);
        chargeRecyclerView = view.findViewById(R.id.charge_fragment_recycler_view);
    }
}
