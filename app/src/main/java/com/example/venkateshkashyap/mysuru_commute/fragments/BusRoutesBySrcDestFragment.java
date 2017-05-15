package com.example.venkateshkashyap.mysuru_commute.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.venkateshkashyap.mysuru_commute.BusNumbersActivity;
import com.example.venkateshkashyap.mysuru_commute.MainActivity;
import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.Utils.Utils;
import com.example.venkateshkashyap.mysuru_commute.adapters.AutoCompleteStopsAdapter;
import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.helpers.BusRoutesBySrcDestHelper;
import com.example.venkateshkashyap.mysuru_commute.models.BusNumbers;
import com.example.venkateshkashyap.mysuru_commute.models.BusRoutes;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BusRoutesBySrcDestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BusRoutesBySrcDestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusRoutesBySrcDestFragment extends Fragment implements BusRoutesBySrcDestHelper.OnBusRoutesResponseReceived{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "source";
    private static final String ARG_PARAM2 = "destination";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private AutoCompleteTextView mAutoCompleteTextViewSource, mAutoCompleteTextViewDestination;
    private View.OnTouchListener mOnTouchListener;
    private Button mButtonSearch;
    private Boolean mSourceValueSet = false;

    public BusRoutesBySrcDestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusRoutesBySrcDestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusRoutesBySrcDestFragment newInstance(String param1, String param2) {
        BusRoutesBySrcDestFragment fragment = new BusRoutesBySrcDestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bus_routes_by_src_dest, container, false);
        final AutoCompleteStopsAdapter adapter = new AutoCompleteStopsAdapter(getContext(), ((MainActivity)getActivity()).getmBusStops().getData());
        // Inflate the layout for this fragment

        mAutoCompleteTextViewSource = (AutoCompleteTextView) view.findViewById(R.id.auto_complete_txt_source);
        mAutoCompleteTextViewDestination = (AutoCompleteTextView) view.findViewById(R.id.auto_complete_txt_destination);

        mButtonSearch = (Button) view.findViewById(R.id.btn_search);

        mAutoCompleteTextViewSource.setThreshold(1);//will start working from first character
        mAutoCompleteTextViewSource.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        mAutoCompleteTextViewSource.setTextColor(Color.BLACK);

        mAutoCompleteTextViewDestination.setThreshold(1);//will start working from first character
        mAutoCompleteTextViewDestination.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        mAutoCompleteTextViewDestination.setTextColor(Color.BLACK);

        mOnTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (view.getId() == R.id.auto_complete_txt_source) {
                    if (mAutoCompleteTextViewSource.getText().toString().length() == 0) {
                        mAutoCompleteTextViewSource.showDropDown();
                    }
                    return false;
                } else if (view.getId() == R.id.auto_complete_txt_destination) {
                    mAutoCompleteTextViewDestination.showDropDown();
                }
                return false;
            }
        };

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSourceValueSet){
                    Utils.hideKeyboard(getActivity());
                    new BusRoutesBySrcDestHelper(getContext()).getRoutesBySrcDest(BusRoutesBySrcDestFragment.this, mAutoCompleteTextViewSource.getText().toString(), mAutoCompleteTextViewDestination.getText().toString());
                }
            }
        });



        mAutoCompleteTextViewSource.setOnTouchListener(mOnTouchListener);
        mAutoCompleteTextViewDestination.setOnTouchListener(mOnTouchListener);

        mAutoCompleteTextViewSource.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStop = adapter.getItem(position);
                mAutoCompleteTextViewSource.setText(selectedStop);
                mAutoCompleteTextViewSource.setSelection(selectedStop.length());
                mSourceValueSet = true;
                mButtonSearch.setEnabled(true);
            }
        });


        mAutoCompleteTextViewDestination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStop = adapter.getItem(position);
                mAutoCompleteTextViewDestination.setText(selectedStop);
                mAutoCompleteTextViewDestination.setSelection(selectedStop.length());
            }
        });


        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            if(TextUtils.isEmpty(mAutoCompleteTextViewSource.getText().toString())) {
                mAutoCompleteTextViewSource.requestFocus();
                mAutoCompleteTextViewSource.requestFocusFromTouch();
                mAutoCompleteTextViewSource.performClick();
            }else if(TextUtils.isEmpty(mAutoCompleteTextViewDestination.getText().toString())){
                mAutoCompleteTextViewDestination.requestFocus();
                mAutoCompleteTextViewDestination.requestFocusFromTouch();
                mAutoCompleteTextViewSource.performClick();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();
            //throw new RuntimeException(context.toString()
                    //+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onBusRoutesResponseReceived(BusRoutes busRoutesData) {

        if(busRoutesData!=null && busRoutesData.getData()!=null && busRoutesData.getData().size()!=0){
            Intent intent = new Intent(getActivity(), BusNumbersActivity.class);
            BusNumbers busNumbers = new BusNumbers();
            busNumbers.setData(busRoutesData.getData());
            intent.putExtra(Constants.BundleIDs.SRC_DEST_BUSES_BUNDLE_ID,busNumbers);
            String srcDestString = mAutoCompleteTextViewSource.getText().toString();

            if(!TextUtils.isEmpty(mAutoCompleteTextViewDestination.getText().toString())){
                srcDestString+= " - "+mAutoCompleteTextViewDestination.getText().toString();
            }
            intent.putExtra(Constants.BundleIDs.SRC_DEST_BUNDLE_ID,srcDestString);

            startActivity(intent);
        }else{
            Snackbar.make(mButtonSearch, "No Buses in this Route! Try changing the source",Snackbar.LENGTH_LONG).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Do Nothing
                }
            }).show();
        }

    }

    @Override
    public void onFailure() {

    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
