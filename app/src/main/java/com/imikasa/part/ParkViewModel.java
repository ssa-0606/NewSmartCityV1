package com.imikasa.part;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.part.pojo.Park;
import com.imikasa.part.task.ParkTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParkViewModel extends ViewModel {

    private final MutableLiveData<List<Park>> parkLiveData ;


    public ParkViewModel() {
        parkLiveData = new MutableLiveData<>();
        CompletableFuture<List<Park>> park = ParkTask.getPark("http://124.93.196.45:10001/prod-api/api/park/lot/list");
        park.thenAccept(parkLiveData::postValue);
    }

    public MutableLiveData<List<Park>> getParkLiveData() {
        return parkLiveData;
    }
}
