package com.example.honeyshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    List<Item> items;
    List<Type> types;
    Context context;
    public ItemAdapter(List<Item> items, List<Type> types,Context context) {
        this.items = items;
        this.types = types;
        this.context=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        final Item item=items.get(position);
        if(item.getName()!=null)
            holder.name.setText(item.getName());
        if(item.getImage()!=null)
            holder.image.setImageBitmap(DataConverter.converByteArray2Bitmap(item.getImage()));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AddItem2InvoiceActivity.class);
                intent.putExtra("item",item);
                context.startActivity(intent);
            }
        });
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db= Room.databaseBuilder(context,AppDatabase.class,"Production").allowMainThreadQueries().build();
                db.itemDAO().delete(item);
                items.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public String getType(int id){
        for(Type type:types){
            if(type.getTypeId()==id){
                return type.getTypeName();
            }
        }
        return "Not Avalable";
    }
}