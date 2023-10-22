package com.example.bt8_firebase_login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bt8_firebase_login.R;
import com.example.bt8_firebase_login.fragment.ChangePasswordFragment;
import com.example.bt8_firebase_login.fragment.DeviceFragment;
import com.example.bt8_firebase_login.fragment.FavoriteFragment;
import com.example.bt8_firebase_login.fragment.HistoryFragment;
import com.example.bt8_firebase_login.fragment.HomeFragment;
import com.example.bt8_firebase_login.fragment.ProfileFragment;
import com.example.bt8_firebase_login.login.login_page;
import com.example.bt8_firebase_login.object.Room;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView tv_email , tv_name ;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DrawerLayout drawer_layout;
    private ImageView img_avatar;
    // Constants to represent different fragments and request codes
    private static final  int home = 0 ;
    private static final  int favorite = 1 ;
    private static final  int history = 2 ;
    private static final int profile = 3;
    public static final int change_password = 4;
    public static final int MY_REQUEST_CODE =10;

    private int mCurrentFragment = home;
    // convert variable to global
    final private ProfileFragment myProfileFragment = new ProfileFragment();
    // activity Result
    final private ActivityResultLauncher<Intent> mActivityResulLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        if (intent == null)
                            return;
                        // get Uri from data
                        Uri uri = intent.getData();
                        //
                        try {
                            // Load the selected image into the profile fragment
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            myProfileFragment.setBitmapImageView(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check if the user is authenticated
        getUserInformation();

        // Navigation view

        // navigation Drawer
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer_layout = findViewById(R.id.drawer_layout);
        // Create a toggle for the drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout , toolbar ,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        // click event on navigationView
        navigation_view.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        navigation_view.getMenu().findItem(R.id.active_home).setChecked(true);
        // Initialize user information in the navigation drawer header
        setUserforheader();
    }

    // Manage user information
    private void getUserInformation(){

        // Method to check user authentication and redirect to the login page if necessary
        auth= FirebaseAuth.getInstance();
        // get user
        user = auth.getCurrentUser();

        if(user ==null){
            // User is not logged in, so redirect to the login page
            Intent intent = new Intent(getApplicationContext(), login_page.class);
            startActivity(intent);
            finish();
        }
    }
    // Method to set user information in the navigation drawer header
    public void setUserforheader(){
        if(user != null) {
            NavigationView navigation_view = (NavigationView) findViewById(R.id.navigation_view);
            // init UI
            tv_email = navigation_view.getHeaderView(0).findViewById(R.id.tv_email);
            tv_name = navigation_view.getHeaderView(0).findViewById(R.id.tv_name);
            img_avatar = navigation_view.getHeaderView(0).findViewById(R.id.img_avatar);
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            tv_name.setText(name);
            tv_email.setText(email);
            // Load user avatar or use a default if not available
            Glide.with(this).load(photoUrl).error(R.drawable.default_profile).into(img_avatar);
        }
    }
    // Handle navigation drawer item clicks
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();
        // Check which item was clicked and replace the fragment accordingly
        if(id == R.id.active_home){
            // Replace with the HomeFragment
            Toast.makeText(this,  item.getTitle(), Toast.LENGTH_SHORT).show();
            if (mCurrentFragment != home){
                replaceFragment(new HomeFragment());
                mCurrentFragment = home;
            }

        } else if (id == R.id.active_favorite) {
            // Replace with the FavoriteFragment
            if (mCurrentFragment != favorite){
                replaceFragment(new FavoriteFragment());
                mCurrentFragment = favorite;
            }
        }
        else if (id == R.id.active_author) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            if (mCurrentFragment != history) {
                // Replace with the HistoryFragment
                replaceFragment(new HistoryFragment());
                mCurrentFragment = history;
            }
        }
        else if (id == R.id.my_profile ) {
            // Replace with the ProfileFragment
            if (mCurrentFragment != profile){
                replaceFragment(myProfileFragment);
                mCurrentFragment = profile;
            }
        } else if (id == R.id.change_password) {
            // Replace with the ChangePasswordFragment
            if (mCurrentFragment != change_password){
                replaceFragment(new ChangePasswordFragment());
                mCurrentFragment = change_password;
            }
        }
        else if (id == R.id.logout) {
            // Sign out the user and redirect to the login page
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), login_page.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "Logout success", Toast.LENGTH_SHORT).show();
            finish();
        }
        // Commit the fragment transaction and close the drawer
        fragmentTransaction.commit();
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }
    // Method to replace the current fragment
    // Override onBackPressed() to close the drawer if it's open
    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

    // Method to replace the current fragment

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame , fragment);
        fragmentTransaction.commit();
    }
    // goto device fragment
    // Method to navigate to the DeviceFragment with room data
    public void goto_Device_Fragment(Room room){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DeviceFragment deviceFragment = new DeviceFragment();

        // sent data to bundle;
        // Pass room data to the fragment using a bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("room" , room);

        // get bundle
        // Replace the current fragment with the DeviceFragment
        deviceFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.content_frame ,deviceFragment);
        fragmentTransaction.addToBackStack(DeviceFragment.TAG);
        fragmentTransaction.commit();
    }

    // process permission of user accept or deny
    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        if(requestCode == MY_REQUEST_CODE ){
            if (grantResults.length >0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
            else {

            }
        }
    }

    // open gallery function
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // Start activity for result
        mActivityResulLauncher.launch(Intent.createChooser(intent,"Select picture"));


    }


}