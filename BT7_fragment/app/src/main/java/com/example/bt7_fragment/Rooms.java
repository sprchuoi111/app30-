package com.example.bt7_fragment;

public class Rooms {
    int img;
    String name;
    String Des;

    public Rooms(int img, String name, String des) {
        this.img = img;
        this.name = name;
        Des = des;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }
}
