package com.example.mercadoesclavo.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(foreignKeys = {
        @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "CityId"),
        @ForeignKey(entity = State.class, parentColumns = "id", childColumns = "stateId"),
        @ForeignKey(entity = Country.class, parentColumns = "id", childColumns = "countryId")})
public class SellerAddress implements Serializable {

    @PrimaryKey
    @SerializedName("pepenoexistes1")
    private Integer id;


    @SerializedName("city")
    @Ignore
    private City city;
    @ColumnInfo(index = true)
    @SerializedName("pepenoexistes2")
    private Integer CityId;
    @SerializedName("state")
    @Ignore
    private State state;
    @ColumnInfo(index = true)
    @SerializedName("pepenoexistes3")
    private Integer stateId;
    @SerializedName("country")
    @Ignore
    private Country country;
    @ColumnInfo(index = true)
    @SerializedName("pepenoexistes4")
    private Integer countryId;

    @Ignore
    public SellerAddress(City city, State state, Country country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }
    public SellerAddress() {
    }

    public Integer getCityId() {
        return CityId;
    }

    public void setCityId(Integer cityId) {
        CityId = cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
