package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleProducto {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("seller_id")
    private String sellerId;
    @SerializedName("price")
    private Double price;
    @SerializedName("initial_quantity")
    private Integer initialQuantity;
    @SerializedName("available_quantity")
    private Integer availableQuantity;
    @SerializedName("buying_mode")
    private String buyingMode;
    @SerializedName("condition")
    private String condition;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("secure_thumbnail")
    private String secureThumbnail;
    @SerializedName("picture")
    private List<Pictures> picturesList;
    @SerializedName("seller_address")
    private SellerAddress sellerAddress;
    @SerializedName("warranty")
    private String warranty;

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

    public List<Pictures> getPicturesList() {
        return picturesList;
    }

    public void setPicturesList(List<Pictures> picturesList) {
        this.picturesList = picturesList;
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

    public DetalleProducto(String id, String title, String sellerId, Double price, Integer initialQuantity,
                           Integer availableQuantity, String buyingMode, String condition, String thumbnail,
                           String secureThumbnail, List<Pictures> picturesList, SellerAddress sellerAddress, String warranty) {
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
        this.picturesList = picturesList;
        this.sellerAddress = sellerAddress;
        this.warranty = warranty;
    }

    public DetalleProducto() {
    }
}


