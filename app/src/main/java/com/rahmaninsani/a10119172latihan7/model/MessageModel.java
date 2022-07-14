package com.rahmaninsani.a10119172latihan7.model;

import com.google.gson.annotations.SerializedName;

/*
    NIM     : 10119172
    Nama    : Rahman Insani
    Kelas   : IF-5

    Kamis, 14 Juli 2022
*/

public class MessageModel {
    @SerializedName("success")
    private final String responseMessage;

    public MessageModel(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

}
