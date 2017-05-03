package com.example.venkateshkashyap.mysuru_commute.helpers;

import android.content.Context;
import android.util.Log;

import com.example.venkateshkashyap.mysuru_commute.Utils.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Srivatsa M.
 *
 */
public class BaseCallback<T> implements Callback<T> {
    private static final String TAG = BaseCallback.class.getSimpleName();
    private final Context mContext;

    public BaseCallback(Context context) {
        mContext = context;
    }


    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "onFailure: ");
        DialogUtils.hideProgressDialog();
    }
}
