package com.example.bt8_firebase_login.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt8_firebase_login.R;

public class ViewHolderDevice extends RecyclerView.ViewHolder {
    // Initialize device

    ImageView img_device;
    TextView txt_name_device;
    CardView card_device;

    ToggleButton btn_favor_room;
    public ViewHolderDevice(@NonNull View itemView) {
        super(itemView);
        cast_device();
    }
    private  void cast_device(){
        img_device = itemView.findViewById(R.id.img_device);
        txt_name_device = itemView.findViewById(R.id.txt_name_device);
        card_device = itemView.findViewById(R.id.card_device);
    }
}
