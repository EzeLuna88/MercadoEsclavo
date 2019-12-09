package com.example.mercadoesclavo.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Categories implements Serializable {

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    @PrimaryKey
    private Integer idRoom;

    @ColumnInfo
    @SerializedName("id")
    private String id;

    @ColumnInfo
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

    @Ignore
    public Categories(String id, String name, Integer idRoom) {
        this.id = id;
        this.name = name;
        this.idRoom = idRoom;
    }
}
