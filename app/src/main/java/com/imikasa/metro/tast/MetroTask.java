package com.imikasa.metro.tast;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.imikasa.metro.pojo.LineStation;
import com.imikasa.metro.pojo.MetroLine;
import com.imikasa.metro.pojo.MetroStation;
import com.imikasa.tools.MyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MetroTask {


    public static CompletableFuture<List<MetroLine>> getMainLines(String url){
        CompletableFuture<List<MetroLine>> completableFuture = CompletableFuture.supplyAsync(()->{
            List<MetroLine> metroLines = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JSONArray data = new JSONObject(result).getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    int lineId = jsonObject.getInt("lineId");
                    String lineName = jsonObject.getString("lineName");
                    int reachTime = jsonObject.getInt("reachTime");
                    String nextStep = jsonObject.getJSONObject("nextStep").getString("name");
                    metroLines.add(new MetroLine(lineId,lineName,reachTime,nextStep));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return metroLines;
        });
        return completableFuture;
    }



    public static CompletableFuture<MetroStation> getMetroStation(String url) {
        CompletableFuture<MetroStation> completableFuture = CompletableFuture.supplyAsync(()->{
            MetroStation metroStation = null;
            List<LineStation> lineStations = new ArrayList<>();
            try {
                String result = MyUtils.GET(url);
                JSONObject data = new JSONObject(result).getJSONObject("data");
                int id = data.getInt("id");
                String name = data.getString("name");
                String first = data.getString("first");
                String end = data.getString("end");
                int km = data.getInt("km");
                int stationsNumber = data.getInt("stationsNumber");
                String runStationsName = data.getString("runStationsName");
                int remainingTime = data.getInt("remainingTime");
                JSONArray metroStepList = data.getJSONArray("metroStepList");
                for (int i = 0; i < metroStepList.length(); i++) {
                    JSONObject jsonObject = metroStepList.getJSONObject(i);
                    int id1 = jsonObject.getInt("id");
                    String name1 = jsonObject.getString("name");
                    lineStations.add(new LineStation(id1,name1));
                }
                metroStation = new MetroStation(id,name,first,end,remainingTime,stationsNumber,km,runStationsName,lineStations);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return metroStation;
        });
        return completableFuture;
    }



}
