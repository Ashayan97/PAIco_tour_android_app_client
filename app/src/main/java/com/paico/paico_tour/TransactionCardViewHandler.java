package com.paico.paico_tour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionCardViewHandler extends RecyclerView.Adapter<TransactionCardViewHandler.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temp_transaction, parent, false);
        return new TransactionCardViewHandler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView amount;
        public TextView id;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.transaction_fragment_card_view_id);
            date = itemView.findViewById(R.id.transaction_fragment_card_view_date);
            amount = itemView.findViewById(R.id.transaction_fragment_card_view_amount);
            title = itemView.findViewById(R.id.transaction_fragment_card_view_title);
        }

    }
}
