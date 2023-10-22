package com.example.bt2_seakbar_progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class seekbar extends AppCompatActivity {
    SeekBar seekbar;  // SeekBar UI element
    TextView value;   // TextView to display the selected value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);  // Set the layout for this activity

        // Initialize UI elements by finding them using their IDs
        seekbar = findViewById(R.id.seekBar);
        value = findViewById(R.id.value);

        // Set a SeekBar listener to track changes
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Called when the progress changes
                // Update the TextView to display the selected value
                value.setText(String.valueOf(progress));

                // Log the progress for debugging (optional)
                Log.d("AAA", "onProgressChanged: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Called when the user starts interacting with the SeekBar
                Log.d("AAA", "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Called when the user stops interacting with the SeekBar
                Log.d("AAA", "onStopTrackingTouch: ");
            }
        });
    }
}
