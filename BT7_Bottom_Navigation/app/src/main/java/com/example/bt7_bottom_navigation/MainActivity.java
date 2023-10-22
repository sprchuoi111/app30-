package com.example.bt7_bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottom_navi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the content view to the layout defined in "activity_main.xml".
        bottom_navi = findViewById(R.id.bottom_navi); // Find the BottomNavigationView in the layout by its ID.

        // Set an item selection listener for the BottomNavigationView.
        bottom_navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item selection based on the selected item's ID.
                if (item.getItemId() == R.id.action_home) {
                    // Display a toast message with the title of the selected item (Home).
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_favorite) {
                    // Display a toast message with the title of the selected item (Favorite).
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_my_page) {
                    // Display a toast message with the title of the selected item (My Page).
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }
                return false; // Return false to indicate that the selection has not been handled.
            }
        });
    }
}
