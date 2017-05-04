package com.example.venkateshkashyap.mysuru_commute.helpers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.venkateshkashyap.mysuru_commute.NetworkManager.NetworkManager;
import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.NetworkUtil;
import com.example.venkateshkashyap.mysuru_commute.Utils.Utils;
import com.example.venkateshkashyap.mysuru_commute.models.BusRoutes;
import com.example.venkateshkashyap.mysuru_commute.models.RouteDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Venkatesh Kashyap on 5/4/2017.
 */

public class RouteDetailslByBusNumberHelper extends BaseHelper{

    private RouteDetailslByBusNumberHelper.OnRouteDetailsResponseReceived mOnRouteDetailsResponseReceived;

    public RouteDetailslByBusNumberHelper(Context context) {
        super(context);
    }

    public interface OnRouteDetailsResponseReceived {
        void onRouteDetailsResponseReceived(RouteDetails routeDetails);
        void onFailure();
    }


    private static final String TAG = RouteDetailslByBusNumberHelper.class.getSimpleName();

    public void getRoutesByBusNum(final RouteDetailslByBusNumberHelper.OnRouteDetailsResponseReceived onRouteDetailsResponseReceived, final View view, final View recyclerView, final String busNum) {
        mOnRouteDetailsResponseReceived = onRouteDetailsResponseReceived;

        if(NetworkUtil.isConnectionAvailable(mContext)) {
            DialogUtils.displayProgressDialog(mContext);
            NetworkManager.getInstance().getRoutesByBusNum(mRouteDetailsCallback,busNum);

        }else{

                Utils.setErrorView(recyclerView,view,mContext, ContextCompat.getDrawable(mContext, R.drawable.ic_sleep),mContext.getString(R.string.no_network_title),mContext.getString(R.string.no_network_sub_text),mContext.getString(R.string.try_again),false);

                TextView mTextViewTryAgain = (TextView) view.findViewById(R.id.text_try_again);

                mTextViewTryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getRoutesByBusNum(onRouteDetailsResponseReceived,view, recyclerView,busNum);
                    }
                });
        }

    }

    private Callback<RouteDetails> mRouteDetailsCallback = new BaseCallback<RouteDetails>(mContext) {

        @Override
        public void onResponse(Call<RouteDetails> call, Response<RouteDetails> response) {
            DialogUtils.hideProgressDialog();
            RouteDetails routeDetails = new RouteDetails();
            if (response != null && response.body() != null) {
                routeDetails.setRoutesList(response.body().getRoutesList());
                mOnRouteDetailsResponseReceived.onRouteDetailsResponseReceived(routeDetails);
                Log.d(TAG, "onResponse: " + response.code());
            }else {
            }
        }

        @Override
        public void onFailure(Call<RouteDetails> call, Throwable t) {
            super.onFailure(call, t);
        }
    };
}
