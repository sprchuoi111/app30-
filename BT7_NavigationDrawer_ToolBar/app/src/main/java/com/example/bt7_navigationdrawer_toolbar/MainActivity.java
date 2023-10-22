package com.example.bt7_navigationdrawer_toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer_layout; // Declare a private member variable for the DrawerLayout.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the XML layout file "activity_main".
        Toolbar toolbar = findViewById(R.id.toolbar); // Find the Toolbar view in the layout.
        setSupportActionBar(toolbar); // Set the Toolbar as the app's action bar.

        drawer_layout = findViewById(R.id.drawer_layout); // Find the DrawerLayout view in the layout.

        // Create an ActionBarDrawerToggle for syncing the ActionBar with the DrawerLayout.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer_layout.addDrawerListener(toggle); // Add a DrawerListener to the DrawerLayout.
        toggle.syncState(); // Synchronize the DrawerToggle state with the DrawerLayout.

        // Find the NavigationView in the layout and set this activity as the listener for item clicks.
        NavigationView navigation_view = findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(this);
    }

    // Implementation of the click event for NavigationView items.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Handle different item clicks based on their IDs.
        if (id == R.id.active_home) {
            // Handle the "Home" item click.
        } else if (id == R.id.active_favorite) {
            // Handle the "Favorite" item click.
        } else if (id == R.id.active_author) {
            // Handle the "Author" item click.
        }

        // Close the navigation drawer when an item is selected.
        drawer_layout.closeDrawer(GravityCompat.START);

        // Return true to indicate that the event has been handled.
        return true;
    }

    @Override
    public void onBackPressed() {
        // Check if the navigation drawer is open, and if so, close it.
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            // If the drawer is not open, allow the default back button behavior.
            super.onBackPressed();
        }
    }
}
