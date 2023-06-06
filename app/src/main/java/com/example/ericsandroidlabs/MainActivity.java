package com.example.ericsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**This page represents the first page loaded
 * @author Eric TOrunski
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {


    /** This is a javadoc comment */

    /*   This is a normal comment */

    /** This holds the "Hello world" text view */
    protected TextView theText;


    /** This holds the "Click me" button */
    protected Button myButton;


    /** This holds the edit text for typing into */
    protected EditText theEditText;

    //equivalent to        static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class


        //loads an XML file on the page
        setContentView(  R.layout.activity_main   );

        theText = findViewById(R.id.textView);
        myButton = findViewById(R.id.button);
        theEditText = findViewById(R.id.theEditText);

        myButton.setOnClickListener( click -> {
            String input = theEditText.getText().toString();

            if(checkInput(input, '='))
            {
                theText.setText("We found =");
            }
            else theText.setText("NO = found in the text");

        });
    }

    /** This function checks the input string
     *
     * @param input  The string to search
     * @param toLookFOr  The character to compare
     * @return true if toLookFor is part of the input string, false otherwise
     */

    boolean checkInput(String input, char toLookFOr  ){
        boolean found = false;

        for(int i = 0; i < input.length(); i++ ){
            char c = input.charAt(i);
            if(c == toLookFOr)
                found = true;
        }
        return found;
    }


}