package com.example.mercadoesclavo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.mercadoesclavo.dto.Results;

import java.util.List;

@Dao
public interface RoomResultsDao {

    @Query("SELECT * FROM Results")
    List<Results> getAll();

    @Insert
    void insert(Results... results);

    @Insert
    void insert(List<Results> resultsList);

    @Query("DELETE FROM RESULTS")
    void delete();
}
