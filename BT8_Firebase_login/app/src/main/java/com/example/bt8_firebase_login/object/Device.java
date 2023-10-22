package com.example.bt8_firebase_login.object;

import java.io.Serializable;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Device implements Serializable {
    String name;
    String des;
    int img;
    public Device() {
        // Default constructor with no arguments (required by Firebase)
    }
    public Device(String name, String des, int img) {
        this.name = name;
        this.des = des;
        this.img = img;
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
