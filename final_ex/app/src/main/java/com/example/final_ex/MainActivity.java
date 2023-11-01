package com.example.final_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.se.omapi.Session;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.final_ex.fragment.DeviceFragment;
import com.example.final_ex.fragment.HomeFragment;
import com.example.final_ex.fragment.LogoutFragment;
import com.example.final_ex.fragment.SettingFragment;
import com.example.final_ex.object.Room;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //create variable for fragment
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_SETTING = 1;
    private static final int FRAGMENT_LOGOUT = 2;
    // create for current fragment
    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;



    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping for toolbar and action bar
        toolbar = findViewById(R.id.toolbar);
        //using created support of android studio
        setSupportActionBar(toolbar);

        //mapping Drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);
        // Create ActionBarDrawerToggle for manage even close and open draw
        toggle = new ActionBarDrawerToggle(this , mDrawerLayout , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        // add ActionBarDrawerToggle
        mDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        // mapping navigation view and set listener
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set Home is default checked first
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        // auto set home fragment when open app
        replaceFragment(new HomeFragment());
        // create stack to store fragment
        //get RealTime database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("room");

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            openHomeFragment();
        } else if (id == R.id.nav_favorite) {
            openSettingFragment();
        } else if (id == R.id.nav_history) {
            openLogoutFragment();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //method open  Fragment
    private void openHomeFragment() {
        if (mCurrentFragment != FRAGMENT_HOME) {
            replaceFragment(new HomeFragment());
            mCurrentFragment = FRAGMENT_HOME;
        }
    }
    private void openSettingFragment() {
        if (mCurrentFragment != FRAGMENT_SETTING) {
            replaceFragment(new SettingFragment());
            mCurrentFragment = FRAGMENT_SETTING;
        }
    }
    private void openLogoutFragment() {
        if (mCurrentFragment != FRAGMENT_LOGOUT) {
            replaceFragment(new LogoutFragment());
            mCurrentFragment = FRAGMENT_LOGOUT;
        }
    }
    // send data using bundle from room to device fragment
    public void gotoDeviceFragment(String nameRoom , int index){
        // construct bundle contain key : nameRom and index of room
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        DeviceFragment deviceFragment  = new DeviceFragment();
        // sent data to bundle;
        // Pass room data to the fragment using a bundle
        Bundle bundle = new Bundle();
        bundle.putString("NameRoom" , nameRoom);
        bundle.putInt("index" , index);
        // get bundle
        // Replace the current fragment with the DeviceFragment
        deviceFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_data, deviceFragment);
        fragmentTransaction.addToBackStack(DeviceFragment.TAG);
        fragmentTransaction.commit();

    }
    // function replace fragment
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_data , fragment);
        fragmentTransaction.commit();
    }
    //fix not close app when push back button in phone
    @Override
    public void onBackPressed() { //tạo sự kiện nhấn nút Back khi Drawer đang mở
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START); //nếu Drawer đang mở sẽ đóng Drawer lại
        } else {
            super.onBackPressed(); //nếu Drawer đã đóng thì gọi phương thức cha
        }
    }

    public void saveRoomList(List<Room> list, String key) {
        // Create a SharedPreferences object associated with the MainActivity.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        // Initialize Gson to convert the list of 'Room' objects into a JSON structure.
        Gson gson = new Gson();

        // Convert the list of 'Room' objects to a JSON string.
        String json = gson.toJson(list);

        // Get an editor to modify the SharedPreferences.
        SharedPreferences.Editor editor = prefs.edit();

        // Store the JSON string under the specified key in SharedPreferences.
        editor.putString(key, json);

        // Apply the changes asynchronously.
        editor.apply();

        // Display a short toast message to confirm that the save operation is complete.
        Toast.makeText(MainActivity.this, "Save complete", Toast.LENGTH_SHORT).show();
    }


    public List<Room> getRoomList(String key) {
        // Retrieve the default SharedPreferences associated with the MainActivity.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        // Initialize Gson for object deserialization.
        Gson gson = new Gson();

        // Retrieve the JSON string stored in SharedPreferences under the given key.
        String json = prefs.getString(key, null);

        // Check if a JSON string was successfully retrieved.
        if (json != null) {
            // Define the type of object to deserialize (List of Room) using TypeToken.
            Type type = new TypeToken<List<Room>>() {}.getType();

            // Deserialize the JSON string into a List of Room objects.
            List<Room> roomList = gson.fromJson(json, type);

            // Return the deserialized list of Room objects.
            return roomList;
        } else {
            // If no data was found under the specified key, return an empty List.
            return new ArrayList<>();
        }
    }

    public void AddRoomListToFireBase(List<Room> list) {
        // Remove all value in ref
        RemoveRoomToFireBase(list);
        // Assuming 'list' is your list of rooms

        // Get a reference to the "rooms" node in the Firebase Realtime Database
//        DatabaseReference roomsRef = mDatabase.getRef();

        // Push each room to create a unique key for each entry
//            DatabaseReference roomRef = roomsRef.push();
            mDatabase.setValue(Room.globalRooms)
                    .addOnSuccessListener(aVoid -> {
                        // Data has been saved successfully
                        Toast.makeText(MainActivity.this, "Save complete", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Handle any errors
                        Toast.makeText(MainActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                    });
    }
    public void RemoveRoomToFireBase(List<Room> list) {
        // Assuming 'list' is your list of rooms

        // Get a reference to the "rooms" node in the Firebase Realtime Database


        // Push each room to create a unique key for each entry
        mDatabase.removeValue()
                .addOnSuccessListener(aVoid -> {
                    // Data has been saved successfully
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                    Toast.makeText(MainActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                });
    }
    // chua viet xong XD
    public void RealtimeChangeValueDevice(){
        // get specific room key for device
        DatabaseReference roomsRef = mDatabase.getRef();
        String roomNameToFind = "Your Room Name"; // Replace with the actual room name you want to find

        roomsRef.orderByChild("roomName").equalTo(roomNameToFind).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
                    String roomKey = roomSnapshot.getKey();
                    // Use roomKey for your purpose
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });
        Query statebutton = FirebaseDatabase.getInstance().getReference("rooms").getRef().child("listDevice").orderByChild("0");
        statebutton.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}