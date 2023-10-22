package com.example.bt7_navigation_fragment;

import android.content.Context;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_devices extends RecyclerView.Adapter<myViewHolder> {

    private Context context;
    private List<Device> list_Device;
    private SelectListener listener;
    private Adapter_devices adapterDevices;

    public Adapter_devices(Context context, List<Device> list_Device, SelectListener listener) {
        this.context = context;
        this.list_Device = list_Device;
        this.listener = listener;
    }
    // Inflate the layout for a device and create a ViewHolder
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_layout,parent,false);
        return new myViewHolder(view);
    }
    // Bind data to the ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        // Set device name and image.
        holder.txt_name_device.setText(list_Device.get(position).getName());
        holder.img_device.setImageResource(list_Device.get(position).getImg());
        final Device device  = list_Device.get(position);
        if(device == null){
            return;
        }
        // Handle clicks on the device card.
        holder.card_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(device);
            }
        });
    }
    // Return the total number of items in the list.
    @Override
    public int getItemCount() {
        return list_Device.size();
    }
}
