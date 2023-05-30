package com.example.ericsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ericsandroidlabs.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding  = ActivitySecondBinding.inflate(getLayoutInflater());
        //loads the XML file  /res/layout/activity_second.xml
        setContentView(binding.getRoot());

        Intent nextPage = getIntent();//return the Intent that got us here
//should be variables in nextPage:

        //null if EMAIL is not found
        String EMAIL = nextPage.getStringExtra("EMAIL");//get the value of EMAIL variable
        String day = nextPage.getStringExtra("DAY");
        int age = nextPage.getIntExtra("AGE", 0);


        int someting = nextPage.getIntExtra("SOMETHING", 0);//default is for when SOMETHING is not there


        binding.textView3.setText("You passed " + EMAIL + " and " + day + " and:" + age);

        binding.goBackButton.setOnClickListener( (v)-> {

            //opposite of startActivity()
            finish(); //go back to previous
        });
    }
}