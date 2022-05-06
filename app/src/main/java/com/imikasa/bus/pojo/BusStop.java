package com.imikasa.bus.pojo;

public class BusStop {
    private int linesId;
    private int stepsId;
    private String name;

    @Override
    public String toString() {
        return "BusStop{" +
                "linesId=" + linesId +
                ", stepsId=" + stepsId +
                ", name='" + name + '\'' +
                '}';
    }

    public int getLinesId() {
        return linesId;
    }

    public void setLinesId(int linesId) {
        this.linesId = linesId;
    }

    public int getStepsId() {
        return stepsId;
    }

    public void setStepsId(int stepsId) {
        this.stepsId = stepsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BusStop(int linesId, int stepsId, String name) {
        this.linesId = linesId;
        this.stepsId = stepsId;
        this.name = name;
    }
}
