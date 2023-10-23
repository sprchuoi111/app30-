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
    //tạo các biến để tham chiếu đến các phần tử ở layout
    private RecyclerView rcvDevice;
    private TextView tvNameRoomDv;
    private FloatingActionButton btnAddDv;
    private FloatingActionButton btnRemoveDv;
    private String NameRinDv;
    private int index;
    private Toolbar toolbar_device;
    private EditText edt_remove_device;
    private DeviceAdapter deviceAdapter;
    private MainActivity mainActivity;
    public static final String TAG = DeviceFragment.class.getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ánh xạ các phần tử vào biến tương ứng
        tvNameRoomDv = view.findViewById(R.id.tvNameRoomDv);
        btnAddDv = view.findViewById(R.id.btnAddDv);
        btnRemoveDv = view.findViewById(R.id.btnRemoveDv);
        rcvDevice = view.findViewById(R.id.rcvDevice);
        mainActivity = (MainActivity) getContext();
        toolbar_device = view.findViewById(R.id.toolbar_device);
        edt_remove_device = view.findViewById(R.id.edt_remove_device);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1); //tạo một GridLayoutManager
        rcvDevice.setLayoutManager(gridLayoutManager); //thiết lập GridLayoutManager làm LayoutManager cho RecyclerView của Device
        // get support of toolbar
        setBackButtonOnToolbar();

        // get bundle From Fragment room
        Bundle bundleReceiver = getArguments();
        if (bundleReceiver != null) {
            NameRinDv = bundleReceiver.getString("NameRoom",""); //lưu tên phòng được bundle vào biến NameRinDv (giá trị ban đầu là "")
            index = bundleReceiver.getInt("index",0); //lưu tên phòng được bundle vào biến index (giá trị ban đầu là 0)
        }

        tvNameRoomDv.setText(NameRinDv); //hiển thị tên phòng lên TextView
        deviceAdapter = new DeviceAdapter(Room.globalRooms.get(index).getListDevice()); //tạo một DeviceAdapter với danh sách thiết bị tương ứng của phòng được chọn
        rcvDevice.setAdapter(deviceAdapter); //set DeviceAdapter cho RecyclerView

        btnAddDv.setOnClickListener(new View.OnClickListener() {//tạo phương thức khi click vào nút Add Device
            @Override
            public void onClick(View v) {
                showDiaLogToAddDevice();
                //thêm thiết bị vào phần tử tương ứng trong globalRooms bằng hàm addDevice với tên lấy từ Edit Text và giá trị ban đầu của trạng thái là false
            }
        });

        btnRemoveDv.setOnClickListener(new View.OnClickListener() { //tạo sự kiện khi click vào nút Remove Device
            @Override
            public void onClick(View v) {
                showDiaLogToRemoveDevice();

                }
        });

    }
    private void showDiaLogToAddDevice(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View  diaLogView = getLayoutInflater().inflate(R.layout.dialog_add_device, null);
        builder.setView(diaLogView);
        builder.setTitle("Add Device");
        builder.setIcon(R.drawable.baseline_add_home_24);
        final EditText edt_add_Device = diaLogView.findViewById(R.id.edt_add_Device);
        builder.setPositiveButton("Add Device", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String DeviceName =  edt_add_Device.getText().toString();
                boolean exited  = false;
                for (int i = 0; i < Room.globalRooms.get(index).getNumber(); i++)
                {
                    if(Room.globalRooms.get(index).getListDevice().get(i).getName().equals(DeviceName)){
                        Toast.makeText(getActivity(), "This Device is exited",Toast.LENGTH_SHORT).show();
                        exited = true;
                        break;
                    }

                }
                if(!exited) {
                    Room.globalRooms.get(index).addDevice(new Device(edt_add_Device.getText().toString(), false));
                    deviceAdapter.notifyDataSetChanged();
                    mainActivity.saveRoomList(Room.globalRooms, "listRoom");

                }
            }
        });

        builder.setNegativeButton("Cancel" ,null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDiaLogToRemoveDevice(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View  diaLogView = getLayoutInflater().inflate(R.layout.dialog_remove_device, null);
        builder.setView(diaLogView);
        builder.setTitle("Remove Device");
        builder.setIcon(R.drawable.baseline_add_24);
        final EditText edt_remove_device = diaLogView.findViewById(R.id.edt_remove_device);
        builder.setPositiveButton("Remove Room", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deviceRemove = edt_remove_device.getText().toString(); //lưu tên thiết bị muốn xóa vào biến deviceRemove
                for (int i = 0; i < Room.globalRooms.get(index).getNumber(); i++) {
                    if (Room.globalRooms.get(index).getListDevice().get(i).getName().equals(deviceRemove)) {
                        Room.globalRooms.get(index).removeDevice(i);
                        deviceAdapter.notifyDataSetChanged();
                        mainActivity.saveRoomList(Room.globalRooms, "listRoom");
                        break;
                        //kiểm tra từng thiết bị trong phòng, nếu có thiết bị trùng tên thì xóa bằng hàm removeDevice
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel" ,null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setBackButtonOnToolbar(){
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar_device);; // Assuming 'toolbar' is the Toolbar you want to use
        activity.getSupportActionBar();

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setTitle("Device list");
        }
        toolbar_device.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(); //thì kết thúc fragment hiện tại và quay lại fragment  trước đó
                Toast.makeText(getContext(), "Room", Toast.LENGTH_SHORT).show();
            }
        });
    }

}