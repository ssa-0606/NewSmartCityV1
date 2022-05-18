package com.imikasa.house.pojo;

public class House {

    private int id;
    private String sourceName;
    private String address;
    private String areaSize;
    private String tel;
    private String price;
    private String houseType;
    private String pic;
    private String description;

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", sourceName='" + sourceName + '\'' +
                ", address='" + address + '\'' +
                ", areaSize='" + areaSize + '\'' +
                ", tel='" + tel + '\'' +
                ", price='" + price + '\'' +
                ", houseType='" + houseType + '\'' +
                ", pic='" + pic + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(String areaSize) {
        this.areaSize = areaSize;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public House(int id, String sourceName, String address, String areaSize, String tel, String price, String houseType, String pic, String description) {
        this.id = id;
        this.sourceName = sourceName;
        this.address = address;
        this.areaSize = areaSize;
        this.tel = tel;
        this.price = price;
        this.houseType = houseType;
        this.pic = pic;
        this.description = description;
    }
}
