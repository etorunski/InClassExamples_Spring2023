package com.example.ericsandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        //loads an XML file on the page
        setContentView(  binding.getRoot()  );


        setSupportActionBar(binding.myToolbar); //adds the toolbar

        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, binding.myToolbar,
                R.string.open, R.string.close);

        drawer.addDrawerListener( toggle ); //rotate the button when opening

        toggle.syncState(); //initialized the button

        binding.myNavigationView.setNavigationItemSelectedListener(
                (menuItem) -> {

                    if(menuItem.getItemId() == R.id.garbage_id)
                    {
                        Log.d("CLicked", "You clicked the gargbage pail");
                    }
                    else if(menuItem.getItemId() == R.id.garbage_2)
                    {
                        Log.d("CLicked", "You clicked the gargbage 2");
                    }

                    drawer.closeDrawer(GravityCompat.START); //close to the left
                    return true;
                }

        ); //onOptionItemSelected
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

         getMenuInflater().inflate(   R.menu.my_menu,  menu);
         return true;
    }
}