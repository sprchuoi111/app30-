package com.example.bt7_navigation_fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottom_navi; // Declare a BottomNavigationView variable.
    private ViewPager2 mViewPager; // Declare a ViewPager2 variable.

    private DrawerLayout drawer_layout; // Declare a DrawerLayout variable.
    private Toolbar toolbar; // Declare a Toolbar variable.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the XML layout file "activity_main".

        bottom_navi = findViewById(R.id.bottom_navi); // Find the BottomNavigationView in the layout.
        mViewPager = findViewById(R.id.view_pager); // Find the ViewPager2 in the layout.
        drawer_layout = findViewById(R.id.drawer_layout); // Find the DrawerLayout in the layout.

        // Set up the ViewPager for handling swipeable screens.
        setUpViewPager();

        // Set up the Toolbar and connect it to the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create an ActionBarDrawerToggle for syncing the ActionBar with the DrawerLayout.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer_layout.addDrawerListener(toggle); // Add a DrawerListener to the DrawerLayout.
        toggle.syncState(); // Synchronize the ActionBarDrawerToggle state with the DrawerLayout.

        // Set up click events for the NavigationView.
        NavigationView navigation_view = findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(this);

        // Set up click events for the items in the BottomNavigationView.
        bottom_navi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item clicks in the BottomNavigationView.
                int id = item.getItemId();
                if (id == R.id.active_home) {
                    mViewPager.setCurrentItem(0);
                } else if (id == R.id.active_favorite) {
                    mViewPager.setCurrentItem(1);
                } else if (id == R.id.active_author) {
                    mViewPager.setCurrentItem(2);
                }
                return true;
            }
        });

        // Set up a callback for ViewPager page changes.
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Update the selected item in both the NavigationView and BottomNavigationView.
                switch (position) {
                    case 0:
                        navigation_view.getMenu().findItem(R.id.active_home).setChecked(true);
                        bottom_navi.getMenu().findItem(R.id.active_home).setChecked(true);
                        break;
                    case 1:
                        navigation_view.getMenu().findItem(R.id.active_favorite).setChecked(true);
                        bottom_navi.getMenu().findItem(R.id.active_favorite).setChecked(true);
                        break;
                    case 2:
                        navigation_view.getMenu().findItem(R.id.active_author).setChecked(true);
                        bottom_navi.getMenu().findItem(R.id.active_author).setChecked(true);
                        break;
                }
            }
        });
    }

    // Set up the ViewPager with an adapter.
    private void setUpViewPager() {
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    // Method to navigate to the "DeviceFragment" with data.
    public void goto_Device_Fragment(Room room) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DeviceFragment deviceFragment = new DeviceFragment();

        // Send data to the fragment using a bundle.
        Bundle bundle = new Bundle();
        bundle.putSerializable("room", room);
        deviceFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frame_list_device, deviceFragment);
        fragmentTransaction.addToBackStack(DeviceFragment.TAG);
        fragmentTransaction.commit();
    }

    // Handle item clicks in the NavigationView and synchronize with the BottomNavigationView.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.active_home) {
            mViewPager.setCurrentItem(0);
            bottom_navi.getMenu().findItem(R.id.active_home).setChecked(true);
        } else if (id == R.id.active_favorite) {
            mViewPager.setCurrentItem(1);
            bottom_navi.getMenu().findItem(R.id.active_favorite).setChecked(true);
        } else if (id == R.id.active_author) {
            mViewPager.setCurrentItem(2);
            bottom_navi.getMenu().findItem(R.id.active_author).setChecked(true);
        }

        drawer_layout.closeDrawer(GravityCompat.START); // Close the navigation drawer.
        return true;
    }

    // Handle the back button press to close the navigation drawer if it's open.
    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
