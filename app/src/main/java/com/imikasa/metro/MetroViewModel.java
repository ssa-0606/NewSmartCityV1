package com.imikasa.metro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.metro.pojo.Line;
import com.imikasa.metro.pojo.MetroLine;
import com.imikasa.metro.pojo.MetroStation;
import com.imikasa.metro.tast.MetroTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MetroViewModel extends ViewModel {
    private final MutableLiveData<List<MetroLine>> listMetroLineLiveData;
    private final MutableLiveData<MetroStation> metroStationMutableLiveData;
    private final MutableLiveData<List<Line>> listLineMutableLiveData;

    public MetroViewModel() {
        listMetroLineLiveData = new MutableLiveData<>();
        metroStationMutableLiveData = new MutableLiveData<>();
        listLineMutableLiveData = new MutableLiveData<>();
        CompletableFuture<List<MetroLine>> mainLines = MetroTask.getMainLines("http://124.93.196.45:10001/prod-api/api/metro/list?currentName=建国门");
        mainLines.thenAccept(listMetroLineLiveData::postValue);
        CompletableFuture<List<Line>> lines = MetroTask.getLine("http://124.93.196.45:10001/prod-api/api/metro/line/list");
        lines.thenAccept(listLineMutableLiveData::postValue);
    }

    public MutableLiveData<List<MetroLine>> getListMetroLineLiveData(){return listMetroLineLiveData;}
    public MutableLiveData<MetroStation> getMetroStationMutableLiveData(){return metroStationMutableLiveData;}
    public MutableLiveData<List<Line>> getListLineMutableLiveData(){return listLineMutableLiveData;}

    public void setMetroStationMutableLiveData(int id){
        CompletableFuture<MetroStation> completableFuture = MetroTask.getMetroStation("http://124.93.196.45:10001/prod-api/api/metro/line/"+id);
        completableFuture.thenAccept(metroStationMutableLiveData::postValue);
    }


}
