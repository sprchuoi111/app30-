package com.example.bt7_navigation_fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_rooms extends RecyclerView.Adapter<myViewHolder> {
    private Context context;
    private List<Room> list_rooms;
    private SelectListener listener;

    public Adapter_rooms(Context context, List<Room> list_rooms, SelectListener listener) {
        this.context = context;
        this.list_rooms = list_rooms;
        this.listener = listener;
    }

    // Inflate the layout for a room and create a ViewHolder.
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_layout, parent, false);
        return new myViewHolder(view);
    }

    // Bind data to the ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Room room = list_rooms.get(position);

        // Set room name, description, and image.
        holder.txt_name_room.setText(room.getName());
        holder.txt_des_room.setText(room.getDes());
        holder.img_room.setImageResource(room.getImg());

        // Handle clicks on the room card.
        holder.card_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(room);
            }
        });

        // Handle changes in the favor button state.
        holder.btn_favor_room.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                listener.onItemClicked(room);
            }
        });
    }

    // Return the total number of items in the list.
    @Override
    public int getItemCount() {
        return list_rooms.size();
    }
}
