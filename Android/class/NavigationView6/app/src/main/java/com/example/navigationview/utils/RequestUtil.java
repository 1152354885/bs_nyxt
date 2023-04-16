package com.example.navigationview.utils;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

public class RequestUtil {
    public static RequestBody getRequestBody(Map<String, Object> map){
        Gson gson = new Gson();
        String strEntity = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        return body;
    }
}
