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

import com.example.bt8_firebase_login.MainActivity;
import com.example.bt8_firebase_login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_page extends AppCompatActivity {

    private TextInputEditText edt_usr_login, edt_psw_login;
    private Button btn_login;
    private Button btn_register_now;
    private ProgressBar login_progressbar;
    private Intent intent;
    private TextView tv_forgot_pass;

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
        setContentView(R.layout.activity_login_page);

        // authendication
        mAuth = FirebaseAuth.getInstance();
        //casting
        edt_usr_login = findViewById(R.id.edt_usr_login);
        edt_psw_login = findViewById(R.id.edt_psw_login);
        btn_login = findViewById(R.id.btn_login);
        login_progressbar = findViewById(R.id.login_progressbar);
        btn_register_now = findViewById(R.id.btn_register_now);
        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
        login_progressbar.setVisibility(View.GONE);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = edt_usr_login.getText().toString();
                password = edt_psw_login.getText().toString();
                // check the Email or Password is empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(login_page.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(login_page.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    // set the progressbar visible when the task login is running
                    login_progressbar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // set the progressbar gone when the task login is completing
                                    login_progressbar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Toast popup login success and go to main page
                                        Toast.makeText(login_page.this, " Login success", Toast.LENGTH_SHORT).show();
                                        intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(login_page.this, "Wrong password or username",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        // OnClick register now button -> go to the register page
        btn_register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), register_page.class);
                startActivity(intent);
                finish();
            }
        });
        // OnClick open forgot page activity
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), forgot_page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}