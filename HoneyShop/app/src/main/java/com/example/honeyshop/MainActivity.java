package com.example.honeyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static TextView total_price;
    Button addItem;
    private static List<InvoiceItem> invoiceItems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setTotal(invoiceItems);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemInvoiceAdapter(invoiceItems,MainActivity.this));
        onclickListeners();
    }
    private void init(){
        recyclerView=findViewById(R.id.add_2_cart_recycle_view);
        total_price=findViewById(R.id.total_price);
        addItem=findViewById(R.id.addItem);
    }
    private void onclickListeners(){
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddItemToListActivity.class));
            }
        });

    }
    public static double getPrice(final List<InvoiceItem> invoiceItems){
        double price=0.0;
        for(InvoiceItem invoiceItem:invoiceItems){
            price=invoiceItem.getPrice()+price;
        }
        return price;
    }
    public static void addToInvoice(InvoiceItem invoiceItem){
        invoiceItems.add(invoiceItem);

    }
    public static void setTotal(List<InvoiceItem> invoiceItems){
        total_price.setText(String.valueOf(getPrice(invoiceItems)));
    }
    /*public void clearAll(View view){
        AppDatabase db= Room.databaseBuilder(MainActivity.this,AppDatabase.class,"Production").allowMainThreadQueries().build();
        db.invoiceItemDAO().deleteAll();

    }
*/
}