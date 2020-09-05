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

import com.paico.paico_tour.object_classes.UserHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        Comparator<Transactions> comparator = new Comparator<Transactions>() {
            @Override
            public int compare(Transactions o1, Transactions o2) {
                if (o1.getId().compareTo(o2.getId())==1)
                    return 1;
                else return -1;
            }
        };
        Collections.sort(UserHolder.getInstance().getUser().getTransactions(), comparator);
        TransactionCardViewHandler transactionCardViewHandler = new TransactionCardViewHandler(UserHolder.getInstance().getUser().getTransactions());
        recyclerView.setAdapter(transactionCardViewHandler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.transaction_fragment_recycler_view);
    }
}
