package com.example.yurumesayar.RestApi;

import com.example.yurumesayar.Models.Bilgiler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("getData")
    Call<List<Bilgiler>> BilgiGetir();
    @GET("getadim")
    Call<List<Bilgiler>> BilgiGetiradim();
    @GET("getgecmis")
    Call<List<Bilgiler>> BilgiGetiradimgecmis();
    @GET("kilogecmis")
    Call<List<Bilgiler>> BilgiGetirkilogecmis();
    @GET("getHata")
    Call<List<Bilgiler>> BilgiGetirhata();
}
