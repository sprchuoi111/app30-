package com.example.bt6_recyclerview;

import android.media.TimedText;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView img_room , frame_img_room , img_device;
    TextView room_name, des , frame_name_room , frame_des_room , name_device, des_device;
    CardView card_rooms , card_devices;
    FrameLayout frame_imf_rooms;
    RecyclerView recyclerView_device;
    MyAdapter_devices myAdapter_devices;
    Button btn_popup_menu_room , btn_popup_menu_device;
    // Create holder to mapping properties
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        //casting item in room
        img_room =  itemView.findViewById(R.id.img_room);
        room_name = itemView.findViewById(R.id.room_name);
        des = itemView.findViewById(R.id.des);
        card_rooms = itemView.findViewById(R.id.card_rooms);
        recyclerView_device = itemView.findViewById(R.id.recyclerView_device);
        btn_popup_menu_room = itemView.findViewById(R.id.btn_popup_menu_room);
        //Casting item room in frame
        frame_img_room = itemView.findViewById(R.id.frame_img_room);
        frame_des_room = itemView.findViewById(R.id.frame_des_room);
        frame_imf_rooms = itemView.findViewById(R.id.frame_imf_rooms);

        //Casting item device
        name_device = itemView.findViewById(R.id.name_device);
        img_device = itemView.findViewById(R.id.img_device);
        des_device = itemView.findViewById(R.id.des_device);
        card_devices = itemView.findViewById(R.id.card_devices);
        btn_popup_menu_device = itemView.findViewById(R.id.btn_popup_menu_device);



    }

    @Override
    public void onClick(View view) {

    }
}
