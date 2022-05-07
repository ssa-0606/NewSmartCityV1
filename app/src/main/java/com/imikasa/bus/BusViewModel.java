package com.imikasa.bus;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.bus.pojo.BusLine;
import com.imikasa.bus.pojo.BusStop;
import com.imikasa.bus.task.BusTask;
import com.imikasa.ui.home.tasks.MyDataLoad;
import com.imikasa.ui.usercenter.pojo.UserInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BusViewModel extends ViewModel {

    private final MutableLiveData<List<BusLine>> busLineLiveData;
    private final MutableLiveData<List<BusStop>> busStopLiveData;
    private final MutableLiveData<BusLine> busLineLiveDataDetail;

    private final MutableLiveData<UserInfo> busUserLiveData;


    public BusViewModel() {
        busLineLiveData = new MutableLiveData<>();
        busStopLiveData = new MutableLiveData<>();
        busLineLiveDataDetail = new MutableLiveData<>();
        busUserLiveData = new MutableLiveData<>();
        CompletableFuture<List<BusLine>> busLine = BusTask.getBusLine("http://124.93.196.45:10001/prod-api/api/bus/line/list");
        busLine.thenAccept(busLineLiveData::postValue);
        CompletableFuture<List<BusStop>> busStop = BusTask.getBusStop("http://124.93.196.45:10001/prod-api/api/bus/stop/list");
        busStop.thenAccept(busStopLiveData::postValue);
        CompletableFuture<BusLine> busLineDetail = BusTask.getBusLineDetail("http://124.93.196.45:10001/prod-api/api/bus/line/1");
        busLineDetail.thenAccept(busLineLiveDataDetail::postValue);
    }

    public MutableLiveData<List<BusLine>> getBusLineLiveData(){return busLineLiveData;}
    public MutableLiveData<List<BusStop>> getBusStopLiveData(){return busStopLiveData;}
    public MutableLiveData<BusLine> getBusLineLiveDataDetail(){return busLineLiveDataDetail;}
    public MutableLiveData<UserInfo> getBusUserLiveData(){return busUserLiveData;}
    public void setBusStopLiveData(String id){
        CompletableFuture<List<BusStop>> busStop = BusTask.getBusStop("http://124.93.196.45:10001/prod-api/api/bus/stop/list?linesId=" + id);
        busStop.thenAccept(busStopLiveData::postValue);
    }
    public void setBusLineLiveDataDetail(String id){
        CompletableFuture<BusLine> busLineCompletableFuture = BusTask.getBusLineDetail("http://124.93.196.45:10001/prod-api/api/bus/line/"+id);
        busLineCompletableFuture.thenAccept(busLineLiveDataDetail::postValue);
    }
    public void setBusUserLiveData(String token){
        CompletableFuture<UserInfo> userInfo = MyDataLoad.getUserInfo("http://124.93.196.45:10001/prod-api/api/common/user/getInfo", token);
        userInfo.thenAccept(busUserLiveData::postValue);
    }
}
