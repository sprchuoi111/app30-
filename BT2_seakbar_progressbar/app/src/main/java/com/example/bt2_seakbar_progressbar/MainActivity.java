package com.example.bt2_seakbar_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_seekbar, btn_progressbar;  // Buttons for navigating to seekbar and progressbar activities

    Intent intent_seekbar, intent_progressbar;  // Intent objects to start activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the layout for this activity

        // Initialize UI elements by finding them using their IDs
        btn_seekbar = findViewById(R.id.btn_seekbar);
        btn_progressbar = findViewById(R.id.btn_progressbar);

        // Set click listeners for the buttons
        btn_seekbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the 'seekbar' activity
                Intent intent_seekbar = new Intent(MainActivity.this, seekbar.class);

                // Start the 'seekbar' activity
                startActivity(intent_seekbar);
            }
        });

        btn_progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate to the 'progressbar' activity
                Intent intent_progressbar = new Intent(MainActivity.this, progressbar.class);

                // Start the 'progressbar' activity
                startActivity(intent_progressbar);
            }
        });
    }
}
