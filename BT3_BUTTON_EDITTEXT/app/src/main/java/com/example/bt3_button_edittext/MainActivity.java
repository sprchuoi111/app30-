package com.example.bt3_button_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_name;
    Button btn_add, btn_delete;
    TextView tv_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mapping for UI
        edt_name = findViewById(R.id.edt_name);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        tv_name = findViewById(R.id.tv_name);
        // set on click function if click on add button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_name.setText(edt_name.getText());
                Toast.makeText(MainActivity.this , "Add success " , Toast.LENGTH_SHORT).show();
            }
        });
        // set on click function if delete on delete button
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_name.setText(null);
                /* show information when delete success */
                Toast.makeText(MainActivity.this , "Delete success " , Toast.LENGTH_SHORT).show();
            }
        });
    }
}