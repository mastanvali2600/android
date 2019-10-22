package com.example.honeyshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddItemAdapter extends RecyclerView.Adapter<AddItemViewHolder> {

    List<Item> items;

    public AddItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AddItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new AddItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
