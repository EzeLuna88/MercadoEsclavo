package com.example.mercadoesclavo.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Producto {

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
