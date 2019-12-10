package com.example.mercadoesclavo.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;


@Entity
public class City implements Serializable {

    @PrimaryKey
    @SerializedName("pepenoexistes")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ColumnInfo
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Ignore
    public City(String name) {
        this.name = name;
    }

    public City() {
    }
}
