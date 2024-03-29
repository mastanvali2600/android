package com.example.honeyshop;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Insert
    void insertAll(Item... users);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("Delete from Item")
    void deleteAll();
}
