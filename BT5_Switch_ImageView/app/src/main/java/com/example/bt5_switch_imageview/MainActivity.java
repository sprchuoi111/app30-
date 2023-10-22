package com.example.bt5_switch_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Switch switch1;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set mapping for element in main activity
        setContentView(R.layout.activity_main);
        switch1 = findViewById(R.id.switch1);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(switch1.isChecked()){
                    // change img when click button on
                    imageView.setImageResource(R.drawable.smilling_cat);
                    // set text under button when click on
                    textView.setText("On");
                }
                else{
                    // change img when click button off
                    imageView.setImageResource(R.drawable.cat_sad);
                    // set text under button when click off
                    textView.setText("Off");
                }
            }
        });
    }
}