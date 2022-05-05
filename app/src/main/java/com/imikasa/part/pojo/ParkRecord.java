package com.imikasa.part.pojo;

public class ParkRecord {

    private int id;
    private String plateNumber;
    private String entryTime;
    private String outTime;
    private String parkName;
    private String monetary;

    public ParkRecord(int id, String plateNumber, String entryTime, String outTime, String parkName, String monetary) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.entryTime = entryTime;
        this.outTime = outTime;
        this.parkName = parkName;
        this.monetary = monetary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getMonetary() {
        return monetary;
    }

    public void setMonetary(String monetary) {
        this.monetary = monetary;
    }
}
