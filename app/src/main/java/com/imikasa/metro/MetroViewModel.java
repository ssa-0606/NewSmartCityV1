package com.imikasa.metro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.metro.pojo.MetroLine;
import com.imikasa.metro.tast.MetroTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MetroViewModel extends ViewModel {
    private final MutableLiveData<List<MetroLine>> listMetroLineLiveData;

    public MetroViewModel() {
        listMetroLineLiveData = new MutableLiveData<>();
        CompletableFuture<List<MetroLine>> mainLines = MetroTask.getMainLines("http://124.93.196.45:10001/prod-api/api/metro/list?currentName=建国门");
        mainLines.thenAccept(listMetroLineLiveData::postValue);
    }

    public MutableLiveData<List<MetroLine>> getListMetroLineLiveData(){return listMetroLineLiveData;}


}
