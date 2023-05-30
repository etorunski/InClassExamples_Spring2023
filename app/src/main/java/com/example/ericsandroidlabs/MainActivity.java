package com.example.ericsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.ericsandroidlabs.databinding.ActivityMainBinding;

import java.io.File;

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


        //where can you save files:
        File myDir =getFilesDir();
        String path = myDir.getAbsolutePath();


        SharedPreferences savedprefs = getSharedPreferences("MyFileName", Context.MODE_PRIVATE);

        //get an editor
        SharedPreferences.Editor edit = savedprefs.edit();





        binding.emailText.setText(
            savedprefs.getString("NAME", "default")
        );



        binding.loginButton.setOnClickListener( (v) -> {
            Log.e(TAG, "You clicked the button");

            String whatIsTyped = binding.emailText.getText().toString();

            edit.putInt("Age", 26);
            edit.putString("NAME",whatIsTyped );
            //saves to disk
            edit.commit();


            //where to go:             leaving here          going to SecondActivity
            /*Intent nextPage = new Intent( this, SecondActivity.class);

             nextPage.putExtra("EMAIL", whatIsTyped);
            nextPage.putExtra("AGE", 25);
            nextPage.putExtra("DAY", "Tuesday");
*/

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"A@A.com", "B@B.com"}  );
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");




            //go to another page
            startActivity(intent);  //carries all the data to the next page
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