package com.example.mercadoesclavo.dto;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Producto implements Serializable {


    @SerializedName("results")
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }



    public void setResults(List<Results> results) {
        this.results = results;
    }

    public Producto(List<Results> results) {
        this.results = results;
    }

    public Producto() {
    }
}
