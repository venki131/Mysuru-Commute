package com.example.venkateshkashyap.mysuru_commute.helpers;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.venkateshkashyap.mysuru_commute.NetworkManager.NetworkManager;
import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.NetworkUtil;
import com.example.venkateshkashyap.mysuru_commute.listeners.OkClickListener;
import com.example.venkateshkashyap.mysuru_commute.models.BusRoutes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Venkatesh Kashyap on 5/4/2017.
 */

public class BusRoutesBySrcDestHelper extends BaseHelper{

    private BusRoutesBySrcDestHelper.OnBusRoutesResponseReceived mOnBusRoutesResponseReceived;

    public BusRoutesBySrcDestHelper(Context context) {
        super(context);
    }

    public interface OnBusRoutesResponseReceived {
        void onBusRoutesResponseReceived(BusRoutes busRoutesData);
        void onFailure();
    }


    private static final String TAG = BusRoutesBySrcDestHelper.class.getSimpleName();

    public void getRoutesBySrcDest(final BusRoutesBySrcDestHelper.OnBusRoutesResponseReceived onBusRoutesResponseReceived, final String source, final String destination) {
        mOnBusRoutesResponseReceived = onBusRoutesResponseReceived;

        if(NetworkUtil.isConnectionAvailable(mContext)) {
            DialogUtils.displayProgressDialog(mContext);
            if (!TextUtils.isEmpty(destination)) {
                NetworkManager.getInstance().getRoutesBySrcDest(mBusRoutesCallback, source, destination);
            }else{
                NetworkManager.getInstance().getRoutesBySrc(mBusRoutesCallback, source);
            }

        }else{
            DialogUtils.showCustomAlertDialog(mContext, ContextCompat.getDrawable(mContext,R.drawable.ic_sleep), mContext.getString(R.string.no_network_title),  mContext.getString(R.string.no_network_sub_text),  mContext.getString(R.string.try_again), new OkClickListener() {
                @Override
                public void onOkClicked() {
                    getRoutesBySrcDest(onBusRoutesResponseReceived, source, destination);
                }

                @Override
                public void onCancelClicked() {

                }
            });

        }

    }

    private final Callback<BusRoutes> mBusRoutesCallback = new BaseCallback<BusRoutes>(mContext) {

        @Override
        public void onResponse(Call<BusRoutes> call, Response<BusRoutes> response) {
            DialogUtils.hideProgressDialog();
            BusRoutes busRoutes = new BusRoutes();
            if (response.body() != null) {
                busRoutes.setData(response.body().getData());
                mOnBusRoutesResponseReceived.onBusRoutesResponseReceived(busRoutes);
                Log.d(TAG, "onResponse: " + response.code());
            }
        }

        @Override
        public void onFailure(Call<BusRoutes> call, Throwable t) {
            super.onFailure(call, t);
        }
    };
}