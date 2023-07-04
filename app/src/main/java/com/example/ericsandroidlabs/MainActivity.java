package com.example.ericsandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ericsandroidlabs.data.MainActivityViewModel;
import com.example.ericsandroidlabs.databinding.ActivityMainBinding;
import com.example.ericsandroidlabs.databinding.ReceiveRowLayoutBinding;
import com.example.ericsandroidlabs.databinding.SentRowLayoutBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {


    protected ArrayList<ChatMessage> theWords;


    /** This holds the "Click me" button */
    protected Button myButton;
    protected RecyclerView recyclerView;

    MessageDatabase myDB ;
    ChatMessageDAO myDAO;

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


        FrameLayout fragmentLocation = findViewById( R.id.fragmentLocation);

        boolean IAmTablet = fragmentLocation != null;


        model = new ViewModelProvider(this).get(MainActivityViewModel.class);

        model.chosenMessage.observe(this, newChatMessage -> {

            if(newChatMessage != null) {
                FragmentManager fMgr = getSupportFragmentManager();

                FragmentTransaction tx = fMgr.beginTransaction();

                //what to show:
                DetailsFragment frag = new DetailsFragment( newChatMessage );

                //where to load:
                tx.add(R.id.fragmentLocation, frag);
                tx.addToBackStack("aqnything here");
                tx.commit();//this will show it
            }
        });

        myDB = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();

        myDAO = myDB.cmDAO();//the only function in MessageDatabase;

//all new messages:
        theWords = model.theWords;


        Executor thread = Executors.newSingleThreadExecutor();
        //add all previous messages from database:
        thread.execute(() -> {
                List<ChatMessage> allMessages = myDAO.getAllMessages();
                theWords.addAll(allMessages);
          });


        myButton = binding.button;
        theEditText = binding.theEditText;
        recyclerView = binding.theRecycleView;

        myButton.setOnClickListener( click -> {
            String input = theEditText.getText().toString();

            int type = 1;
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage newMessage = new ChatMessage(input, currentDateandTime, type);
            Executor thread1 = Executors.newSingleThreadExecutor();
            thread1.execute(() ->{
                    newMessage.id = myDAO.anyFunctionNameForInsertion(newMessage);//add to database;
                   /*this runs in another thread*/
            });

            //insert into ArrayList
            theWords.add(newMessage);

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
                if (position % 2 == 0)
                    return 1;
                else //odd
                    return 2;
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

            itemView.setOnClickListener( click -> {

                int index = getAbsoluteAdapterPosition();
                ChatMessage thisMessage = theWords.get(index);
                model.chosenMessage.postValue(  thisMessage );

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
                builder.setMessage("Do you want to delete this?")
                        .setTitle("QUestion")
                        .setPositiveButton("Go Ahead",(dlg, which)->{

                    //what is the index?
                    int index = getAbsoluteAdapterPosition();

                    ChatMessage toDelete = theWords.get(index);

                    Executor thread1 = Executors.newSingleThreadExecutor();
                    thread1.execute(() ->{
                        myDAO.deleteThisChatMessage( toDelete );
                        theWords.remove(index);//remove from our array list

                        //must be done on the main UI thread
                        runOnUiThread(() -> {  myAdapter.notifyDataSetChanged(); });

                        Snackbar.make( recyclerView, "Deleted your message", Snackbar.LENGTH_LONG)
                                .setAction("UNDO", clk -> {
                                    Executor myTHread = Executors.newSingleThreadExecutor();
                                    myTHread.execute(() -> {
                                        myDAO.anyFunctionNameForInsertion(toDelete);

                                        theWords.add(index,toDelete );
                                       runOnUiThread( () ->  myAdapter.notifyDataSetChanged());
                                    });



                                } )
                                .show();
                        /*this runs in another thread* /
                    });


                } )
                        .setNegativeButton("No", (dl, wh)->{ /*Hide the dialog, do nothing* /})

                //appear:
                .create().show();*/
            }

            );
            //THis holds the message Text:
            theWord = itemView.findViewById(R.id.theMessage);

            //This holds the time text
            theTime = itemView.findViewById(R.id.theTime);
        }
    }
}