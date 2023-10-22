package com.example.bt5_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list_room;
    Button btn_add, btn_update;
    EditText Room_edt;
    ArrayList<String> arrayRooms;
    int locate = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components.
        list_room = findViewById(R.id.list_room);
        Room_edt = findViewById(R.id.Room_edt);
        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);

        // Create a new list to store room names.
        arrayRooms = new ArrayList<>();

        // Add initial items to the list.
        arrayRooms.add("Living room");
        arrayRooms.add("Rest room");
        arrayRooms.add("Bed room 1");
        arrayRooms.add("Kitchen");
        arrayRooms.add("Hall");
        arrayRooms.add("Bed room 2");

        // Create an ArrayAdapter to display the list of rooms in the ListView.
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayRooms);

        // Set the adapter for the ListView.
        list_room.setAdapter(adapter);

        // Handle item click events.
        list_room.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the position of the room when clicking and display it as a toast message.
                Toast.makeText(MainActivity.this, arrayRooms.get(i), Toast.LENGTH_SHORT).show();
                // Set the room name in the EditText for potential updates.
                Room_edt.setText(arrayRooms.get(i));
                // Store the position of the clicked item.
                locate = i;
            }
        });

        // Handle long-press item click events for deletion.
        list_room.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Display a toast message for long clicks.
                Toast.makeText(MainActivity.this, "Long Click: " + i, Toast.LENGTH_SHORT).show();
                // Remove the room name at the selected position from the list.
                arrayRooms.remove(i);
                // Notify the adapter of the data change to refresh the ListView.
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        // Handle the "Add" button click event.
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add the room name from the EditText to the list.
                arrayRooms.add(Room_edt.getText().toString());
                // Notify the adapter of the data change to refresh the ListView.
                adapter.notifyDataSetChanged();
            }
        });

        // Handle the "Update" button click event.
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the name of the room at the stored position (locate).
                arrayRooms.set(locate, Room_edt.getText().toString());
                // Notify the adapter of the data change to refresh the ListView.
                adapter.notifyDataSetChanged();
            }
        });
    }
}
