package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

public class Description {

    @SerializedName("plain_text")
    private String plaintText;

    public String getPlaintText() {
        return plaintText;
    }

    public void setPlaintText(String plaintText) {
        this.plaintText = plaintText;
    }

    public Description(String plaintText) {
        this.plaintText = plaintText;
    }

    public Description() {
    }
}
