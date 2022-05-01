package com.imikasa.ui.home.pojo;

public class MainService {

    private int id;
    private String serviceName;
    private String serviceType;
    private String imgUrl;
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public MainService(int id, String serviceName, String serviceType, String imgUrl, int sort) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.imgUrl = imgUrl;
        this.sort = sort;
    }
}
