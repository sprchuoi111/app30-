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
import android.widget.Toast;

import com.example.final_ex.fragment.DeviceFragment;
import com.example.final_ex.fragment.HomeFragment;
import com.example.final_ex.fragment.LogoutFragment;
import com.example.final_ex.fragment.SettingFragment;
import com.example.final_ex.object.Room;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //create variable for fragment
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_SETTING = 1;
    private static final int FRAGMENT_LOGOUT = 2;
    // create for current fragment
    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
        Toast.makeText(MainActivity.this, "Save complete", Toast.LENGTH_SHORT).show();
    }

    public List<Room> getRoomList(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        if (json != null) {
            Type type = new TypeToken<List<Room>>() {}.getType();
            List<Room> roomList = gson.fromJson(json, type);
            return roomList;
        } else {
            return new ArrayList<>();
        }
    }
}