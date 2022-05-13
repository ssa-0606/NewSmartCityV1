package com.imikasa.metro.pojo;

public class MetroLine {

    private int lineId;
    private String lineName;
    private int reachTime;
    private String nextStep;

    public int getLineId() {
        return lineId;
    }

    @Override
    public String toString() {
        return "MetroLine{" +
                "lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                ", reachTime=" + reachTime +
                ", nextStep='" + nextStep + '\'' +
                '}';
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getReachTime() {
        return reachTime;
    }

    public void setReachTime(int reachTime) {
        this.reachTime = reachTime;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public MetroLine(int lineId, String lineName, int reachTime, String nextStep) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.reachTime = reachTime;
        this.nextStep = nextStep;
    }
}
