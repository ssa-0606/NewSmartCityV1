package com.imikasa.part;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.part.pojo.Park;
import com.imikasa.part.pojo.ParkRecord;
import com.imikasa.part.task.ParkTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParkViewModel extends ViewModel {

    private final MutableLiveData<List<Park>> parkLiveData ;
    private final MutableLiveData<Park> parkLive;
    private final MutableLiveData<List<ParkRecord>> parkRecordLiveData;
    private final MutableLiveData<List<ParkRecord>> queryRecord;

    public ParkViewModel() {
        parkLiveData = new MutableLiveData<>();
        parkLive = new MutableLiveData<>();
        parkRecordLiveData = new MutableLiveData<>();
        queryRecord = new MutableLiveData<>();
        CompletableFuture<List<Park>> park = ParkTask.getPark("http://124.93.196.45:10001/prod-api/api/park/lot/list");
        park.thenAccept(parks -> {
            parkLiveData.postValue(parks);
            parkLive.postValue(parks.get(0));
        });
        CompletableFuture<List<ParkRecord>> record = ParkTask.getRecord("http://124.93.196.45:10001/prod-api/api/park/lot/record/list?entryTime=&outTime=");
        record.thenAccept(parkRecordLiveData::postValue);
    }

    public MutableLiveData<List<Park>> getParkLiveData() {
        return parkLiveData;
    }
    public MutableLiveData<Park> getParkLive(){return parkLive;}
    public MutableLiveData<List<ParkRecord>> getParkRecordLiveData(){return parkRecordLiveData;}
    public MutableLiveData<List<ParkRecord>> getQueryRecord(){
        return queryRecord;
    }
    public void setParkLive(Park park){
        parkLive.setValue(park);
    }
    public void setParkRecordLiveData(String in,String out){
        CompletableFuture<List<ParkRecord>> record = ParkTask.getRecord("http://124.93.196.45:10001/prod-api/api/park/lot/record/list?entryTime="+in+"&outTime="+out);
        record.thenAccept(queryRecord::postValue);
    }
}
