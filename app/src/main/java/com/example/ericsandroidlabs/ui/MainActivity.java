package com.example.ericsandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ericsandroidlabs.data.MainActivityViewModel;
import com.example.ericsandroidlabs.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

MainActivityViewModel model;

    //equivalent to        static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        model = new ViewModelProvider(this).get(MainActivityViewModel.class);


        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView( variableBinding.getRoot() );//after this line, you can use the variables


        //listen for changes to MutableLiveData
        model.isOn.observe(this, (newValue) -> {
            //in here, isOn has changed and the new value is newValue
            variableBinding.theCheckbox.setChecked(newValue);
            variableBinding.theSwitch.setChecked(newValue);
        });

        //lambda notation:
        variableBinding.theCheckbox.setOnCheckedChangeListener( ( a, b ) -> {
            model.isOn.postValue(b) ;//set to b, notify all observers

        });

        variableBinding.theSwitch.setOnCheckedChangeListener( ( a, b ) -> {
            model.isOn.postValue( b );

        });                                             //onClick(View v)
variableBinding.theImageButton.setOnClickListener( (v) -> {

    Toast.makeText(this, "This is here", Toast.LENGTH_SHORT).show();

});


        variableBinding.theText.setText(  model.theText );


        variableBinding.theButton.setOnClickListener( ( click )  -> {
            model.theText = "you clicked me";
            variableBinding.theText.setText(model.theText);

        } );



    }

}