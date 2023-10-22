package com.example.bt6_recyclerview;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter_devices extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<Devices> list_device;
    private SelectListener listener;

    public MyAdapter_devices(Context context, List<Devices> list_device, SelectListener listener) {
        this.context = context;
        this.list_device = list_device;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.img_device.setImageResource(list_device.get(position).getImg());
        holder.name_device.setText(list_device.get(position).getName());
        holder.des_device.setText(list_device.get(position).getDescription());
        holder.card_devices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(list_device.get(position));
            }
        });

        // get position of device when click on card device
        holder.card_devices.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getAdapterPosition();
                if( position != RecyclerView.NO_POSITION)
                    listener.onItemLongClicked(position);
                return true;
            }
        });
        //btn popup menu room
        holder.btn_popup_menu_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popup_menu_device;
                popup_menu_device = new PopupMenu(context,holder.btn_popup_menu_device);
                // Inflating popup menu from popup_menu_room.xml file
                popup_menu_device.getMenuInflater().inflate(R.menu.popup_menu_room, popup_menu_device.getMenu());
                //processing popup menu of device when click on the icon
                popup_menu_device.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(context,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        int position = holder.getAdapterPosition();
                        if( position != RecyclerView.NO_POSITION)
                            listener.onItemClicked(position);
                        return true;
                    }
                });
                //showing popup menu
                popup_menu_device.show();
            }
        });

    }
    // return size of device in room
    @Override
    public int getItemCount() {
        return list_device.size();
    }
}
