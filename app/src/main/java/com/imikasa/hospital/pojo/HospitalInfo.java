package com.imikasa.hospital.pojo;

public class HospitalInfo {
    private int id;
    private String hospitalName;
    private String brief;
    private int level;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public HospitalInfo(int id, String hospitalName, String brief, int level, String imgUrl) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.brief = brief;
        this.level = level;
        this.imgUrl = imgUrl;
    }
}
