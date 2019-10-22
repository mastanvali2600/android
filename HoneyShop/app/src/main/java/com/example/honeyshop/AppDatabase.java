package com.example.honeyshop;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class,InvoiceItem.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDAO itemDAO();
    public abstract InvoiceItemDAO invoiceItemDAO();
}
