package com.example.bt8_firebase_login.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bt8_firebase_login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ChangePasswordFragment extends Fragment {

    private EditText edt_new_pass, edt_retype_new_pass;
    private Button btn_change_pass;
    private View mView;
    private ProgressBar change_password_progressbar;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_change_password, container, false);
        uiInit();
        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeUserPass();
            }
        });




        return mView;
    }
    // Init UI for change Fragment
    private void uiInit(){
        edt_new_pass = mView.findViewById(R.id.edt_new_pass);
        edt_retype_new_pass = mView.findViewById(R.id.edt_retype_new_pass);
        btn_change_pass = mView.findViewById(R.id.btn_change_pass);
        change_password_progressbar =mView.findViewById(R.id.change_password_progressbar);

    }

    private  void changeUserPass(){
        String newPassword = edt_new_pass.getText().toString();
        if(edt_retype_new_pass.getText().toString().equals(edt_new_pass.getText().toString()) ){
            if(TextUtils.isEmpty(newPassword)){
                Toast.makeText(getActivity(),"password is empty",Toast.LENGTH_SHORT).show();
            }
            else {
                change_password_progressbar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(user.getEmail())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    change_password_progressbar.setVisibility(View.GONE);
                                }
                            }
                        });

                user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(),"Change password completely",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }
        }
        else Toast.makeText(getActivity(), "Password isn't match ", Toast.LENGTH_SHORT).show();


    }
}