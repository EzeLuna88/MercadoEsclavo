package com.example.mercadoesclavo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Categories implements Serializable {


    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories() {
    }

    public Categories(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
