package com.rahmaninsani.a10119172latihan7.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    NIM     : 10119172
    Nama    : Rahman Insani
    Kelas   : IF-5

    Kamis, 14 Juli 2022
*/

public class MessageService {
    public static Retrofit retrofit;
    public static final String BASE_URL = "https://api.simsimi.net/v2/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
