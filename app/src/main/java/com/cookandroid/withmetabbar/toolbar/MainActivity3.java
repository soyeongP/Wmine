package com.cookandroid.withmetabbar.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cookandroid.withmetabbar.MainActivity;
import com.cookandroid.withmetabbar.R;
import com.cookandroid.withmetabbar.navigation.AlarmFragment;
import com.cookandroid.withmetabbar.navigation.HomeFragment;
import com.cookandroid.withmetabbar.navigation.PlaceCheckFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity3  extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();//1
    PlaceCheckFragment placeCheckFragment = new PlaceCheckFragment();//2
    HomeFragment homeFragment = new HomeFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);




    }






} //new

