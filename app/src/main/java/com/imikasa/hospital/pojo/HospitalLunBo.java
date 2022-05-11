package com.imikasa.hospital.pojo;

public class HospitalLunBo {
    private int id;
    private String imgUrl;
    private int hospitalId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public HospitalLunBo(int id, String imgUrl, int hospitalId) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.hospitalId = hospitalId;
    }
}
