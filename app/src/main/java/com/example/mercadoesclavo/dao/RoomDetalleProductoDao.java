package com.example.mercadoesclavo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mercadoesclavo.dto.DetalleProducto;

import java.util.List;

@Dao
public interface RoomDetalleProductoDao {

    @Query("SELECT * FROM DetalleProducto")
    DetalleProducto getAll();

    @Query("SELECT * FROM DetalleProducto")
    List<DetalleProducto> getDetalleProductoList();

    @Insert
    void insert(DetalleProducto... detalleProductos);

    @Insert
    void insert(List<DetalleProducto> detalleProductoList);

    @Query("DELETE FROM DetalleProducto")
    void delete();

}
