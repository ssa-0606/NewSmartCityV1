package com.imikasa.hospital;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.hospital.fragments.HospitalFragment;
import com.imikasa.hospital.pojo.HospitalInfo;
import com.imikasa.hospital.pojo.HospitalLunBo;
import com.imikasa.hospital.task.HospitalTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class HospitalViewModel extends ViewModel {

    private final MutableLiveData<List<HospitalLunBo>> hospitalLunBoListLiveData;
    private final MutableLiveData<List<HospitalInfo>> hospitalInfoListLiveData;
    private final MutableLiveData<HospitalInfo> hospitalInfoMutableLiveData;

    public HospitalViewModel() {
        hospitalLunBoListLiveData = new MutableLiveData<>();
        hospitalInfoListLiveData = new MutableLiveData<>();
        hospitalInfoMutableLiveData = new MutableLiveData<>();
        CompletableFuture<List<HospitalInfo>> info = HospitalTask.getInfo("http://124.93.196.45:10001/prod-api/api/hospital/hospital/list");
        info.thenAccept(hospitalInfoListLiveData::postValue);
    }

    public MutableLiveData<List<HospitalLunBo>> getHospitalLunBoListLiveData (){return hospitalLunBoListLiveData;}
    public MutableLiveData<List<HospitalInfo>> getHospitalInfoListLiveData(){return hospitalInfoListLiveData;}
    public MutableLiveData<HospitalInfo> getHospitalInfoMutableLiveData(){return hospitalInfoMutableLiveData;}

    public void setHospitalLunBoListLiveData(int id){
        CompletableFuture<List<HospitalLunBo>> lunBo = HospitalTask.getLunBo("http://124.93.196.45:10001/prod-api/api/hospital/banner/list?hospitalId="+id);
        lunBo.thenAccept(hospitalLunBoListLiveData::postValue);
    }

    public void setHospitalInfoMutableLiveData(int id){
        CompletableFuture<HospitalInfo> infoCompletableFuture = HospitalTask.getInfoById("http://124.93.196.45:10001/prod-api/api/hospital/hospital/"+id);
        infoCompletableFuture.thenAccept(hospitalInfoMutableLiveData::postValue);
    }

}
