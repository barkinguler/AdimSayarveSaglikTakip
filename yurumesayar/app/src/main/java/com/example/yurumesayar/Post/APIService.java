package com.example.yurumesayar.Post;

import com.example.yurumesayar.Models.Bilgiler;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
@POST("create")
    Call<ResponseBody>createPost(@Body RequestBody requestBody);
@POST("update")
    Call<ResponseBody>createPost1(@Body RequestBody requestBody);
@POST("endex")
    Call<ResponseBody>createPost2(@Body RequestBody requestBody);

}
