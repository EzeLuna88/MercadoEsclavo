package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State(String name) {
        this.name = name;
    }

    public State() {
    }
}
