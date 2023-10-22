package com.example.bt1_layout_relative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button signin,signup;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get username and pass
                String username = email.getText().toString();
                String pass = password.getText().toString();
                // create intent for open other activity
                Intent intent =  new Intent(MainActivity.this , MainActivity2.class);
                // using intent extra put data in to other activity
                intent.putExtra("email" , username);
                intent.putExtra("pass" , pass);
                startActivity(intent);

            }
        });
    }
}