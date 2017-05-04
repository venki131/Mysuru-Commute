package com.example.venkateshkashyap.mysuru_commute.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.RouteDetailsActivity;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.fragments.BusNumbersFragment;
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

    private BusNumbers mBusNumbersData = new BusNumbers();
    private List<String> mValues = new ArrayList<>();
    private final BusNumbersFragment.OnListFragmentInteractionListener mListener;
    private Context context;

    public BusNumbersRecyclerViewAdapter(Context context,BusNumbers busNumbersData, OnListFragmentInteractionListener listener) {
        this.context = context;
        mBusNumbersData = busNumbersData;
        if(mBusNumbersData.getData()!=null) {
            mValues = mBusNumbersData.getData();
        }
        mListener = listener;
    }

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);

                    Intent intent = new Intent(context,RouteDetailsActivity.class);

                    intent.putExtra(Constants.BundleIDs.BUS_NUM_BUNDLE_ID, holder.mItem);
                    context.startActivity(intent);
                }
            }
        });
    }

    public void setList(BusNumbers busNumbersData) {
        mBusNumbersData = busNumbersData;
        mValues.clear();
        mValues.addAll(mBusNumbersData.getData());
        notifyDataSetChanged();
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

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
