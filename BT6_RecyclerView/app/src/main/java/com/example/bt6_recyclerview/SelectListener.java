package com.example.bt6_recyclerview;

public interface SelectListener {
    void onItemClicked(Rooms rooms);
    void onItemClicked(Devices devices);

    void onItemLongClicked(Rooms rooms);
    void onItemLongClicked(Devices devices);
    void onItemLongClicked(int position);
    void onItemClicked(int position);
}
