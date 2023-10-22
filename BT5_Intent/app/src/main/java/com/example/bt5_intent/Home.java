package com.example.bt5_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView usr_name;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_back = findViewById(R.id.btn_back);
        usr_name = findViewById(R.id.usr_name);
        // Get intent
        Intent intent = getIntent();
        // take Bundle from intent
        Bundle bundle = intent.getBundleExtra("mypacket");
        // get data from bundle
        String usr = bundle.getString("usr");
        //set user name
        usr_name.setText(usr);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}