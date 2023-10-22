package com.example.bt8_firebase_login.adapter;

import android.bluetooth.le.ScanSettings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt8_firebase_login.R;

public class ViewHolderRoom  extends RecyclerView.ViewHolder{
    // Initialize Room
    ImageView img_room;
    TextView txt_name_room , txt_des_room;
    CardView card_room;
    ToggleButton btn_favor_room;
    public ViewHolderRoom(@NonNull View itemView) {
        super(itemView);
        cast_room();

    }
    private void cast_room(){
        img_room = itemView.findViewById(R.id.img_room);
        txt_name_room = itemView.findViewById(R.id.txt_name_room);
        txt_des_room = itemView.findViewById(R.id.txt_des_room);
        card_room = itemView.findViewById(R.id.card_room);
        // favor button
        btn_favor_room = itemView.findViewById(R.id.btn_favor_room);
    }

}
