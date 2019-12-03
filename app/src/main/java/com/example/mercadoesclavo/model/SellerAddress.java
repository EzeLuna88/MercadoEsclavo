package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SellerAddress implements Serializable {


    @SerializedName("city")
    private City city;
    @SerializedName("state")
    private State state;
    @SerializedName("country")
    private Country country;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public SellerAddress(City city, State state, Country country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public SellerAddress() {
    }
}
