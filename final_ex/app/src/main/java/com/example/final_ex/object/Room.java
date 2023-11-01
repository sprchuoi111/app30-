package com.example.final_ex.object;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Room implements Serializable { // get bundle data
    private String name;
    private List<Device> listDevice;

    public Room(String name, List<Device> listDevice) {
        this.name = name;
        this.listDevice = listDevice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // function add device
    public void addDevice(Device dv){
        listDevice.add(dv);
    }

    // function remove device

    public void removeDevice(int index){
        listDevice.remove(index);
    }

    public int getNumber(){
        return listDevice.size();
    }

    public List<Device> getListDevice() {
        return listDevice;
    }

    public void setListDevice(List<Device> listDevice) {
        this.listDevice = listDevice;
    }
    //define for any access list room
    public static List<Room> globalRooms = new ArrayList<>();
    public static List<Device> default_device()
    {
        List<Device> globalDevices = new ArrayList<>();
        globalDevices.add(0, new Device("LED 1",false));
        globalDevices.add(1, new Device("LED 2",false));
        globalDevices.add(2, new Device("LED 3",false));
        return globalDevices;
    }


}
