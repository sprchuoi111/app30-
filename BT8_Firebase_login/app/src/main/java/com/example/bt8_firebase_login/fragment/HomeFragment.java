package com.example.bt8_firebase_login.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bt8_firebase_login.Interface.SelectListener;
import com.example.bt8_firebase_login.MainActivity;
import com.example.bt8_firebase_login.R;
import com.example.bt8_firebase_login.adapter.AdapterRoom;
import com.example.bt8_firebase_login.object.Device;
import com.example.bt8_firebase_login.object.Room;

import java.util.ArrayList;
import java.util.List;

// import lib of firebase
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment {


    private  List<Device> devices , list_device_livingroom ,list_device_bedroom , list_device_bathroom ;

    private AdapterRoom adapterRooms;
    private RecyclerView recyclerView_rooms;
    private MainActivity mMainActivity;
    private View mView;
    // Initialize Firebase
    private FirebaseDatabase database;
    private DatabaseReference rootRef;

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

        // set data base of room
        mMainActivity = (MainActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        // set type of Recycler View
        // GridLayoutManager gridLayoutManager = new GridLayoutManager(mMainActivity, 2 );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        // Casting
        recyclerView_rooms = mView.findViewById(R.id.recyclerView_rooms);
        recyclerView_rooms.setLayoutManager(linearLayoutManager);
        recyclerView_rooms.setHasFixedSize(true);

        //push data in firebase
        database = FirebaseDatabase.getInstance();
        rootRef = database.getReference();



        // Inflate the layout for this fragment

        return mView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //set Room Adapter
        AdapterRoom adapterRooms = new AdapterRoom(mMainActivity, data_rooms(), new SelectListener() {
            @Override
            public void onItemClicked(Room room) {
                mMainActivity.goto_Device_Fragment(room);
            }

            @Override
            public void onItemClicked(Device device) {

            }
        });
        recyclerView_rooms.setAdapter(adapterRooms);
        // push room to firebase
        pushRoomsToDatabase();

        // get room data in firebase
        getRoomsFromDatabase();

    }
    // data Room
    public List<Room> data_rooms(){
        List<Room> rooms = new ArrayList<>();
        list_device_livingroom = create_Devices_Livingroom();
        list_device_bathroom = create_Devices_Bathroom();
        list_device_bedroom = create_Devices_Bedroom();
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
    // data Device
    public List<Device> create_Devices_Bedroom(){
        devices =new ArrayList<>();
        devices.add(new Device("LED1","This is led 1",R.drawable.led));
        devices.add(new Device("LED2","This is led 2",R.drawable.led));
        devices.add(new Device("LED3","This is led 3",R.drawable.led));
        devices.add(new Device("Air Conditioner","This is Air Conditioner",R.drawable.airconditioner));
        devices.add(new Device("LED4","This is led 4",R.drawable.led));
        pushDevicesToDatabase(devices, "Bedroom");
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
        pushDevicesToDatabase(devices, "Bedroom");
        return devices;
    }
    public List<Device> create_Devices_Bathroom(){
        devices =new ArrayList<>();
        devices.add(new Device("LED1","This is led 1",R.drawable.led));
        devices.add(new Device("LED2","This is led 2",R.drawable.led));
        devices.add(new Device("LED3","This is led 3",R.drawable.led));
        devices.add(new Device("Water Heater","This is Water Heater",R.drawable.waterheater));
        pushDevicesToDatabase(devices, "Bathroom");
        return devices;
    }
    public void pushRoomsToDatabase() {
        List<Room> rooms = data_rooms();
        DatabaseReference roomsRef = rootRef.child("rooms");

        for (int i = 0; i < rooms.size(); i++) {
            roomsRef.child("room" + i).setValue(rooms.get(i));
        }
    }
    public void pushDevicesToDatabase(List<Device> devices, String roomName) {
        DatabaseReference devicesRef = rootRef.child("devices").child(roomName);

        for (int i = 0; i < devices.size(); i++) {
            devicesRef.child("device" + i).setValue(devices.get(i));
        }
    }

    // Retrieve data from Firebase
    public void getRoomsFromDatabase() {
        DatabaseReference roomsRef = rootRef.child("rooms");

        roomsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Room> rooms = new ArrayList<>();
                for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
                    Room room = roomSnapshot.getValue(Room.class);
                    if (room != null) {
                        rooms.add(room);
                    }
                }

                // Update the UI with the retrieved data
                updateUIWithRooms(rooms);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors here
            }
        });
    }

    // Update the UI with the retrieved data
    private void updateUIWithRooms(List<Room> rooms) {
        AdapterRoom adapterRooms = new AdapterRoom(mMainActivity, rooms, new SelectListener() {
            @Override
            public void onItemClicked(Room room) {
                mMainActivity.goto_Device_Fragment(room);
            }

            @Override
            public void onItemClicked(Device device) {
                // Handle device clicks
            }
        });

        recyclerView_rooms.setAdapter(adapterRooms);
    }
}