package com.example.checkbox_radiobutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView user, sex, textView_touchbutton, textView_status;
    private RadioGroup radioGroup;
    private Toolbar toolbar; // Commented out, not in use.
    private CheckBox checkBox1, checkBox2, checkBox3;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize references to UI elements by finding their corresponding views in the layout.
        user = (TextView) findViewById(R.id.name);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        sex = (TextView) findViewById(R.id.sex);
        button = (Button) findViewById(R.id.button);
        textView_touchbutton = (TextView) findViewById(R.id.textView_touchbutton);
        textView_status = findViewById(R.id.textView_status);

        // toolbar = (Toolbar) findViewById(R.id.myToolBar); (Commented out and not in use)
        //
        // SetSupportActionBar(toolbar); (Commented out and not in use)

        // Get the "username" from the Intent that started this activity and set it in the "user" TextView.
        String username = getIntent().getStringExtra("email");
        user.setText(username);

        // Show a short toast message indicating that the sign-in was successful.
        Toast.makeText(MainActivity.this, "Sign in success!!", Toast.LENGTH_SHORT).show();

        // Set a listener for changes in the RadioGroup (selection of a RadioButton).
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked_id) {
                // Find the RadioButton that was selected.
                RadioButton radioButton = radioGroup.findViewById(checked_id);
                // Set the text of the "sex" TextView to the text of the selected RadioButton.
                sex.setText(radioButton.getText());
            }
        });

        // Set a touch listener for the "button" to change the "textView_touchbutton" text when pressed.
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    textView_touchbutton.setText("HOLDING...");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    textView_touchbutton.setText("NORMAL");
                }
                return false;
            }
        });
    }

    // Function to handle checkbox clicks.
    public void checkbox(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == R.id.checkBox1) {
            if (checked) {
                textView_status.setText("Single");
            }
        } else if (view.getId() == R.id.checkBox2) {
            if (checked) {
                textView_status.setText("Has 1 child");
            }
        } else if (view.getId() == R.id.checkBox3) {
            if (checked) {
                textView_status.setText("Marriage");
            }
        }
    }

}