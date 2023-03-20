package com.example.dva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GetData.AsyncResponse {

    private static final String TAG= "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Samara&lang=ru&appid=c1438b7ab7adcbb5ffee5d716f1141db&units=metric");
            new GetData(this).execute(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        Log.d(TAG, "processFinish: "+output);
        try {
            JSONObject resultJSON= new JSONObject(output);
            JSONObject weather= resultJSON.getJSONObject("main");
            JSONObject sys= resultJSON.getJSONObject("sys");

            TextView temp=findViewById(R.id.temperature);
            temp.setText(weather.getString("temp"));

            TextView pressure=findViewById(R.id.pressure);
            pressure.setText(weather.getString("pressure"));

            TextView sunrise=findViewById(R.id.timeSunrise);
            String timeSunrise=sys.getString("sunrise");
            Locale myLocale=new Locale("ru", "RU");
            SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss", myLocale);
            String dateString=formatter.format(new Date(Long.parseLong(timeSunrise)*1000+(60*60*1000)));
            sunrise.setText(dateString);

            TextView sunset=findViewById(R.id.timeSunset);
            String timeSunset=sys.getString("sunset");
            //Locale myLocale=new Locale("ru", "RU");
            //SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss", myLocale);
            dateString=formatter.format(new Date(Long.parseLong(timeSunset)*1000+(60*60*1000)));
            sunset.setText(dateString);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}