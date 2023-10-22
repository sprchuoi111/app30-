package com.example.bt1_layout_frame;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Button Button1, Button2, Button3;  // Buttons
    ImageView img1, img2, img3;        // Image Views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Set the layout for this activity

        // Initialize UI elements by finding them using their IDs
        Button1 = findViewById(R.id.Button1);
        Button2 = findViewById(R.id.Button2);
        Button3 = findViewById(R.id.Button3);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        // Set click listeners for the buttons
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When Button1 is clicked, set the z-translation (elevation) of img1 to 2
                // and set the z-translation of img2 and img3 to 1
                img1.setTranslationZ(2);
                img2.setTranslationZ(1);
                img3.setTranslationZ(1);
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When Button2 is clicked, set the z-translation (elevation) of img2 to 2
                // and set the z-translation of img1 and img3 to 1
                img1.setTranslationZ(1);
                img2.setTranslationZ(2);
                img3.setTranslationZ(1);
            }
        });

        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When Button3 is clicked, set the z-translation (elevation) of img3 to 2
                // and set the z-translation of img1 and img2 to 1
                img1.setTranslationZ(1);
                img2.setTranslationZ(1);
                img3.setTranslationZ(2);
            }
        });
    }
}
