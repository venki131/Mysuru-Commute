package com.example.venkateshkashyap.mysuru_commute;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.venkateshkashyap.mysuru_commute.helpers.BusStopsHelper;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;

public class SplashScreen extends Activity implements BusStopsHelper.OnBusStopsResponseReceived{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        new BusStopsHelper(this).getBusStops(this);
    }

    @Override
    public void onBusStopsResponseReceived(BusStops busStopsData) {
        // After completing http call
        // will close this activity and lauch main activity
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);

        // close this activity
        finish();
    }

    @Override
    public void onFailure() {
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);

        // close this activity
        finish();
    }
}