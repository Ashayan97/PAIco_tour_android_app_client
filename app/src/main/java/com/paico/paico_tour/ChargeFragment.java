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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.paico.paico_tour.object_classes.UserHolder;

import java.util.ArrayList;

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
        balanceAmount.setText(UserHolder.getInstance().getUser().getBalance());
    }

    private void onClick() {
        chargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addMoneyAmount.getText()!=null && !addMoneyAmount.getText().toString().equals("")){
                String newBalance=String.valueOf(Integer.valueOf(UserHolder.getInstance().getUser().getBalance())+Integer.valueOf(addMoneyAmount.getText().toString()));
                FirebaseDatabase.getInstance().getReference("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).
                        child("balance").setValue(newBalance);
                balanceAmount.setText(newBalance);
                }
            }
        });
    }

    private void findView(View view) {
        addMoneyAmount = view.findViewById(R.id.charge_fragment_add_money);
        balanceAmount = view.findViewById(R.id.charge_fragment_amount_of_money);
        chargeButton = view.findViewById(R.id.charge_fragment_button);
        chargeRecyclerView = view.findViewById(R.id.charge_fragment_recycler_view);
        ChargeFragmentCardViewHandler handler=new ChargeFragmentCardViewHandler(new ArrayList<Transactions>());
        chargeRecyclerView.setAdapter(handler);
        chargeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
