package com.example.mercadoesclavo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mercadoesclavo.dto.Description;

import java.util.List;

@Dao
public interface RoomDescriptionDao {

    @Query("SELECT * FROM Description")
    Description getAll();

    @Insert
    void insert(Description... descriptions);

    @Insert
    void insert(List<Description> descriptionList);

    @Query("DELETE FROM Description")
    void delete();
}
