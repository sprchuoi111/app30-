package com.example.bt6_recyclerview;

import java.lang.reflect.Array;
import java.util.List;

public class Rooms {
    String name;
    String des;
    int img;
    List<Devices> list_device;

    public Rooms(String name, String des, int img ,List<Devices> list_device) {
        this.name = name;
        this.des = des;
        this.img = img;
        this.list_device = list_device;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public List<Devices> getList_device() {
        return list_device;
    }

    public void setList_device(List<Devices> list_device) {
        this.list_device = list_device;
    }
}
