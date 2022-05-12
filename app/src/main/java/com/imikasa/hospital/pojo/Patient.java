package com.imikasa.hospital.pojo;

public class Patient {
    private int id;
    private String name;
    private String cardId;
    private String tel;
    private String sex;
    private String birthday;
    private String address;
    private int userId;

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Patient(int id, String name, String cardId, String tel, String sex, String birthday, String address, int userId) {
        this.id = id;
        this.name = name;
        this.cardId = cardId;
        this.tel = tel;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.userId = userId;
    }
}
