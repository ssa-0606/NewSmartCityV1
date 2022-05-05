package com.imikasa.part;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.part.pojo.Park;
import com.imikasa.part.task.ParkTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParkViewModel extends ViewModel {

    private final MutableLiveData<List<Park>> parkLiveData ;
    private final MutableLiveData<Park> parkLive;


    public ParkViewModel() {
        parkLiveData = new MutableLiveData<>();
        parkLive = new MutableLiveData<>();
        CompletableFuture<List<Park>> park = ParkTask.getPark("http://124.93.196.45:10001/prod-api/api/park/lot/list");
        park.thenAccept(parks -> {
            parkLiveData.postValue(parks);
            parkLive.postValue(parks.get(0));
        });
    }

    public MutableLiveData<List<Park>> getParkLiveData() {
        return parkLiveData;
    }
    public MutableLiveData<Park> getParkLive(){return parkLive;}
    public void setParkLive(Park park){
        parkLive.setValue(park);
    }
}
