package com.imikasa.part.pojo;

public class Park {
    private int id;
    private String parkName;
    private String vacancy;
    private String priceCaps;
    private String imgUrl;
    private String rates;
    private String address;
    private String distance;
    private String allPark;
    private String open;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getPriceCaps() {
        return priceCaps;
    }

    public void setPriceCaps(String priceCaps) {
        this.priceCaps = priceCaps;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAllPark() {
        return allPark;
    }

    public void setAllPark(String allPark) {
        this.allPark = allPark;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Park(int id, String parkName, String vacancy, String priceCaps, String imgUrl, String rates, String address, String distance, String allPark, String open) {
        this.id = id;
        this.parkName = parkName;
        this.vacancy = vacancy;
        this.priceCaps = priceCaps;
        this.imgUrl = imgUrl;
        this.rates = rates;
        this.address = address;
        this.distance = distance;
        this.allPark = allPark;
        this.open = open;
    }
}
