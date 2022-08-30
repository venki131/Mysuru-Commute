package com.example.venkateshkashyap.mysuru_commute;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.splashscreen.SplashScreen;

import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.helpers.BusStopsHelper;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;

public class RoutingActivity extends Activity implements BusStopsHelper.OnBusStopsResponseReceived{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashScreen.setKeepOnScreenCondition(() -> true );
        new BusStopsHelper(this).getBusStops(this);
    }

    @Override
    public void onBusStopsResponseReceived(BusStops busStopsData) {
        // After completing http call
        // will close this activity and launch main activity
        Intent i = new Intent(RoutingActivity.this, MainActivity.class);
        i.putExtra(Constants.BundleIDs.BUS_STOPS_BUNDLE_ID, busStopsData);
        startActivity(i);
        // close this activity
        finish();
    }

    @Override
    public void onFailure() {
        Intent i = new Intent(RoutingActivity.this, MainActivity.class);
        startActivity(i);

        // close this activity
        finish();
    }
}