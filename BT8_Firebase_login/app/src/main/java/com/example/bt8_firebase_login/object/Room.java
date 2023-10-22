package com.example.bt8_firebase_login.object;

import java.io.Serializable;
import java.util.List;
// bug in firebase
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Room implements Serializable {
    String name ;
    String des;
    int img;
    boolean is_favor;
    List<Device>devices;
    public Room() {
        // Default constructor with no arguments (required by Firebase)
    }

    public Room(String name, String des, int img, List<Device> devices , boolean is_favor) {
        this.name = name;
        this.des = des;
        this.img = img;
        this.devices = devices;
        this.is_favor = is_favor;
    }

    public boolean isIs_favor() {
        return is_favor;
    }

    public void setIs_favor(boolean is_favor) {
        this.is_favor = is_favor;
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

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
