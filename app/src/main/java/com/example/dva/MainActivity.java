package com.example.dva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements GetData.AsyncResponse {

    private static final String TAG= "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather/?q=Samara&appid=c1438b7ab7adcbb5ffee5d716f1141db");
            new GetData(this).execute(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        Log.d(TAG, "processFinish: "+output);
        
    }
}