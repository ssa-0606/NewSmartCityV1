package com.imikasa.hospital.task;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.hospital.pojo.HospitalInfo;
import com.imikasa.hospital.pojo.HospitalLunBo;
import com.imikasa.hospital.pojo.Patient;
import com.imikasa.tools.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HospitalTask {

    public static CompletableFuture<List<HospitalLunBo>> getLunBo(String url){
        CompletableFuture<List<HospitalLunBo>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<HospitalLunBo> hospitalLunBos = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JsonArray data = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("data");
                for (JsonElement datum : data) {
                    HospitalLunBo lunBo = new Gson().fromJson(datum,new TypeToken<HospitalLunBo>(){}.getType());
                    hospitalLunBos.add(lunBo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hospitalLunBos;
        });
        return completableFuture;
    }
    public static CompletableFuture<List<HospitalInfo>> getInfo(String url){
        CompletableFuture<List<HospitalInfo>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<HospitalInfo> hospitalLunBos = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JsonArray data = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement datum : data) {
                    HospitalInfo lunBo = new Gson().fromJson(datum,new TypeToken<HospitalInfo>(){}.getType());
                    hospitalLunBos.add(lunBo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return hospitalLunBos;
        });
        return completableFuture;
    }

    public static CompletableFuture<HospitalInfo> getInfoById(String url){
        CompletableFuture<HospitalInfo> completableFuture = CompletableFuture.supplyAsync(()->{
            HospitalInfo info = null;
            try {
                String get = MyUtils.GET(url);
                JsonObject data = new JsonParser().parse(get).getAsJsonObject().getAsJsonObject("data");
                info = new Gson().fromJson(data,new TypeToken<HospitalInfo>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return info;
        });
        return completableFuture;
    }


    public static CompletableFuture<List<Patient>> getPatient(String url,String token){
        CompletableFuture<List<Patient>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<Patient> patients = new ArrayList<>();
            try {
                String result = MyUtils.GET_T(url, token);
                JsonArray rows = new JsonParser().parse(result).getAsJsonObject().getAsJsonArray("rows");
                for (JsonElement row : rows) {
                    Patient patient = new Gson().fromJson(row,new TypeToken<Patient>(){}.getType());
                    patients.add(patient);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return patients;
        });
        return completableFuture;
    }


}
