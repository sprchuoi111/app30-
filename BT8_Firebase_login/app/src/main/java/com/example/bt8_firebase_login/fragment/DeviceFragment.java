package com.example.bt8_firebase_login.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bt8_firebase_login.Interface.SelectListener;
import com.example.bt8_firebase_login.MainActivity;
import com.example.bt8_firebase_login.R;
import com.example.bt8_firebase_login.adapter.AdapterDevice;
import com.example.bt8_firebase_login.adapter.AdapterRoom;
import com.example.bt8_firebase_login.object.Device;
import com.example.bt8_firebase_login.object.Room;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment {
    public static final String TAG = DeviceFragment.class.getName();
    private ImageView img_room_device;

    private TextView txt_name_room_device , txt_des_room_device;

    private List<Device> list_devices;

    private RecyclerView recyclerView_devices;
    private MainActivity mMainActivity;
    private View mView;
    private Button btn_back_device_room;

    public DeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // set data base of room
        mMainActivity = (MainActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_device, container, false);
        // set type of Recycler View
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mMainActivity, 2 );
        // Casting
        recyclerView_devices = mView.findViewById(R.id.recyclerView_devices);
        recyclerView_devices.setLayoutManager(gridLayoutManager);
        recyclerView_devices.setHasFixedSize(true);

        // Inflate the layout for this fragment
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_room_device = view.findViewById(R.id.img_room_device);
        txt_des_room_device = view.findViewById(R.id.txt_des_room_device);
        txt_name_room_device = view.findViewById(R.id.txt_name_room_device);



        // get bundle From Fragment room
        Bundle bundleReceiver = getArguments();
        if(bundleReceiver != null){
            Room room = (Room) bundleReceiver.get("room");
            if (room != null){
                img_room_device.setImageResource(room.getImg());
                txt_des_room_device.setText(room.getDes());
                txt_name_room_device.setText(room.getName());
                // set adapter
                // create List device
                List<Device> deviceList = new ArrayList<>();
                deviceList = room.getDevices();
                if(deviceList != null){
                    AdapterDevice adapterDevices = new AdapterDevice(mMainActivity,deviceList , new SelectListener() {
                        @Override
                        public void onItemClicked(Room room) {

                        }

                        @Override
                        public void onItemClicked(Device device) {
                            Toast.makeText(mMainActivity , device.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView_devices.setAdapter(adapterDevices);
                }
                else Toast.makeText(mMainActivity,"No data in array",Toast.LENGTH_SHORT).show();
            }
        }
        // favor button
        // Back Button
        btn_back_device_room = view.findViewById(R.id.btn_back_device_room);
        btn_back_device_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // back to previous fragment from stack
                getFragmentManager().popBackStack();
            }
        });
    }
}