package com.rahmaninsani.a10119172latihan7.service;

import com.rahmaninsani.a10119172latihan7.model.MessageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
    NIM     : 10119172
    Nama    : Rahman Insani
    Kelas   : IF-5

    Kamis, 14 Juli 2022
*/

public interface GetService {
    @GET("?lc=id&cf=false")
    Call<MessageModel> getResponse(@Query("text") String message);
}
