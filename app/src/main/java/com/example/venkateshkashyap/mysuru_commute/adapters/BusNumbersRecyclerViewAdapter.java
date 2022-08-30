package com.example.venkateshkashyap.mysuru_commute.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.RouteDetailsActivity;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.fragments.BusNumbersFragment.OnListFragmentInteractionListener;
import com.example.venkateshkashyap.mysuru_commute.models.BusNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.example.venkateshkashyap.mysuru_commute.models.BusNumbers} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class BusNumbersRecyclerViewAdapter extends RecyclerView.Adapter<BusNumbersRecyclerViewAdapter.ViewHolder> {

    private BusNumbers mBusNumbersData;
    private List<String> mValues = new ArrayList<>();
    private Context context;

    public BusNumbersRecyclerViewAdapter(Context context,BusNumbers busNumbersData) {
        this.context = context;
        mBusNumbersData = busNumbersData;
        if(mBusNumbersData.getData()!=null) {
            mValues = mBusNumbersData.getData();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_busnumbers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,RouteDetailsActivity.class);
            intent.putExtra(Constants.BundleIDs.BUS_NUM_BUNDLE_ID, holder.mItem);
            context.startActivity(intent);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(BusNumbers busNumbersData) {
        mBusNumbersData = busNumbersData;
        mValues.clear();
        if(mBusNumbersData!=null && mBusNumbersData.getData()!=null && mBusNumbersData.getData().size()!=0) {
            mValues.addAll(mBusNumbersData.getData());
            notifyDataSetChanged();
        }
    }

    public void animateTo(List<String> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<String> newModels) {
        for (int i = mValues.size() - 1; i >= 0; i--) {
            final String model = mValues.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<String> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final String  model = newModels.get(i);
            if (!mValues.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<String> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final String model = newModels.get(toPosition);
            final int fromPosition = mValues.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public String removeItem(int position) {
        final String model = mValues.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, String model) {
        mValues.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final String model = mValues.remove(fromPosition);
        mValues.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}