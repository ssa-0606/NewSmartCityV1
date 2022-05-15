package com.imikasa.movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.imikasa.movie.pojo.MovieDetail;
import com.imikasa.movie.pojo.MovieLunBo;
import com.imikasa.movie.task.MovieTask;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MovieViewModel extends ViewModel {

    private final MutableLiveData<List<MovieLunBo>> lunBoLiveData;
    private final MutableLiveData<List<MovieDetail>> listDetailLiveData;
    private final MutableLiveData<MovieDetail> detailLiveData;

    public MovieViewModel() {
        lunBoLiveData = new MutableLiveData<>();
        listDetailLiveData = new MutableLiveData<>();
        detailLiveData = new MutableLiveData<>();
        CompletableFuture<List<MovieLunBo>> lunbos = MovieTask.getLunBo("http://124.93.196.45:10001/prod-api/api/movie/rotation/list");
        lunbos.thenAccept(lunBoLiveData::postValue);
        CompletableFuture<List<MovieDetail>> details = MovieTask.getList("http://124.93.196.45:10001/prod-api/api/movie/film/list");
        details.thenAccept(listDetailLiveData::postValue);
    }

    public MutableLiveData<List<MovieLunBo>> getLunBoLiveData(){return lunBoLiveData;}
    public MutableLiveData<List<MovieDetail>> getListDetailLiveData(){return listDetailLiveData;}
    public MutableLiveData<MovieDetail> getDetailLiveData(){return detailLiveData;}

    public void setListDetailLiveData(String msg){
        List<MovieDetail> value = listDetailLiveData.getValue();
        List<MovieDetail> collect = value.stream().filter(movieDetail -> movieDetail.getName().contains(msg)).collect(Collectors.toList());
        listDetailLiveData.setValue(collect);
    }

    public void setDetailLiveData(int id){
        List<MovieDetail> value = listDetailLiveData.getValue();
        for (MovieDetail detail : value) {
            if(detail.getId() == id){
                detailLiveData.setValue(detail);
                break;
            }
        }
    }

}
