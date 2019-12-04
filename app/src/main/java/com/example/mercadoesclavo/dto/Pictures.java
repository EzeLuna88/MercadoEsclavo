package com.example.mercadoesclavo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pictures implements Serializable {

    @SerializedName("secure_url")
    private String secureUrl;

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public Pictures(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public Pictures() {
    }
}
