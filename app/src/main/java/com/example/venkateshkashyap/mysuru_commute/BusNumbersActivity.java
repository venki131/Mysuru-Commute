package com.example.venkateshkashyap.mysuru_commute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.ViewUtils;
import com.example.venkateshkashyap.mysuru_commute.adapters.BusNumbersRecyclerViewAdapter;
import com.example.venkateshkashyap.mysuru_commute.adapters.RouteDetailsRecyclerViewAdapter;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.helpers.RouteDetailslByBusNumberHelper;
import com.example.venkateshkashyap.mysuru_commute.models.BusNumbers;
import com.example.venkateshkashyap.mysuru_commute.models.RouteDetails;

import java.util.ArrayList;
import java.util.List;

public class BusNumbersActivity extends AppCompatActivity{

    private BusNumbersRecyclerViewAdapter mBusNumbersRecyclerViewAdapter;
    private BusNumbers mBusNumbers;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_numbers);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        mBusNumbers = getIntent().getParcelableExtra(Constants.BundleIDs.SRC_DEST_BUSES_BUNDLE_ID);
        mBusNumbersRecyclerViewAdapter = new BusNumbersRecyclerViewAdapter(BusNumbersActivity.this,mBusNumbers);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mBusNumbersRecyclerViewAdapter);

        getSupportActionBar().setTitle(getIntent().getStringExtra(Constants.BundleIDs.SRC_DEST_BUNDLE_ID));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
           super.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}

