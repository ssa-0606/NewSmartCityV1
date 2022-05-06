package com.imikasa.bus.pojo;

public class BusLine {
    private int id;
    private String name;
    private String first;
    private String end;
    private String startTime;
    private String endTime;
    private double price;
    private String mileage;

    public BusLine(int id, String name, String first, String end, String startTime, String endTime, double price, String mileage) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
