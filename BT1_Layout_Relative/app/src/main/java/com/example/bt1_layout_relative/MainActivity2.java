package com.example.bt1_layout_relative;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity2 extends AppCompatActivity {
    private TextView user , sex , textView_touchbutton,textView_status;
    private RadioGroup radioGroup;
    private Toolbar toolbar;
    private CheckBox checkBox1,checkBox2,checkBox3;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user = (TextView) findViewById(R.id.name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        sex = (TextView) findViewById(R.id.sex);
        button = (Button) findViewById(R.id.button);
        textView_touchbutton = (TextView) findViewById(R.id.textView_touchbutton);
        textView_status = findViewById(R.id.textView_status);
        //toolbar = (Toolbar) findViewById(R.id.myToolBar);
        //
        //SetSupportActionBar(toolbar);
        String username = getIntent().getStringExtra("email");
        user.setText(username);
        Toast.makeText(MainActivity2.this, "Sign in success!!", Toast.LENGTH_SHORT).show();



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked_id) {
                //find id via sellected button
                RadioButton radioButton = radioGroup.findViewById(checked_id);
                // set Text of button checked id
                sex.setText(radioButton.getText());
            }
        });
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    textView_touchbutton.setText("HOLDING...");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    textView_touchbutton.setText("NORMAL");
                }
                return false;
            }
        });
    }
    // function of checkbox
    public void checkbox(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(view.getId()== R.id.checkBox1){
            if(checked){
                textView_status.setText("Single");
            }
        }
        else if (view.getId()== R.id.checkBox2) {
            if(checked) {
                textView_status.setText("Has 1 child");
            }
        }
        else if (view.getId()== R.id.checkBox3) {
            if(checked) {
                textView_status.setText("Mariage");
            }
        }
    }
}