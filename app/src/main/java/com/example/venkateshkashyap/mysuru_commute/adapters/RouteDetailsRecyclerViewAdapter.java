package com.example.venkateshkashyap.mysuru_commute.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.models.RouteDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Venkatesh Kashyap on 5/4/2017.
 */

public class RouteDetailsRecyclerViewAdapter extends RecyclerView.Adapter<RouteDetailsRecyclerViewAdapter.ViewHolder> {

    private RouteDetails routeDetails = new RouteDetails();
    private List<String> routeLists = new ArrayList<>();
    private Context context;

    public RouteDetailsRecyclerViewAdapter(Context context, RouteDetails routeDetails, List<String> routeLists){
        this.context = context;
        this.routeDetails = routeDetails;
        this.routeLists = routeLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routes_list_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = routeLists.get(position);
        holder.mContentView.setText(routeLists.get(position));
    }

    @Override
    public int getItemCount() {
        return routeLists.size();
    }

    public void setList(RouteDetails routeDetails) {
        this.routeDetails = routeDetails;
        routeLists.clear();
        routeLists.addAll(routeDetails.getRoutesList().getRoute());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.txt_route_details);
        }
    }
}
