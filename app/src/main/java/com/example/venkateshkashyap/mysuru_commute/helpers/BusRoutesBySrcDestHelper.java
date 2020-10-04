package com.example.venkateshkashyap.mysuru_commute.helpers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.venkateshkashyap.mysuru_commute.NetworkManager.NetworkManager;
import com.example.venkateshkashyap.mysuru_commute.R;
import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;
import com.example.venkateshkashyap.mysuru_commute.Utils.NetworkUtil;
import com.example.venkateshkashyap.mysuru_commute.Utils.Utils;
import com.example.venkateshkashyap.mysuru_commute.models.BusRoutes;

import androidx.core.content.ContextCompat;
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

    public void getRoutesBySrcDest(final BusRoutesBySrcDestHelper.OnBusRoutesResponseReceived onBusRoutesResponseReceived, final View view, final View recyclerView, final String source, final String destination) {
        mOnBusRoutesResponseReceived = mOnBusRoutesResponseReceived;

        if(NetworkUtil.isConnectionAvailable(mContext)) {
            DialogUtils.displayProgressDialog(mContext);
            NetworkManager.getInstance().getRoutesBySrcDest((Callback<BusRoutes>) onBusRoutesResponseReceived,source,destination);

        }else{

                Utils.setErrorView(recyclerView,view,mContext, ContextCompat.getDrawable(mContext, R.drawable.ic_sleep),mContext.getString(R.string.no_network_title),mContext.getString(R.string.no_network_sub_text),mContext.getString(R.string.try_again),false);

                TextView mTextViewTryAgain = (TextView) view.findViewById(R.id.text_try_again);

                mTextViewTryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getRoutesBySrcDest(onBusRoutesResponseReceived,view, recyclerView,source,destination);
                    }
                });
        }

    }

    private Callback<BusRoutes> mBusRoutesCallback = new BaseCallback<BusRoutes>(mContext) {

        @Override
        public void onResponse(Call<BusRoutes> call, Response<BusRoutes> response) {
            DialogUtils.hideProgressDialog();
            BusRoutes busRoutes = new BusRoutes();
            if (response != null && response.body() != null) {
              busRoutes.setData(response.body().getData());
                mOnBusRoutesResponseReceived.onBusRoutesResponseReceived(busRoutes);
                Log.d(TAG, "onResponse: " + response.code());
            }else {
            }
        }

        @Override
        public void onFailure(Call<BusRoutes> call, Throwable t) {
            super.onFailure(call, t);
        }
    };
}
