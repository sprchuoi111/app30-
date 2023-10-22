package com.example.final_ex.object;

public class Device {
    private String name;
    private int img;
    private boolean status;
    // constructor

    public Device(String name, int img, boolean status) {
        this.name = name;
        this.img  = img;
        this.status = status;
    }

    public int getImg() {
        return img;
    }

    public int setImg(int img) {
        this.img = img;
        return  img;
    }

    public Device(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    // get & set function

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
