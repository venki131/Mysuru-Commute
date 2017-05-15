package com.example.venkateshkashyap.mysuru_commute.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.filters.StopsFilter;

import java.util.ArrayList;
import java.util.List;


public class AutoCompleteStopsAdapter extends ArrayAdapter<String> {

    private final List<String> stops;


    public List<String> filteredStops = new ArrayList<>();

    public AutoCompleteStopsAdapter(Context context, List<String> stops) {
        super(context, 0, stops);
        this.stops = stops;
    }

    @Override
    public int getCount() {
        return filteredStops.size();
    }

    @Override
    public Filter getFilter() {
        return new StopsFilter(this, stops);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {

            if (convertView == null) {
                // inflate the layout
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.drop_down_item, parent, false);
            }

            // object item based on the position
            String state = filteredStops.get(position);
            // get the TextView and then set the text (item name) and tag (item ID) values
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(state);


        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;

    }

    @Override
    public String getItem(int position) {
        return filteredStops.get(position);
    }
}
