package com.paico.paico_tour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class TransactionFragment extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.transaction_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        setView();
    }

    private void setView() {
        //TODO add api of getting transactions
        ArrayList<Transactions> transactions = new ArrayList<>();
        Transactions fake1 = new Transactions(new Date(), "6984342", "+1000T", "charge");
        Transactions fake2 = new Transactions(new Date(), "2345968", "-1000T", "Bus ticket");
        Transactions fake3 = new Transactions(new Date(), "9654742", "+5000T", "charge");
        Transactions fake4 = new Transactions(new Date(), "6485478", "-9000T", "cinema ticket");
        transactions.add(fake1);
        transactions.add(fake2);
        transactions.add(fake3);
        transactions.add(fake4);

        TransactionCardViewHandler transactionCardViewHandler = new TransactionCardViewHandler(transactions);
        recyclerView.setAdapter(transactionCardViewHandler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.transaction_fragment_recycler_view);
    }
}
