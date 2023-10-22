package com.example.bt8_firebase_login.Interface;

import com.example.bt8_firebase_login.object.Device;
import com.example.bt8_firebase_login.object.Room;

public interface SelectListener {
    void onItemClicked(Room room);
    void onItemClicked(Device device);
}
