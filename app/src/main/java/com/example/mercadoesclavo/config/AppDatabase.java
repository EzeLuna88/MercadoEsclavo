package com.example.mercadoesclavo.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mercadoesclavo.dao.RoomMercadoLibreDao;
import com.example.mercadoesclavo.dto.Categories;
import com.example.mercadoesclavo.dto.Results;

@Database(version = 1, entities = {Categories.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomMercadoLibreDao roomMercadoLibreDao();


    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "mercado-esclavo-db")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
