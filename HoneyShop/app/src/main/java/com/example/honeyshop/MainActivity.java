package com.example.honeyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView total_price;
    Button addItem;
    private static List<InvoiceItem> invoiceItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        invoiceItems=new ArrayList<>();
        total_price.setText(String.valueOf(getPrice(invoiceItems)));

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
    public double getPrice(final List<InvoiceItem> invoiceItems){
        double price=0.0;
        for(InvoiceItem invoiceItem:invoiceItems){
            price=invoiceItem.getPrice()+price;
        }
        return price;
    }
    public static void addToInvoice(InvoiceItem invoiceItem){
        invoiceItems.add(invoiceItem);

    }

}