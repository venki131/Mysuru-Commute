package com.example.venkateshkashyap.mysuru_commute;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.ViewUtils;
import com.example.venkateshkashyap.mysuru_commute.adapters.RouteDetailsRecyclerViewAdapter;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.helpers.RouteDetailslByBusNumberHelper;
import com.example.venkateshkashyap.mysuru_commute.models.RouteDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RouteDetailsActivity extends AppCompatActivity implements RouteDetailslByBusNumberHelper.OnRouteDetailsResponseReceived{

    private RouteDetailsRecyclerViewAdapter mRouteDetailsRecyclerViewAdapter;
    private RelativeLayout mErrorLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mErrorLayout= (RelativeLayout) findViewById(R.id.rl_error_layout);
        RouteDetails mRouteDetails = new RouteDetails();
        List<String> mRouteLists = new ArrayList<>();
        mRouteDetailsRecyclerViewAdapter = new RouteDetailsRecyclerViewAdapter(RouteDetailsActivity.this, mRouteDetails, mRouteLists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mRouteDetailsRecyclerViewAdapter);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getIntent().getStringExtra(Constants.BundleIDs.BUS_NUM_BUNDLE_ID));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new RouteDetailslByBusNumberHelper(this).getRoutesByBusNum((RouteDetailslByBusNumberHelper.OnRouteDetailsResponseReceived) this, mErrorLayout, mRecyclerView, getIntent().getStringExtra(Constants.BundleIDs.BUS_NUM_BUNDLE_ID));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void updateList(RouteDetails routeDetails) {
        if (mErrorLayout.getVisibility() == View.VISIBLE) {
            ViewUtils.hideTheViews(mErrorLayout);
            ViewUtils.showTheViews(mRecyclerView);
        }
        mRouteDetailsRecyclerViewAdapter.setList(routeDetails);
    }


    @Override
    public void onRouteDetailsResponseReceived(RouteDetails routeDetails) {

        DialogUtils.hideProgressDialog();

        if(routeDetails!=null && routeDetails.getRoutesList()!=null &&  routeDetails.getRoutesList().getRoute()!=null && routeDetails.getRoutesList().getRoute().size()!=0){
            updateList(routeDetails);
        }

    }

    @Override
    public void onFailure() {
        DialogUtils.hideProgressDialog();
    }
}

