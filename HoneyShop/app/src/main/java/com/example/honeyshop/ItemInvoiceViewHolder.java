package com.example.honeyshop;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemInvoiceViewHolder extends RecyclerView.ViewHolder {
    TextView name,price,quantity,remove;
    CardView invoiceCard;
    public ItemInvoiceViewHolder(@NonNull final View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        price=itemView.findViewById(R.id.price);
        quantity=itemView.findViewById(R.id.quantity);
        remove=itemView.findViewById(R.id.remove);
        invoiceCard=itemView.findViewById(R.id.invoice_cardView);

    }
}