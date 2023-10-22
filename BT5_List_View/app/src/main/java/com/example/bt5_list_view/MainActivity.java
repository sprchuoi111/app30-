package com.example.bt5_list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listRooms;
    ArrayList<Rooms> arrayRooms;
    Rooms_adapter rooms_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components.
        cast();

        // Create an ArrayList of room objects.
        arrayRooms = new ArrayList<>();
        arrayRooms.add(new Rooms("Living Room", "This is Living room", R.drawable.livingroom));
        arrayRooms.add(new Rooms("Bed Room 1", "This is Bed room 1", R.drawable.bedroom));
        arrayRooms.add(new Rooms("Bath Room 1", "This is Bath room 1", R.drawable.bathroom));
        arrayRooms.add(new Rooms("Bath Room 2", "This is bath room 2", R.drawable.bathroom));
        arrayRooms.add(new Rooms("Kitchen", "This is kitchen", R.drawable.kitchen));
        arrayRooms.add(new Rooms("Kitchen 1", "This is kitchen", R.drawable.kitchen));
        arrayRooms.add(new Rooms("Kitchen 2", "This is kitchen", R.drawable.kitchen));

        // Create a custom adapter (Rooms_adapter) to display the list of rooms.
        rooms_adapter = new Rooms_adapter(this, R.layout.room_layout, arrayRooms);

        // Set the adapter for the ListView (listRooms).
        listRooms.setAdapter(rooms_adapter);

        // Handle item click events for the ListView.
        listRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected room object and display its name in a toast message.
                Rooms room = arrayRooms.get(i);
                Toast.makeText(MainActivity.this, room.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Initialize UI components.
    private void cast() {
        listRooms = findViewById(R.id.listRooms);
    }
}
