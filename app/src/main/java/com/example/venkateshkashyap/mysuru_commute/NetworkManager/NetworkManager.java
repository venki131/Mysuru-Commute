package com.example.venkateshkashyap.mysuru_commute.NetworkManager;

import android.util.Log;

import com.example.venkateshkashyap.mysuru_commute.constants.Constants;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Srivatsa M
 */
public class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();
    private static NetworkManager ourInstance;
    private ApiService apiService;

    public static NetworkManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new NetworkManager();
        }
        return ourInstance;
    }


    private Retrofit getRetroFit() {
        Retrofit retrofit = null;
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }


    public void getAllBusStops(Callback<BusStops> busStopsCallBack) {
        Log.d(TAG, "getAllBusStops");
        ApiService apiService = getApiService();
        Call<BusStops> busStops = apiService.getAllBusStops();
        busStops.enqueue(busStopsCallBack);
    }

    public void resetApiService() {
        this.apiService = null;
    }

    private ApiService getApiService() {

        if (apiService == null)
            apiService = getRetroFit().create(ApiService.class);

        return apiService;
    }


}
