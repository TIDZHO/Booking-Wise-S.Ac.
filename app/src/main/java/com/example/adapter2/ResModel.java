package com.example.adapter2;

public class ResModel {

    private String text1;
    private String text2;

    String name, des, key, imageId;

    public ResModel() {

    }

    public ResModel(String name, String des, String key, String imageId) {
        this.name = name;
        this.des = des;
        this.key = key;
        this.imageId = imageId;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
