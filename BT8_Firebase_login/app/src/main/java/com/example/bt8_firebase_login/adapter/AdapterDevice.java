package com.example.bt8_firebase_login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt8_firebase_login.Interface.SelectListener;
import com.example.bt8_firebase_login.R;
import com.example.bt8_firebase_login.object.Device;

import java.util.List;

public class AdapterDevice extends RecyclerView.Adapter<ViewHolderDevice> {

    private final Context context;
    private final List<Device> devices;
    private final SelectListener listener;
    private AdapterDevice adapterDevices;

    public AdapterDevice(Context context, List<Device> devices, SelectListener listener) {
        this.context = context;
        this.devices = devices;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderDevice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_layout,parent,false);
        return new ViewHolderDevice(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDevice holder, int position) {
        holder.txt_name_device.setText(devices.get(position).getName());
        holder.img_device.setImageResource(devices.get(position).getImg());
        final Device device  = devices.get(position);
        if(device == null){
            return;
        }
        holder.card_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(device);
            }
        });
    }


    @Override
    public int getItemCount() {
        return devices.size();
    }
}
