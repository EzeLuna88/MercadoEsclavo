package com.example.mercadoesclavo.dto;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Description implements Serializable {

    @PrimaryKey
    public Integer id;


    @ColumnInfo
    @SerializedName("plain_text")
    private String plaintText;

    public String getPlaintText() {
        return plaintText;
    }

    public void setPlaintText(String plaintText) {
        this.plaintText = plaintText;
    }

    @Ignore
    public Description(String plaintText) {
        this.plaintText = plaintText;
    }

    public Description() {
    }
}
