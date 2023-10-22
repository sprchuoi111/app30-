package com.example.bt7_navigation_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment {
    public static final String TAG = DeviceFragment.class.getName();

    private ImageView img_room_device;
    private TextView txt_name_room_device, txt_des_room_device;
    private List<Device> list_devices;
    private RecyclerView recyclerView_devices;
    private MainActivity mMainActivity;
    private View mView;
    private Button btn_back_device_room;
    private ViewPager2 mViewPager;

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
        // Initialize the fragment's view and RecyclerView.
        mMainActivity = (MainActivity) getActivity(); // Get a reference to the MainActivity.
        mView = inflater.inflate(R.layout.fragment_device, container, false); // Inflate the fragment's layout.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mMainActivity, 2); // Create a GridLayoutManager with 2 columns.
        recyclerView_devices = mView.findViewById(R.id.recyclerView_devices); // Find the RecyclerView in the layout.
        recyclerView_devices.setLayoutManager(gridLayoutManager); // Set the layout manager for the RecyclerView.
        recyclerView_devices.setHasFixedSize(true); // Optimize for fixed content size.
        return mView; // Return the fragment's view.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Find and initialize views in the fragment layout.
        img_room_device = view.findViewById(R.id.img_room_device);
        txt_des_room_device = view.findViewById(R.id.txt_des_room_device);
        txt_name_room_device = view.findViewById(R.id.txt_name_room_device);

        // Retrieve data from the bundle passed from the RoomFragment.
        Bundle bundleReceiver = getArguments();
        if (bundleReceiver != null) {
            Room room = (Room) bundleReceiver.get("room"); // Get the Room object from the bundle.
            if (room != null) {
                // Set the image, description, and name of the room.
                img_room_device.setImageResource(room.getImg());
                txt_des_room_device.setText(room.getDes());
                txt_name_room_device.setText(room.getName());

                // Set up the RecyclerView adapter with a list of devices in the room.
                List<Device> deviceList = room.getList_device();
                if (deviceList != null) {
                    Adapter_devices adapterDevices = new Adapter_devices(mMainActivity, deviceList, new SelectListener() {
                        @Override
                        public void onItemClicked(Room room) {
                            // Handle item click for a room (not implemented in this code).
                        }

                        @Override
                        public void onItemClicked(Device device) {
                            // Display a toast message when a device is clicked.
                            Toast.makeText(mMainActivity, device.getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView_devices.setAdapter(adapterDevices); // Set the adapter for the RecyclerView.
                } else {
                    // Show a toast message if there is no data in the device list.
                    Toast.makeText(mMainActivity, "No data in array", Toast.LENGTH_SHORT).show();
                }
            }
        }

        // Set up the back button to return to the previous fragment from the stack.
        btn_back_device_room = view.findViewById(R.id.btn_back_device_room);
        btn_back_device_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack(); // Navigate back to the previous fragment.
            }
        });
    }
}
