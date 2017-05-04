package com.example.venkateshkashyap.mysuru_commute.helpers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venkateshkashyap.mysuru_commute.NetworkManager.NetworkManager;
import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.NetworkUtil;
import com.example.venkateshkashyap.mysuru_commute.Utils.Utils;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Venkatesh Kashyap on 5/4/2017.
 */

public class BusStopsHelper extends BaseHelper{

    private BusStopsHelper.OnBusStopsResponseReceived mOnBusStopsResponseReceived;

    public BusStopsHelper(Context context) {
        super(context);
    }

    public interface OnBusStopsResponseReceived {
        void onBusStopsResponseReceived(BusStops busStopsData);
        void onFailure();
    }


    private static final String TAG = BusStopsHelper.class.getSimpleName();

    public void getBusStops(final BusStopsHelper.OnBusStopsResponseReceived onBusStopsResponseReceived) {
        mOnBusStopsResponseReceived = onBusStopsResponseReceived;

        if(NetworkUtil.isConnectionAvailable(mContext)) {
            NetworkManager.getInstance().getAllBusStops(mBusStopsCallback);
        }else{
            mOnBusStopsResponseReceived.onFailure();
            Toast.makeText(mContext, "Please Check your Internet Connection and Try Again!", Toast.LENGTH_SHORT).show();
        }

    }

    private Callback<BusStops> mBusStopsCallback = new BaseCallback<BusStops>(mContext) {

        @Override
        public void onResponse(Call<BusStops> call, Response<BusStops> response) {
            DialogUtils.hideProgressDialog();
            BusStops busStops = new BusStops();
            if (response != null && response.body() != null) {
              busStops.setData(response.body().getData());
                mOnBusStopsResponseReceived.onBusStopsResponseReceived(busStops);
                Log.d(TAG, "onResponse: " + response.code());
            }else {
            }
        }

        @Override
        public void onFailure(Call<BusStops> call, Throwable t) {
            super.onFailure(call, t);
        }
    };
}
