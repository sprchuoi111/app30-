package com.example.bt7_navigation_fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<Room> rooms; // List to store Room objects.
    private List<Device> devices, list_device_livingroom, list_device_bedroom, list_device_bathroom; // Lists to store Device objects.

    private Adapter_rooms adapterRooms; // Adapter for Room data.
    private RecyclerView recyclerView_rooms; // RecyclerView for displaying rooms.
    private MainActivity mMainActivity; // Reference to the MainActivity.
    private View mView; // View for this fragment.
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize the fragment's view and RecyclerView.
        mMainActivity = (MainActivity) getActivity(); // Get a reference to the MainActivity.
        mView = inflater.inflate(R.layout.fragment_home, container, false); // Inflate the fragment's layout.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        recyclerView_rooms = mView.findViewById(R.id.recyclerView_rooms); // Find the RecyclerView in the layout.
        recyclerView_rooms.setLayoutManager(linearLayoutManager); // Set the layout manager for the RecyclerView.
        recyclerView_rooms.setHasFixedSize(true); // Optimize for fixed content size.
        return mView; // Return the fragment's view.

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up the Room Adapter and handle item clicks.
        Adapter_rooms adapterRooms = new Adapter_rooms(mMainActivity, data_rooms(), new SelectListener() {
            @Override
            public void onItemClicked(Room room) {
                // Handle a room item click by navigating to a DeviceFragment.
                mMainActivity.goto_Device_Fragment(room);
            }

            @Override
            public void onItemClicked(Device device) {
                // Handle a device item click (not implemented in this code).
            }
        });
        recyclerView_rooms.setAdapter(adapterRooms); // Set the adapter for the RecyclerView.
    }


    public List<Room> data_rooms(){
        // Create Room objects and populate the list.
        List<Room> rooms = new ArrayList<>();
        list_device_livingroom = create_Devices_Livingroom();
        list_device_bathroom = create_Devices_Bathroom();
        list_device_bedroom = create_Devices_Bedroom();
        // Create Room objects and populate the list.
        rooms.add(new Room("Living Room 1","This is Living room 1", R.drawable.livingroom , list_device_livingroom,false));
        rooms.add(new Room("Bed Room 1","This is Bed room 1",R.drawable.bedroom, list_device_bedroom,false));
        rooms.add(new Room("Bath Room 1 ","This is Bath room 1",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 2 ","This is Bath room 2",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 3 ","This is Bath room 3",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 4 ","This is Bath room 4",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Living Room 2","This is Living room 2", R.drawable.livingroom, list_device_livingroom,false));
        rooms.add(new Room("Bed Room 2","This is Bed room 2",R.drawable.bedroom, list_device_bedroom,false));
        rooms.add(new Room("Bath Room 5 ","This is Bath room 5",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 6 ","This is Bath room 6",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 7 ","This is Bath room 7",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 8 ","This is Bath room 8",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Living Room 3","This is Living room 3", R.drawable.livingroom, list_device_livingroom,false));
        rooms.add(new Room("Bed Room 1","This is Bed room 1",R.drawable.bedroom, list_device_bedroom,false));
        rooms.add(new Room("Bath Room 9 ","This is Bath room 9",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 10 ","This is Bath room 10",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 11 ","This is Bath room 11",R.drawable.bathroom, list_device_bathroom,false));
        rooms.add(new Room("Bath Room 12 ","This is Bath room 12",R.drawable.bathroom, list_device_bathroom,false));
        return rooms;
    }
    // Add Device objects to the list.
    public List<Device> create_Devices_Bedroom(){
        devices =new ArrayList<>();
        devices.add(new Device("LED1","This is led 1",R.drawable.led));
        devices.add(new Device("LED2","This is led 2",R.drawable.led));
        devices.add(new Device("LED3","This is led 3",R.drawable.led));
        devices.add(new Device("Air Conditioner","This is Air Conditioner",R.drawable.airconditioner));
        devices.add(new Device("LED4","This is led 4",R.drawable.led));
        return devices;
    }
    public List<Device> create_Devices_Livingroom(){
        devices =new ArrayList<>();
        devices.add(new Device("LED1","This is led 1",R.drawable.led));
        devices.add(new Device("LED2","This is led 2",R.drawable.led));
        devices.add(new Device("LED3","This is led 3",R.drawable.led));
        devices.add(new Device("LED4","This is led 4",R.drawable.led));
        devices.add(new Device("Air Conditioner 1","This is Air Conditioner 1",R.drawable.airconditioner));
        devices.add(new Device("Air Conditioner 2","This is Air Conditioner 2",R.drawable.airconditioner));
        return devices;
    }
    public List<Device> create_Devices_Bathroom(){
        devices =new ArrayList<>();
        devices.add(new Device("LED1","This is led 1",R.drawable.led));
        devices.add(new Device("LED2","This is led 2",R.drawable.led));
        devices.add(new Device("LED3","This is led 3",R.drawable.led));
        devices.add(new Device("Water Heater","This is Water Heater",R.drawable.waterheater));
        return devices;
    }

}