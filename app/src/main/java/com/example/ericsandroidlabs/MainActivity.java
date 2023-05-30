package com.example.ericsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ericsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
protected ActivityMainBinding binding;

    //equivalent to        static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        Log.e(TAG, "In OnCreate()");


        //loads an XML file on the page
        setContentView(  binding.getRoot()   );

        binding.loginButton.setOnClickListener( (v) -> {
            Log.e(TAG, "You clicked the button");


            //where to go:             leaving here          going to SecondActivity
            Intent nextPage = new Intent( this, SecondActivity.class);

            //go to another page
            startActivity(nextPage);
        } );

    }

    @Override //garbage collected, app is gone
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "In OnDestroy()");
    }

    @Override //now visible, not listening for clicks
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "In OnStart()");
    }

    @Override  //no longer visible on screen
    protected void onStop() {
        super.onStop();

        Log.e(TAG, "In OnStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "In OnResume()");
    }

    @Override //leaving the screen, no longer listening for input
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "In OnPause()");
    }
}