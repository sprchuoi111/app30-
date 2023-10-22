package com.example.bt8_firebase_login.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bt8_firebase_login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_page extends AppCompatActivity {
    TextInputEditText edt_usr_register, edt_psw_register;
    Button btn_register;
    TextView login_Now;
    ProgressBar progressBar;
    Intent intent;

    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            intent = new Intent(getApplicationContext() , register_page.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // authendication
        mAuth = FirebaseAuth.getInstance();
        //casting
        edt_usr_register = findViewById(R.id.edt_usr_register);
        edt_psw_register =findViewById(R.id.edt_psw_register);
        btn_register = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.ProgessBar);
        login_Now = findViewById(R.id.login_Now);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = edt_usr_register.getText().toString();
                password = edt_psw_register.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                // check if the edt Email is empty
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(register_page.this, "Enter email" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                // check if the edt Pass is empty
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(register_page.this, "Enter password" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                // Create User with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // set progressbar gone when register completed
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(register_page.this," Account is created",Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(register_page.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });
        // Login function -> go to login page
        login_Now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext() , login_page.class);
                startActivity(intent);
                finish();
            }
        });



    }
}