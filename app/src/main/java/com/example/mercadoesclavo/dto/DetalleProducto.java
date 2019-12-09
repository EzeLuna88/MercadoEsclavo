package com.example.mercadoesclavo.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class DetalleProducto implements Serializable {

    @PrimaryKey
    private Integer idRoom;

    @ColumnInfo
    @SerializedName("id")
    private String id;
    @ColumnInfo
    @SerializedName("title")
    private String title;
    @ColumnInfo
    @SerializedName("seller_id")
    private String sellerId;
    @ColumnInfo
    @SerializedName("price")
    private Double price;
    @ColumnInfo
    @SerializedName("initial_quantity")
    private Integer initialQuantity;
    @ColumnInfo
    @SerializedName("available_quantity")
    private Integer availableQuantity;
    @ColumnInfo
    @SerializedName("buying_mode")
    private String buyingMode;
    @ColumnInfo
    @SerializedName("condition")
    private String condition;
    @ColumnInfo
    @SerializedName("thumbnail")
    private String thumbnail;
    @ColumnInfo
    @SerializedName("secure_thumbnail")
    private String secureThumbnail;
    @Ignore
    @SerializedName("pictures")
    private List<Pictures> pictures;
    @Ignore
    @SerializedName("seller_address")
    private SellerAddress sellerAddress;
    @ColumnInfo
    @SerializedName("warranty")
    private String warranty;
    @Ignore
    @SerializedName("geolocation")
    private Geolocation geolocation;

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocationList(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }

    public void setPictures(List<Pictures> picturesList) {
        this.pictures = picturesList;
    }

    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(SellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    @Ignore
    public DetalleProducto(Integer idRoom, String id, String title, String sellerId, Double price, Integer initialQuantity,
                           Integer availableQuantity, String buyingMode, String condition, String thumbnail,
                           String secureThumbnail, List<Pictures> pictures, SellerAddress sellerAddress, String warranty, List<Geolocation> geolocationList) {
        this.id = id;
        this.title = title;
        this.sellerId = sellerId;
        this.price = price;
        this.initialQuantity = initialQuantity;
        this.availableQuantity = availableQuantity;
        this.buyingMode = buyingMode;
        this.condition = condition;
        this.thumbnail = thumbnail;
        this.secureThumbnail = secureThumbnail;
        this.pictures = pictures;
        this.sellerAddress = sellerAddress;
        this.warranty = warranty;
        this.geolocation = geolocation;
    }

    public DetalleProducto() {
    }
}


