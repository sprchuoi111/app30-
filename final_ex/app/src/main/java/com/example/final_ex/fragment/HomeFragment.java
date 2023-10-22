package com.example.final_ex.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_ex.Interface.SelectListener;
import com.example.final_ex.MainActivity;
import com.example.final_ex.R;
import com.example.final_ex.object.Room;
import com.example.final_ex.viewholder.RoomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    // Tạo các biến để tham chiếu đến các phần tử ở layout
    private RecyclerView rcvRoom; // A reference to the RecyclerView for displaying rooms.
    private View mView; // A reference to the main view of the fragment.
    private FloatingActionButton btnAddRoom; // A reference to a button for adding a room.
    private FloatingActionButton btnRemoveRoom; // A reference to a button for removing a room.
    private MainActivity mMainActivity; // A reference to the main activity.
    private RoomAdapter mRoomAdapter; // An adapter for populating data in the RecyclerView.


    // Ghi đè phương thức onResume để xử lý khi chuyển sang màn hình khác
    @Override
    public void onResume() {
        mRoomAdapter.notifyDataSetChanged(); // Refresh the RecyclerView data.
        super.onResume(); // Call the parent class's onResume method.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        mView = inflater.inflate(R.layout.fragment_home, container, false); // Inflate the fragment_home layout into mView.
        mMainActivity = (MainActivity) getActivity(); // Get a reference to the hosting Activity (assumed to be MainActivity).
        rcvRoom = mView.findViewById(R.id.rcvRoom); // Find the RecyclerView in the layout.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainActivity);
        rcvRoom.setLayoutManager(linearLayoutManager); // Create and set a LinearLayoutManager for rcvRoom.
        mRoomAdapter = new RoomAdapter(Room.globalRooms, new SelectListener() {
            // Create a RoomAdapter with globalRooms data and an interface for handling item clicks.
            @Override
            public void onClickItemRoom(Room room, int position) {
                mMainActivity.gotoDeviceFragment(room.getName().toString(), position);
            }
        });
        rcvRoom.setAdapter(mRoomAdapter); // Set the adapter for the RecyclerView.

        // Tham chiếu đến EditText và nút nhấn
        btnAddRoom = mView.findViewById(R.id.btnAdd); // Find the button for adding a room.
        btnRemoveRoom = mView.findViewById(R.id.btnRemove); // Find the button for removing a room.
        //floating_btn_add_rm_room = mView.findViewById(R.id.floating_btn_add_rm_room); // floating button for remove room

        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            // Set a click event listener for the "Add Room" button.
            @Override
            public void onClick(View v) {
                // when clicked show the dialog show
                showDiaLogToAddRoom();


            }
        });

        btnRemoveRoom.setOnClickListener(new View.OnClickListener() {
            // Set a click event listener for the "Remove Room" button.
            @Override
            public void onClick(View v) {
                showDiaLogToRemoveRoom();
            }
        });

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mMainActivity, DividerItemDecoration.VERTICAL);
        // Set an ItemDecoration for the RecyclerView to display dividers between list items.

        return mView; // Return the inflated view (fragment_home).
    }
    private void showDiaLogToAddRoom(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View  diaLogView = getLayoutInflater().inflate(R.layout.dialog_add_room, null);
        builder.setView(diaLogView);
        builder.setTitle("Add Room");
        builder.setIcon(R.drawable.baseline_add_home_24);
        final EditText edt_add_Room = diaLogView.findViewById(R.id.edt_add_Room);
        builder.setPositiveButton("Add Room", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String roomName =  edt_add_Room.getText().toString();
                boolean exited  = false;
                for(Room room : Room.globalRooms)
                {
                    if(room.getName().equals(roomName)){
                        Toast.makeText(getActivity(), "This room is exited",Toast.LENGTH_SHORT).show();
                        exited = true;
                        break;
                    }

                }
                if(!exited) {
                    Room.globalRooms.add(new Room(edt_add_Room.getText().toString(),Room.default_device()));
                    mRoomAdapter.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton("Cancel" ,null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showDiaLogToRemoveRoom(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View  diaLogView = getLayoutInflater().inflate(R.layout.dialog_remove_room, null);
        builder.setView(diaLogView);
        builder.setTitle("Remove Room");
        builder.setIcon(R.drawable.baseline_add_home_24);
        final EditText edt_remove_Room = diaLogView.findViewById(R.id.edt_remove_Room);
        builder.setPositiveButton("Remove Room", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean existed  = false;
                String roomRemove = edt_remove_Room.getText().toString(); // Get the room name to remove.
                for (int i = Room.globalRooms.size() - 1; i >= 0; i--) {
                    Room lRoom = Room.globalRooms.get(i);
                    if (lRoom.getName().toString().equals(roomRemove)) {
                        Room.globalRooms.remove(i);
                        mRoomAdapter.notifyDataSetChanged();
                        // Iterate through globalRooms and remove the Room with the specified name.
                        existed  = true;
                       }
               }
                if(!existed) {
                    Toast.makeText(getActivity(),"This room doesn't exist" , Toast.LENGTH_SHORT).show();
                    mRoomAdapter.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton("Cancel" ,null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}