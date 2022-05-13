package com.imikasa.metro.pojo;

import java.util.List;

public class MetroStation {

    private int id;
    private String name;
    private String first;
    private String end;
    private int remainingTime;
    private int stationsNumber;
    private int km;
    private String runStationsName;
    private List<LineStation> lineStations;

    public MetroStation() {
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

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getStationsNumber() {
        return stationsNumber;
    }

    public void setStationsNumber(int stationsNumber) {
        this.stationsNumber = stationsNumber;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getRunStationsName() {
        return runStationsName;
    }

    public void setRunStationsName(String runStationsName) {
        this.runStationsName = runStationsName;
    }

    public List<LineStation> getLineStations() {
        return lineStations;
    }

    public void setLineStations(List<LineStation> lineStations) {
        this.lineStations = lineStations;
    }

    public MetroStation(int id, String name, String first, String end, int remainingTime, int stationsNumber, int km, String runStationsName, List<LineStation> lineStations) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.end = end;
        this.remainingTime = remainingTime;
        this.stationsNumber = stationsNumber;
        this.km = km;
        this.runStationsName = runStationsName;
        this.lineStations = lineStations;
    }
}
