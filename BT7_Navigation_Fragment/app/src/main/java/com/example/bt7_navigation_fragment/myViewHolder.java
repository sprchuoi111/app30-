package com.example.bt7_navigation_fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder {
    // Initialize Room
    ImageView img_room;
    TextView txt_name_room , txt_des_room;
    CardView card_room;
    ToggleButton btn_favor_room;
    // Initialize device

    ImageView img_device;
    TextView txt_name_device;
    CardView card_device;


    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        cast_room();
        cast_device();
    }
    // set cast for room
    private void cast_room(){
        img_room = itemView.findViewById(R.id.img_room);
        txt_name_room = itemView.findViewById(R.id.txt_name_room);
        txt_des_room = itemView.findViewById(R.id.txt_des_room);
        card_room = itemView.findViewById(R.id.card_room);
        // favor button
        btn_favor_room = itemView.findViewById(R.id.btn_favor_room);
    }
    // set cast for device
    private void cast_device(){
        img_device = itemView.findViewById(R.id.img_device);
        txt_name_device = itemView.findViewById(R.id.txt_name_device);
        card_device = itemView.findViewById(R.id.card_device);
    }
}
