package com.example.mercadoesclavo.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity
public class Country implements Serializable {

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
    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }
}
