package com.example.venkateshkashyap.mysuru_commute.filters;

import android.widget.Filter;

import com.example.venkateshkashyap.mysuru_commute.adapters.AutoCompleteStopsAdapter;

import java.util.ArrayList;
import java.util.List;


public class StopsFilter extends Filter {

    AutoCompleteStopsAdapter adapter;
    List<String> originalList;
    List<String> filteredList;

    public StopsFilter(AutoCompleteStopsAdapter adapter, List<String> originalList) {
        super();
        this.adapter = adapter;
        this.originalList = originalList;
        this.filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            // Your filtering logic goes in here
            for (final String state : originalList) {
                if (state.toLowerCase().contains(filterPattern)) {
                    filteredList.add(state);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.filteredStops.clear();
        adapter.filteredStops.addAll((List) results.values);
        adapter.notifyDataSetChanged();
    }
}