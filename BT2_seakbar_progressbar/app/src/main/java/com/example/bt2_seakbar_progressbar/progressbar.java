package com.example.bt2_seakbar_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class progressbar extends AppCompatActivity {
    ProgressBar progressBar;  // Progress bar UI element
    Button button;  // Button to increment the progress

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);  // Set the layout for this activity

        // Initialize UI elements by finding them using their IDs
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call a function to increment the progress of the progress bar
                progressBar.incrementProgressBy(10);

                // Check if the progress has reached or exceeded 100%
                if (progressBar.getProgress() >= 100) {
                    // Display a toast message when the progress is complete
                    Toast.makeText(progressbar.this,
                            "Complete",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}