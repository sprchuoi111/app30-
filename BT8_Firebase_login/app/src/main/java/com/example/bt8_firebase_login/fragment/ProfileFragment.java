package com.example.bt8_firebase_login.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bt8_firebase_login.MainActivity;
import com.example.bt8_firebase_login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileFragment extends Fragment {
    public static final int MY_REQUEST_CODE =10;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private View mView;
    private Button btn_update_profile;
    private ImageView img_avatar_profile;
    private Uri mUri;
    private MainActivity mainActivity;
    private ProgressBar progress_update_profile;
    EditText edt_name_profile, edt_email_profile;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // init UI for profile

        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        initUI();
        mainActivity = (MainActivity) getActivity();
        setUserInformation();
        initListener();
        // button click update profile of user
        btn_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfile();
            }
        });
        return mView ;
    }
    // Init UI in profile
    private void initUI(){
        edt_name_profile = mView.findViewById(R.id.edt_name_profile);
        edt_email_profile = mView.findViewById(R.id.edt_email_profile);
        btn_update_profile =mView.findViewById(R.id.btn_update_profile);
        img_avatar_profile = mView.findViewById(R.id.img_avatar_profile);
        progress_update_profile = mView.findViewById(R.id.progress_update_profile);
    }
    // set information into profile;
    private void setUserInformation(){
        // get author from firebase
        auth= FirebaseAuth.getInstance();
        // get user
        user = auth.getCurrentUser();
        if(user == null){
            return;
        }
        edt_name_profile.setText(user.getDisplayName());
        edt_email_profile.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.default_profile).into(img_avatar_profile);

    }

    // function to upload img from gallery
    private void initListener(){
        img_avatar_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
    }
    // get permission from service android to load img
    private void onClickRequestPermission() {
        if(mainActivity == null){
            return;
        }
        mainActivity.openGallery();
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        getActivity().requestPermissions(permissions, MY_REQUEST_CODE);
    }

    //set bit map img View
    public void setBitmapImageView(Bitmap bitmapImageView){
        img_avatar_profile.setImageBitmap(bitmapImageView);
    }
    // set uri
    private void setUri(Uri mUri){
        this.mUri = mUri;
    }

    private void onClickUpdateProfile(){
        // get Inform from firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // if user is null passing
        if(user == null){
            return;
        }
        progress_update_profile.setVisibility(View.VISIBLE);
        String strFullname = edt_name_profile.getText().toString().trim();
        String strEmail = edt_email_profile.getText().toString().trim();
        // sent request profile of user changing in firebase
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullname)
                .setPhotoUri(mUri)
                .build();
        // update profile of user in firebase
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //when completed , progress bar is gone and toast popup
                            progress_update_profile.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Update profile success" ,Toast.LENGTH_SHORT).show();
                            // update inform of user in header
                            mainActivity.setUserforheader();
                        }
                    }
                });
        // update user email
        user.updateEmail(strEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                    }
                });

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });
    }
}