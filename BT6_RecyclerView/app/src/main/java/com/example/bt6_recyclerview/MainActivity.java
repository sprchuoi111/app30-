package com.example.bt6_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener{
    RecyclerView recyclerView_rooms , recyclerView_device;
    List<Rooms> rooms;
    List<Devices> list_device,list_device_bedroom ,list_device_bathroom ,list_device_livingroom ;
    FrameLayout frame_imf_rooms ,main_frame , frame_btn_add_device;
    ImageView frame_img_room;
    TextView frame_name_room , frame_des_room , name_device, des_device;
    Button btn_close_rooms , btn_add_device;
    ToggleButton btn_Add;
    CardView card_rooms , card_devices;

    MyAdapter_devices myAdapter_devices;
    // check_room = 1 -> living room ; =2 bedroom =3 bath room
    EditText edt_btn_add_name_device, edt_btn_add_des_device;
    int check_room =0 ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize UI components and data structures.
        setContentView(R.layout.activity_main);
        recyclerView_rooms = findViewById(R.id.recyclerView_rooms);
        // casting for rooms

        casting_Frame_Rooms();
        //get list rooms
        rooms = new ArrayList<>();
        list_device_bedroom = create_Devices_Bedroom();
        list_device_bathroom = create_Devices_Bathroom();
        list_device_livingroom = create_Devices_Livingroom();
        rooms.add(new Rooms("Living Room 1","This is Living room 1", R.drawable.livingroom , list_device_livingroom));
        rooms.add(new Rooms("Bed Room 1","This is Bed room 1",R.drawable.bedroom, list_device_bedroom));
        rooms.add(new Rooms("Bath Room 1 ","This is Bath room 1",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 2 ","This is Bath room 2",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 3 ","This is Bath room 3",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 4 ","This is Bath room 4",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Living Room 2","This is Living room 2", R.drawable.livingroom, list_device_livingroom));
        rooms.add(new Rooms("Bed Room 2","This is Bed room 2",R.drawable.bedroom, list_device_bedroom));
        rooms.add(new Rooms("Bath Room 5 ","This is Bath room 5",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 6 ","This is Bath room 6",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 7 ","This is Bath room 7",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 8 ","This is Bath room 8",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Living Room 3","This is Living room 3", R.drawable.livingroom, list_device_livingroom));
        rooms.add(new Rooms("Bed Room 1","This is Bed room 1",R.drawable.bedroom, list_device_bedroom));
        rooms.add(new Rooms("Bath Room 9 ","This is Bath room 9",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 10 ","This is Bath room 10",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 11 ","This is Bath room 11",R.drawable.bathroom, list_device_bathroom));
        rooms.add(new Rooms("Bath Room 12 ","This is Bath room 12",R.drawable.bathroom, list_device_bathroom));

        // set fix size recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false  ));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set up RecyclerView for rooms.
        recyclerView_rooms.setHasFixedSize(true);
        recyclerView_rooms.setLayoutManager(gridLayoutManager);
        recyclerView_rooms.setAdapter(new MyAdapter(getApplicationContext(),rooms,this));
        // set recyclerView for device
        GridLayoutManager gridLayoutManager_device = new GridLayoutManager(this, 2);
        recyclerView_device.setLayoutManager(gridLayoutManager_device);
        // Set up event listeners for buttons and toggle button.
        btn_close_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView_rooms.setVisibility(View.VISIBLE);
                frame_imf_rooms.setVisibility(View.GONE);
            }
        });
        btn_Add.setText(null);
        btn_Add.setTextOn(null);
        btn_Add.setTextOff(null);
        // Handle toggle button state changes.
        btn_Add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    recyclerView_device.setVisibility(View.GONE);
                    frame_btn_add_device.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView_device.setVisibility(View.VISIBLE);
                    frame_btn_add_device.setVisibility(View.GONE);
                }
            }
        });
        // Handle adding a new device.
        btn_add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( edt_btn_add_name_device.getText().toString().equals("") || edt_btn_add_des_device.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Name or Des is empty !!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Devices device= new Devices(edt_btn_add_name_device.getText().toString(),edt_btn_add_des_device.getText().toString(),R.drawable.led);
                    if(check_room == 1){
                        list_device_livingroom.add(device);
                        Toast.makeText(MainActivity.this,"Add Succeed "+edt_btn_add_name_device.getText(),Toast.LENGTH_SHORT).show();
                    }
                    else if(check_room == 2){
                        list_device_bedroom.add(device);
                        Toast.makeText(MainActivity.this,"Add Succeed "+edt_btn_add_name_device.getText(),Toast.LENGTH_SHORT).show();
                    }
                    else if(check_room == 3){
                        list_device_bathroom.add(device);
                        Toast.makeText(MainActivity.this,"Add Succeed "+edt_btn_add_name_device.getText(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    public void casting_Frame_Rooms() {
        //Casting item room in frame
        frame_img_room = findViewById(R.id.frame_img_room);
        frame_des_room = findViewById(R.id.frame_des_room);
        frame_imf_rooms = findViewById(R.id.frame_imf_rooms);
        frame_name_room = findViewById(R.id.frame_name_room);
        recyclerView_device = findViewById(R.id.recyclerView_device);
        main_frame = findViewById(R.id.main_frame);
        btn_close_rooms = findViewById(R.id.btn_close_rooms);
        btn_Add = findViewById(R.id.btn_Add);

        frame_btn_add_device = findViewById(R.id.frame_btn_add_device);

        // edit text
        edt_btn_add_name_device = findViewById(R.id.edt_btn_add_name_device);
        edt_btn_add_des_device = findViewById(R.id.edt_btn_add_des_device);

        // button in frame add btn

        btn_add_device = findViewById(R.id.btn_add_device);



    }
    public List<Devices> create_Devices_Bedroom(){
        list_device =new ArrayList<>();
        list_device.add(new Devices("LED1","This is led 1",R.drawable.led));
        list_device.add(new Devices("LED2","This is led 2",R.drawable.led));
        list_device.add(new Devices("LED3","This is led 3",R.drawable.led));
        list_device.add(new Devices("Air Conditioner","This is Air Conditioner",R.drawable.airconditioner));
        list_device.add(new Devices("LED4","This is led 4",R.drawable.led));
        return list_device;
    }
    public List<Devices> create_Devices_Livingroom(){
        list_device =new ArrayList<>();
        list_device.add(new Devices("LED1","This is led 1",R.drawable.led));
        list_device.add(new Devices("LED2","This is led 2",R.drawable.led));
        list_device.add(new Devices("LED3","This is led 3",R.drawable.led));
        list_device.add(new Devices("LED4","This is led 4",R.drawable.led));
        list_device.add(new Devices("Air Conditioner 1","This is Air Conditioner 1",R.drawable.airconditioner));
        list_device.add(new Devices("Air Conditioner 2","This is Air Conditioner 2",R.drawable.airconditioner));
        return list_device;
    }
    public List<Devices> create_Devices_Bathroom(){
        list_device =new ArrayList<>();
        list_device.add(new Devices("LED1","This is led 1",R.drawable.led));
        list_device.add(new Devices("LED2","This is led 2",R.drawable.led));
        list_device.add(new Devices("LED3","This is led 3",R.drawable.led));
        list_device.add(new Devices("Water Heater","This is Water Heater",R.drawable.waterheater));
        return list_device;
    }
    @Override
    public void onItemClicked(Rooms rooms) {
        Toast.makeText(this, rooms.getName(), Toast.LENGTH_SHORT).show();
        frame_imf_rooms.setVisibility(View.VISIBLE);
        frame_img_room.setImageResource(rooms.getImg());
        frame_name_room.setText(rooms.getName());
        frame_des_room.setText(rooms.getDes());
        recyclerView_rooms.setVisibility(View.GONE);
        String name_room = rooms.getName();
        if(name_room.contains("Living")){
            check_room = 1;
            // create adapter for device
            myAdapter_devices = new MyAdapter_devices(getApplicationContext(),list_device_livingroom,this);

        } else if (name_room.contains("Bed")) {
            check_room = 2;;
            myAdapter_devices = new MyAdapter_devices(getApplicationContext(),list_device_bedroom,this);

        } else if (name_room.contains("Bath")) {
            check_room = 3;
            myAdapter_devices = new MyAdapter_devices(getApplicationContext(),list_device_bathroom,this);
        }
        //Set adapter for device
        recyclerView_device.setAdapter(myAdapter_devices);
    }

    @Override
    public void onItemClicked(Devices devices) {
        Toast.makeText(this, devices.getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(Rooms rooms) {
        Toast.makeText(this, "Long Click",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(Devices devices) {

    }


    @Override
    public void onItemLongClicked(int position) {
        list_device.remove(position);
        myAdapter_devices.notifyItemRemoved(position);
    }

    @Override
    public void onItemClicked(int position) {

    }
    // addButton
}