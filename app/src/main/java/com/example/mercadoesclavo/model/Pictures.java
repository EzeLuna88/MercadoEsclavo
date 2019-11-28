package com.example.mercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

public class Pictures {

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
