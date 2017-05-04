package com.example.venkateshkashyap.mysuru_commute.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.ViewUtils;
import com.example.venkateshkashyap.mysuru_commute.adapters.BusNumbersRecyclerViewAdapter;
import com.example.venkateshkashyap.mysuru_commute.helpers.BusNumbersHelper;
import com.example.venkateshkashyap.mysuru_commute.models.BusNumbers;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BusNumbersFragment extends Fragment implements BusNumbersHelper.OnBusNumbersResponseReceived {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_FRAGMENT_NAME = "fragment-name";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private BusNumbersRecyclerViewAdapter mBusNumbersRecyclerViewAdapter;
    private RelativeLayout mErrorLayout;
    private RecyclerView mRecyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BusNumbersFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BusNumbersFragment newInstance(int columnCount, String fragmentName) {
        BusNumbersFragment fragment = new BusNumbersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busnumbers_list, container, false);
        mErrorLayout = (RelativeLayout) view.findViewById(R.id.rl_error_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        // Set the adapter

        Context context = view.getContext();
        DividerItemDecoration dividerItemDecoration;
        if (mColumnCount <= 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

            dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                    linearLayoutManager.getOrientation());
            mRecyclerView.setLayoutManager(linearLayoutManager);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, mColumnCount);
            dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), gridLayoutManager.getOrientation());
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }

        mBusNumbersRecyclerViewAdapter = new BusNumbersRecyclerViewAdapter(new BusNumbers(), mListener);


        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mBusNumbersRecyclerViewAdapter);

        new BusNumbersHelper(getContext()).getBusNumbers(this, mErrorLayout, mRecyclerView);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_bus_numbers,menu);

    }

    private void updateList(BusNumbers busNumbersData) {
        if (mErrorLayout.getVisibility() == View.VISIBLE) {
            ViewUtils.hideTheViews(mErrorLayout);
            ViewUtils.showTheViews(mRecyclerView);
        }
        mBusNumbersRecyclerViewAdapter.setList(busNumbersData);
    }


    @Override
    public void onBusNumbersResponseReceived(BusNumbers busNumbersData) {
        DialogUtils.hideProgressDialog();
        if (busNumbersData != null && busNumbersData.getData().size() != 0) {
            updateList(busNumbersData);
        }
    }

    @Override
    public void onFailure() {

        DialogUtils.hideProgressDialog();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String item);
    }
}
