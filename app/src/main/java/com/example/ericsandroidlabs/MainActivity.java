package com.example.ericsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ericsandroidlabs.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;


/**This page represents the first page loaded
 * @author Eric TOrunski
 * @version 1.0
 */

public class MainActivity extends AppCompatActivity {


    // for sending network requests:
    RequestQueue queue = null;


    //equivalent to        static void main(String args[])
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater() );

        //This part goes at the top of the onCreate function:
        queue = Volley.newRequestQueue(this); //like a constructor

        //loads an XML file on the page
        setContentView(  binding.getRoot()  );


        binding.button.setOnClickListener( click -> {

            String cityName = binding.theEditText.getText().toString();

            //server name and parameters:                           name=value&name2=value2&name3=value3
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
                    URLEncoder.encode(cityName) //replace spaces, &. = with other characters
                    + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";

            JsonObjectRequest request =   new JsonObjectRequest(Request.Method.GET, url, null,
                    ( successfulResponse ) -> {

                        JSONObject main = null;
                        try {
                            main = successfulResponse.getJSONObject( "main"  );
                            double temp = main.getDouble( "temp" );

                            binding.textView.setText("The temperature is " + temp + " degrees");

                            JSONArray weatherArray = successfulResponse.getJSONArray( "weather");

                            JSONObject pos0 = weatherArray.getJSONObject( 0 );
                            String  iconName = pos0.getString("icon");

                            String pictureURL = "http://openweathermap.org/img/w/" + iconName + ".png";

                            ImageRequest imgReq = new ImageRequest(pictureURL, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {

                                    int i = 0;
                                }
                            }, 1024, 1024, ImageView.ScaleType.CENTER, null,
                                    (error ) -> {
                                    int i = 0;
                            });

                            queue.add(imgReq);

                        }
                        catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }, //gets called if it is successful
                    (vError) ->   {
                            int i = 0;

                    }  ); //gets called if there is an error

            queue.add(request); //run the web query

        });
    }

}