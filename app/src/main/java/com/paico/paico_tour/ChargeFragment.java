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
import com.google.firebase.database.Transaction;
import com.paico.paico_tour.object_classes.User;
import com.paico.paico_tour.object_classes.UserHolder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class ChargeFragment extends Fragment {
    private EditText addMoneyAmount;
    private TextView balanceAmount;
    private Button chargeButton;
    private RecyclerView chargeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.charge_fragment, container, false);
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
                if (addMoneyAmount.getText() != null && !addMoneyAmount.getText().toString().equals("")) {
                    String newBalance = String.valueOf(Integer.valueOf(UserHolder.getInstance().getUser().getBalance()) + Integer.valueOf(addMoneyAmount.getText().toString()));
                    balanceAmount.setText(newBalance);
                    User user = UserHolder.getInstance().getUser();
                    if (user.getTransactions() == null)
                        user.setTransactions(new ArrayList<Transactions>());
                    if (user.getCharges() == null)
                        user.setCharges(new ArrayList<Transactions>());
                    Transactions transaction = new Transactions();
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                    transaction.setId(FirebaseAuth.getInstance().getUid().substring(0, 2) + timeStamp);
                    transaction.setAmount("+ " + addMoneyAmount.getText().toString());
                    String time= timeStamp.substring(0,4)+"/"+timeStamp.substring(4,6)+"/"+timeStamp.substring(6,8);
                    transaction.setDate(time);
                    transaction.setTitle("Charge");
                    user.getTransactions().add(transaction);
                    user.getCharges().add(transaction);
                    user.setBalance(newBalance);
                    FirebaseDatabase.getInstance().getReference("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).
                            setValue(user);
                    updateView();
                }
            }
        });
    }
    private void updateView(){
        Comparator<Transactions> comparator = new Comparator<Transactions>() {
            @Override
            public int compare(Transactions o1, Transactions o2) {
                if (o1.getId().compareTo(o2.getId())==1)
                    return 1;
                else return -1;
            }
        };
        Collections.sort(UserHolder.getInstance().getUser().getCharges(), comparator);
        ChargeFragmentCardViewHandler handler = new ChargeFragmentCardViewHandler(UserHolder.getInstance().getUser().getCharges());
        chargeRecyclerView.setAdapter(handler);
        chargeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void findView(View view) {
        addMoneyAmount = view.findViewById(R.id.charge_fragment_add_money);
        balanceAmount = view.findViewById(R.id.charge_fragment_amount_of_money);
        chargeButton = view.findViewById(R.id.charge_fragment_button);
        chargeRecyclerView = view.findViewById(R.id.charge_fragment_recycler_view);
        updateView();
    }
}
