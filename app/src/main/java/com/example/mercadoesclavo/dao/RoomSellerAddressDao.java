package com.example.mercadoesclavo.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mercadoesclavo.dto.Results;
import com.example.mercadoesclavo.dto.SellerAddress;

import java.util.List;

@Dao
public interface RoomSellerAddressDao {

    @Query("SELECT * FROM SellerAddress")
    List<SellerAddress> getAll();

    @Query("SELECT * FROM SellerAddress")
    SellerAddress getSellerAddress();

    @Insert
    void insert(SellerAddress... sellerAddresses);

    @Insert
    void insert(List<SellerAddress> sellerAddressList);

    @Query("DELETE FROM SellerAddress")
    void delete();
}
