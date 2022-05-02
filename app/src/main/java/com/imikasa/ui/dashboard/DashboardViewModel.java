package com.imikasa.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.ui.home.pojo.MainService;
import com.imikasa.ui.home.tasks.MyDataLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<List<MainService>> serviceLiveData;

    public DashboardViewModel() {
        serviceLiveData = new MutableLiveData<>();
        CompletableFuture<List<MainService>> service = MyDataLoad.getService("http://124.93.196.45:10001/prod-api/api/service/list?serviceType=车主服务");
        service.thenAccept(serviceLiveData::postValue);
    }

    public LiveData<List<MainService>> getService() {
        return serviceLiveData;
    }
    public void setServiceLiveData(String type){
        CompletableFuture<List<MainService>> service = MyDataLoad.getService("http://124.93.196.45:10001/prod-api/api/service/list?serviceType=" + type);
        service.thenAccept(serviceLiveData::postValue);
    }
}