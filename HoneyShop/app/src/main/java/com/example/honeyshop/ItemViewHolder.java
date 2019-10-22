package com.example.honeyshop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name,removeItem;
    public ItemViewHolder(@NonNull final View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.image);
        name=itemView.findViewById(R.id.name);
        removeItem=itemView.findViewById(R.id.removeItem);
    }
}