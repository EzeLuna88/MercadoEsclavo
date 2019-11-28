package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City(String name) {
        this.name = name;
    }

    public City() {
    }
}
