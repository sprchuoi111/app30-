package com.example.bt5_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText psw, usr;         // EditText fields for password and username
    Button signin_btn, signup_btn;  // Buttons for sign-in and sign-up
    int counter = 3;           // Counter to keep track of login attempts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements by finding them using their IDs
        psw = findViewById(R.id.psw);
        usr = findViewById(R.id.usr);
        signin_btn = findViewById(R.id.signin_btn);
        signup_btn = findViewById(R.id.signup_btn);
    }

    // Method to handle the sign-in button click event
    public void signIn(View view) {
        if (psw.getText().toString().equals("admin") && usr.getText().toString().equals("admin")) {
            // If the username and password match, display a success message
            Toast.makeText(MainActivity.this, "Sign in success", Toast.LENGTH_SHORT).show();

            // Create an intent to navigate to the 'Home' activity
            Intent intent = new Intent(MainActivity.this, Home.class);

            // Create a bundle to pass data (in this case, the username)
            Bundle bundle = new Bundle();
            bundle.putString("usr", usr.getText().toString());

            // Put the bundle into the intent
            intent.putExtra("mypacket", bundle);

            // Start the 'Home' activity
            startActivity(intent);

            // Reset the login attempt counter
            counter = 3;
        } else {
            // If the username or password is incorrect, display an error message
            Toast.makeText(MainActivity.this,
                    "Wrong password or username, try again. Attempts left: " + (counter - 1),
                    Toast.LENGTH_SHORT).show();

            // Decrement the login attempt counter
            counter--;

            // Disable the sign-in button if the maximum number of attempts is reached
            if (counter == 0) {
                signin_btn.setEnabled(false);
            }
        }
    }
}
