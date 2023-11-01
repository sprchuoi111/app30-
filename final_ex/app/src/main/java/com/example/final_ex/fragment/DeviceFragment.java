package com.example.final_ex.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_ex.MainActivity;
import com.example.final_ex.R;
import com.example.final_ex.object.Device;
import com.example.final_ex.object.Room;
import com.example.final_ex.viewholder.DeviceAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DeviceFragment extends Fragment {
    // Tạo các biến để tham chiếu đến các phần tử ở layout
// Create variables to reference elements in the layout.
    private RecyclerView rcvDevice; // Reference to the RecyclerView for displaying devices.
    private TextView tvNameRoomDv; // Reference to a TextView for displaying the room name.
    private FloatingActionButton btnAddDv; // Reference to a button for adding a device.
    private FloatingActionButton btnRemoveDv; // Reference to a button for removing a device.
    private String NameRinDv; // A string to store the name of the room.
    private int index; // An integer to store the index of the room.
    private Toolbar toolbar_device; // Reference to a toolbar.
    private EditText edt_remove_device; // Reference to an EditText for removing a device.
    private DeviceAdapter deviceAdapter; // An adapter for populating data in the RecyclerView.
    private MainActivity mainActivity; // Reference to the main activity.
    public static final String TAG = DeviceFragment.class.getName(); // A constant TAG for logging.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Reference elements in the layout.
        tvNameRoomDv = view.findViewById(R.id.tvNameRoomDv);
        btnAddDv = view.findViewById(R.id.btnAddDv);
        btnRemoveDv = view.findViewById(R.id.btnRemoveDv);
        rcvDevice = view.findViewById(R.id.rcvDevice);
        mainActivity = (MainActivity) getContext();
        toolbar_device = view.findViewById(R.id.toolbar_device);
        edt_remove_device = view.findViewById(R.id.edt_remove_device);

        // Set the layout manager for the RecyclerView.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rcvDevice.setLayoutManager(gridLayoutManager);

        // Get data from the bundle passed from the previous fragment.
        Bundle bundleReceiver = getArguments();
        if (bundleReceiver != null) {
            NameRinDv = bundleReceiver.getString("NameRoom", ""); // Get the room name.
            index = bundleReceiver.getInt("index", 0); // Get the room index.
        }

        // Display the room name in a TextView.
        tvNameRoomDv.setText(NameRinDv);

        // Create a device adapter for the RecyclerView.
        deviceAdapter = new DeviceAdapter(Room.globalRooms.get(index).getListDevice());
        rcvDevice.setAdapter(deviceAdapter);

        // Set click listeners for "Add Device" and "Remove Device" buttons.
        btnAddDv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogToAddDevice(); // Show a dialog to add a device.
            }
        });

        btnRemoveDv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogToRemoveDevice(); // Show a dialog to remove a device.
            }
        });
    }

    // Method to show a dialog for adding a device.
    private void showDiaLogToAddDevice() {
        // Dialog to input device name and add it to the selected room.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View diaLogView = getLayoutInflater().inflate(R.layout.dialog_add_device, null);
        builder.setView(diaLogView);
        builder.setTitle("Add Device");
        builder.setIcon(R.drawable.baseline_add_home_24);
        final EditText edt_add_Device = diaLogView.findViewById(R.id.edt_add_Device);

        builder.setPositiveButton("Add Device", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Add a new device to the selected room.
                String DeviceName =  edt_add_Device.getText().toString();
                boolean existed  = false;
                for (int i = 0; i < Room.globalRooms.get(index).getNumber(); i++) {
                    if (Room.globalRooms.get(index).getListDevice().get(i).getName().equals(DeviceName)) {
                        Toast.makeText(getActivity(), "This Device is already added", Toast.LENGTH_SHORT).show();
                        existed = true;
                        break;
                    }
                }
                if (!existed) {
                    Room.globalRooms.get(index).addDevice(new Device(edt_add_Device.getText().toString(), false));
                    deviceAdapter.notifyDataSetChanged();
                    mainActivity.saveRoomList(Room.globalRooms, "listRoom");
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to show a dialog for removing a device.
    private void showDiaLogToRemoveDevice() {
        // Dialog to select and remove a device from the room.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View diaLogView = getLayoutInflater().inflate(R.layout.dialog_remove_device, null);
        builder.setView(diaLogView);
        builder.setTitle("Remove Device");
        builder.setIcon(R.drawable.baseline_add_24);
        final EditText edt_remove_device = diaLogView.findViewById(R.id.edt_remove_device);

        builder.setPositiveButton("Remove Device", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Remove the selected device from the room.
                String deviceRemove = edt_remove_device.getText().toString();
                for (int i = 0; i < Room.globalRooms.get(index).getNumber(); i++) {
                    if (Room.globalRooms.get(index).getListDevice().get(i).getName().equals(deviceRemove)) {
                        Room.globalRooms.get(index).removeDevice(i);
                        deviceAdapter.notifyDataSetChanged();
                        mainActivity.saveRoomList(Room.globalRooms, "listRoom");
                        break;
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Set up the toolbar with a back button for navigation.
    public void setBackButtonOnToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar_device);

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setTitle("Device list");
        }

        // Set a click listener on the back button to navigate back.
        toolbar_device.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                Toast.makeText(getContext(), "Room", Toast.LENGTH_SHORT).show();
            }
        });
    }


}