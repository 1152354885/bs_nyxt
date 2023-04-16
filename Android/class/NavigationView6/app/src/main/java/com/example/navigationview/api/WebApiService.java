package com.example.navigationview.api;


import com.example.navigationview.entity.User;
import com.example.navigationview.result.BaseResult;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

//接口定义
public interface WebApiService {
    @POST("user/login")
    Call<BaseResult<User>> login(@Body RequestBody body);

    @POST("user/register")
    Call<BaseResult<Object>> register(@Body RequestBody body);

    @PUT("user/forgetPwd")
    Call<BaseResult<Object>> forgetPwd(@Body RequestBody body);

    @GET("question/list")
    Call<BaseResult<JsonObject>> getQuestions();
}


