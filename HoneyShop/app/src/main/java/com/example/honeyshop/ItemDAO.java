package com.example.honeyshop;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Insert
    void insertAll(Item... users);
}
