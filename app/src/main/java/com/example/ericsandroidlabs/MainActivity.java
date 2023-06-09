package com.example.ericsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ericsandroidlabs.databinding.ActivityMainBinding;


/** This class represents the first page of our application
 * @author Eric Torunski
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the "Hello world" textview*/
    private TextView theTextView;

    /**This holds the "Click me" button */
    private Button clickButton;

    /** This holds the edit text for writing text */
    private EditText passwordText;

    /** This is a comment  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater());

        //loads an XML file on the page
        setContentView(  binding.getRoot()  );

        theTextView = binding.textView;
        clickButton = binding.clickButton;
        passwordText = binding.passwordText;


        clickButton.setOnClickListener( click -> {

            String password = passwordText.getText().toString();

            if(checkForEquals(password))// checks if = is in password
            {
                //we found = in password
                theTextView.setText("We found =");
            }
            else{
                //no = in password
                theTextView.setText("There was no = found");
            }
        });
    }

    /** This function look for = in a string and returns true if it is found
     *
     * @param password  This is the string to look in
     * @return true if = is in password, false otherwise
     */
    boolean checkForEquals(String password){
        boolean foundEquals = false;

        for(int i = 0; i < password.length(); i++)
        {
            char c = password.charAt(i);

            if( c == '=')
            {
                foundEquals = true;
            }
        }

        return foundEquals;
    }
}