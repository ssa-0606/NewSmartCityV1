package com.imikasa.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.ui.home.pojo.MainService;
import com.imikasa.ui.home.tasks.MyDataLoad;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<List<MainService>> serviceLiveData;
    private final MutableLiveData<List<MainService>> showService;
    private final MutableLiveData<List<MainService>> allServiceLiveData;

    public DashboardViewModel() {
        serviceLiveData = new MutableLiveData<>();
        allServiceLiveData = new MutableLiveData<>();
        showService = new MutableLiveData<>();
        CompletableFuture<List<MainService>> service = MyDataLoad.getService("http://124.93.196.45:10001/prod-api/api/service/list?serviceType=车主服务");
        service.thenAccept(serviceLiveData::postValue);
        CompletableFuture<List<MainService>> allService = MyDataLoad.getService("http://124.93.196.45:10001/prod-api/api/service/list");
        allService.thenAccept(allServiceLiveData::postValue);
    }

    public LiveData<List<MainService>> getService() {
        return serviceLiveData;
    }
    public LiveData<List<MainService>> getShowService(){return showService;}
    public void setServiceLiveData(String type){
        CompletableFuture<List<MainService>> service = MyDataLoad.getService("http://124.93.196.45:10001/prod-api/api/service/list?serviceType=" + type);
        service.thenAccept(serviceLiveData::postValue);
    }

    public void setServiceLiveDataByMsg(String msg){
        List<MainService> value = allServiceLiveData.getValue();
        List<MainService> collect = value.stream().filter(mainService -> mainService.getServiceName().contains(msg)).collect(Collectors.toList());
        if(collect.size()>0){
            showService.setValue(collect);
        }else if(collect.size() == 0){
            showService.setValue(null);
        }
    }

}