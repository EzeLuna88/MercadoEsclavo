package com.example.mercadoesclavo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mercadoesclavo.dto.City;
import com.example.mercadoesclavo.dto.SellerAddress;

import java.util.List;

@Dao
public interface RoomCityDao {
    @Query("SELECT * FROM City")
    List<City> getAll();

    @Query("SELECT * FROM City")
    City getCity();

    @Insert
    void insert(City... city);

    @Insert
    void insert(List<City> cityList);

    @Query("DELETE FROM City")
    void delete();
}
