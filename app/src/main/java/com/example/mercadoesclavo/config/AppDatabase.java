package com.example.mercadoesclavo.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mercadoesclavo.dao.RoomCategoriesDao;
import com.example.mercadoesclavo.dao.RoomCityDao;
import com.example.mercadoesclavo.dao.RoomDescriptionDao;
import com.example.mercadoesclavo.dao.RoomDetalleProductoDao;
import com.example.mercadoesclavo.dao.RoomResultsDao;
import com.example.mercadoesclavo.dao.RoomSellerAddressDao;
import com.example.mercadoesclavo.dto.Categories;
import com.example.mercadoesclavo.dto.City;
import com.example.mercadoesclavo.dto.Country;
import com.example.mercadoesclavo.dto.Description;
import com.example.mercadoesclavo.dto.DetalleProducto;
import com.example.mercadoesclavo.dto.Results;
import com.example.mercadoesclavo.dto.SellerAddress;
import com.example.mercadoesclavo.dto.State;

@Database(version = 5, entities = {Categories.class, Results.class, DetalleProducto.class, Description.class, SellerAddress.class, City.class, State.class, Country.class}, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomCategoriesDao roomCategoriesDao();
    public abstract RoomResultsDao roomResultsDao();
    public abstract RoomDetalleProductoDao roomDetalleProductoDao();
    public abstract RoomDescriptionDao roomDescriptionDao();
    public abstract RoomSellerAddressDao roomSellerAddressDao();
    public abstract RoomCityDao roomCityDao();


    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "mercado-esclavo-db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            ;
        }
        return INSTANCE;
    }


}
