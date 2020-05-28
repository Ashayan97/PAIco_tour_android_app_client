package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChargeFragmentCardViewHandler extends RecyclerView.Adapter<ChargeFragmentCardViewHandler.ViewHolder> {
    private ArrayList<Transactions> transactions;

    public ChargeFragmentCardViewHandler(ArrayList<Transactions> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_charge_box, parent, false);
        return new ChargeFragmentCardViewHandler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.amount.setText(transactions.get(position).getAmount());
        holder.id.setText(transactions.get(position).getId());
        holder.date.setText(transactions.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView amount;
        public TextView id;
        public TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount= itemView.findViewById(R.id.charge_card_view_amount);
            id= itemView.findViewById(R.id.charge_card_view_id);
            date= itemView.findViewById(R.id.charge_card_view_date);
        }
    }
}