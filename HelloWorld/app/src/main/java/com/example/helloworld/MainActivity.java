package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textA = findViewById(R.id.texta);
        TextView textB = findViewById(R.id.textb);

    }
    public void onBtnClick(View view){
        TextView textResult = findViewById(R.id.result);
            
    }
}