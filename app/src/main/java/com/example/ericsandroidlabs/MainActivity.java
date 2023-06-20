package com.example.ericsandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ericsandroidlabs.data.MainActivityViewModel;
import com.example.ericsandroidlabs.databinding.ActivityMainBinding;
import com.example.ericsandroidlabs.databinding.ReceiveRowLayoutBinding;
import com.example.ericsandroidlabs.databinding.SentRowLayoutBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {


    protected ArrayList<ChatMessage> theWords;


    /** This holds the "Click me" button */
    protected Button myButton;
    protected RecyclerView recyclerView;

    /** This holds the edit text for typing into */
    protected EditText theEditText;
    MainActivityViewModel model;
    RecyclerView.Adapter myAdapter;
    //equivalent to        static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        //loads an XML file on the page
        setContentView(  binding.getRoot()  );

        model = new ViewModelProvider(this).get(MainActivityViewModel.class);
//all new messages:
        theWords = model.theWords;

        myButton = binding.button;
        theEditText = binding.theEditText;
        recyclerView = binding.theRecycleView;

        myButton.setOnClickListener( click -> {
            String input = theEditText.getText().toString();

            int type = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            //insert into ArrayList
            theWords.add(new ChatMessage(input, currentDateandTime, type));

            myAdapter.notifyDataSetChanged(); //updates the rows

            theEditText.setText("");
        });

        //for recycler view:


        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               //this inflates the row layout

                //int viewType is what layout to load
                if(viewType == 1) {
                    SentRowLayoutBinding binding =                           //how big is parent?
                            SentRowLayoutBinding.inflate(getLayoutInflater(), parent, false);

                       return new MyRowHolder(binding.getRoot());
                }
                else {
                    ReceiveRowLayoutBinding binding =
                            ReceiveRowLayoutBinding.inflate(getLayoutInflater(), parent, false);
                    return new MyRowHolder(binding.getRoot());
                }
            }
            public int getItemViewType(int position){

                //even
               // if (position % 2 == 0)
                    return 1;
                //else //odd
                   // return 2;
            }
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

                //update the widgets:
                ChatMessage atThisRow = theWords.get(position);
                holder.theTime.setText(atThisRow.timeSent);
                holder.theWord.setText(  atThisRow.message );//puts the string in position at theWord TextView
            }

            @Override
            public int getItemCount() {
                //how many rows there are:
                return theWords.size();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected class MyRowHolder extends RecyclerView.ViewHolder {
        // put variables for what is on a single row:
        TextView theWord;
        TextView theTime;
                                    //This is a row:
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            //THis holds the message Text:
            theWord = itemView.findViewById(R.id.theMessage);

            //This holds the time text
            theTime = itemView.findViewById(R.id.theTime);
        }
    }
}