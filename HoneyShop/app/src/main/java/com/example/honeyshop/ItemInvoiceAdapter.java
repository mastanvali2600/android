package com.example.honeyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemInvoiceAdapter extends RecyclerView.Adapter<ItemInvoiceViewHolder> {

    List<InvoiceItem> invoiceItems;

    Context context;

    public ItemInvoiceAdapter(List<InvoiceItem> invoiceItems,Context context) {
        this.invoiceItems = invoiceItems;
        this.context=context;
    }


    @Override
    public ItemInvoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_item_row,parent,false);
        return new ItemInvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemInvoiceViewHolder holder, int position) {
        final InvoiceItem invoiceItem=invoiceItems.get(position);
        holder.name.setText(invoiceItem.getName());
        holder.price.setText(String.valueOf(invoiceItem.getPrice()));
        holder.quantity.setText(invoiceItem.getQuntity());
        holder.invoiceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"card clicked",Toast.LENGTH_SHORT).show();

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                invoiceItems.remove(position);
                MainActivity.setTotal(invoiceItems);
                notifyItemRemoved(position);
            }
        });

    }



    @Override
    public int getItemCount() {
        return invoiceItems.size();
    }
}
