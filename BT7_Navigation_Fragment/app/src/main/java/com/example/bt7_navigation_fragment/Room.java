package com.example.bt7_navigation_fragment;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable{
    String name;
    String des;
    int img ;
    List<Device> list_device;
    boolean isFavor;

    public Room(String name, String des, int img, List<Device> list_device, boolean isFavor) {
        this.name = name;
        this.des = des;
        this.img = img;
        this.list_device = list_device;
        this.isFavor = isFavor;
    }

    public boolean isFavor() {
        return isFavor;
    }

    public void setFavor(boolean favor) {
        isFavor = favor;
    }

    public List<Device> getList_device() {
        return list_device;
    }

    public void setList_device(List<Device> list_device) {
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
}
