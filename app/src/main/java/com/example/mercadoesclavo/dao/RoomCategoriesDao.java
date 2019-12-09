package com.example.mercadoesclavo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mercadoesclavo.dto.Categories;
import com.example.mercadoesclavo.dto.Results;

import java.util.List;

@Dao
public interface RoomCategoriesDao {

    @Query("SELECT * FROM Categories")
    List<Categories> getAll();

    @Insert
    void insert(Categories... categories);

    @Insert
    void insert(List<Categories> categoriesList);

    @Query("DELETE FROM CATEGORIES")
    void delete();







}
