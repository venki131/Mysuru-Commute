package com.example.venkateshkashyap.mysuru_commute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venkateshkashyap.mysuru_commute.Utils.ViewUtils;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.helpers.BusStopsHelper;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;


public class SplashScreenActivity extends Activity implements BusStopsHelper.OnBusStopsResponseReceived{

    private TextView mTextViewAppName;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mTextViewAppName = (TextView) findViewById(R.id.text_app_name) ;
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        new BusStopsHelper(this).getBusStops(this);
    }

    @Override
    public void onBusStopsResponseReceived(BusStops busStopsData) {
        // After completing http call
        // will close this activity and lauch main activity
        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
        i.putExtra(Constants.BundleIDs.BUS_STOPS_BUNDLE_ID, busStopsData);
        startActivity(i);

        // close this activity
        finish();
    }

    @Override
    public void onFailure() {
//        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
//        startActivity(i);
        ViewUtils.hideTheViews(mProgressBar);
        Snackbar.make(mTextViewAppName, "Connect to Internet and Try Again!", Snackbar.LENGTH_INDEFINITE).setAction(R.string.try_again, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtils.showTheViews(mProgressBar);
                new BusStopsHelper(SplashScreenActivity.this).getBusStops(SplashScreenActivity.this);

            }
        }).show();


    }
}