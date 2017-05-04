package com.example.venkateshkashyap.mysuru_commute.NetworkManager;

import com.example.venkateshkashyap.mysuru_commute.models.BusNumbers;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by Srivatsa M
 */
public interface ApiService {

    //URL for getting all bus stops
    String getAllBusStopsUrl = "/get_all_bus_stops";

    //URL for getting all bus numbers
    String getAllBusNumbersUrl = "/get_all_bus_numbers";

    @GET(getAllBusStopsUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusStops> getAllBusStops();

    @GET(getAllBusNumbersUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusNumbers> getAllBusNumbers();

}
