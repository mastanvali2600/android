package com.example.honeyshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AddItemToListActivity extends AppCompatActivity {

    Button add_new_item;
    RecyclerView add_new_item_recycle_view;
    static List<Item> items=new ArrayList<>();
    List<Type> types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_list);
        init();

        types=new ArrayList<>();
        addTypes();
        add_new_item_recycle_view.setLayoutManager(new GridLayoutManager(this,3));
        //TO-d0 get items from db set here in items
        AppDatabase db= Room.databaseBuilder(this,AppDatabase.class,"Production").allowMainThreadQueries().build();
        add_new_item_recycle_view.setAdapter(new ItemAdapter(db.itemDAO().getAll(),types,AddItemToListActivity.this));
        onclickListeners();
    }
    private void init(){
        add_new_item_recycle_view=findViewById(R.id.add_new_item_recycle_view);
        add_new_item=findViewById(R.id.add_new_item);
    }
    private void onclickListeners(){
        add_new_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddItemToListActivity.this,CreateItemActivity.class));
            }
        });
    }
    public void addTypes(){
        types.add(new Type(1,"KG"));
        types.add(new Type(2,"Liter"));
        types.add(new Type(3,"Pack"));
    }
    public static void addItem(Item item){
        items.add(item);
    }

}
