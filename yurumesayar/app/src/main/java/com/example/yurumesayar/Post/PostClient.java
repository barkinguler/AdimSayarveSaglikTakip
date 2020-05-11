package com.example.yurumesayar.Post;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.99.1:8081/RestAp_/webresources/testPost/";

    private PostClient() {
    }

    public static synchronized Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    }


