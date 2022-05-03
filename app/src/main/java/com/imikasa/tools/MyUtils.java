package com.imikasa.tools;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyUtils {

    public static String GET(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        return result;
    }

    public static String GET_T(String url ,String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(url).addHeader("Authorization", token).build();
        Response res = client.newCall(req).execute();
        return res.body().string();
    }

    public static String POST(String url,String msg) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,msg);
        Request request = new Request.Builder().url(url).method("POST",body).addHeader("Content-Type","application/json").build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    public static String PUT_T(String url,String msg,String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,msg);
        Request request = new Request.Builder().url(url).method("PUT",body).addHeader("Content-Type","application/json").addHeader("Authorization",token).build();
        return client.newCall(request).execute().body().string();
    }


}
