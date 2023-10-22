package com.example.bt8_firebase_login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt8_firebase_login.Interface.SelectListener;
import com.example.bt8_firebase_login.R;
import com.example.bt8_firebase_login.object.Device;
import com.example.bt8_firebase_login.object.Room;

import java.util.List;

public class AdapterRoom extends RecyclerView.Adapter<ViewHolderRoom> {
    private final Context context;
    private final List<Room> rooms;
    private List<Device> devices;
    private final SelectListener listener;

    public AdapterRoom(Context context, List<Room> rooms, SelectListener listener) {
        this.context = context;
        this.rooms = rooms;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderRoom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_layout,parent,false);
        return new ViewHolderRoom(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRoom holder, int position) {
        holder.txt_name_room.setText(rooms.get(position).getName());
        holder.txt_des_room.setText(rooms.get(position).getDes());
        holder.img_room.setImageResource(rooms.get(position).getImg());
        final Room room  = rooms.get(position);
        if(room == null){
            return;
        }
        holder.card_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(room);
            }
        });
        holder.btn_favor_room.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listener.onItemClicked(room);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
}
