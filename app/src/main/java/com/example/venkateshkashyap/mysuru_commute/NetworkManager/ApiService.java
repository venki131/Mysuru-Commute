package com.example.venkateshkashyap.mysuru_commute.NetworkManager;

import com.example.venkateshkashyap.mysuru_commute.models.BusNumbers;
import com.example.venkateshkashyap.mysuru_commute.models.BusRoutes;
import com.example.venkateshkashyap.mysuru_commute.models.BusStops;
import com.example.venkateshkashyap.mysuru_commute.models.RouteDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Srivatsa M
 */
public interface ApiService {

    //URL for getting all bus stops
    String getAllBusStopsUrl = "/get_all_bus_stops";

    //URL for getting all bus numbers
    String getAllBusNumbersUrl = "/get_all_bus_numbers";

    //URL for getting all bus routes by source and destination
    String getRoutesBySrcDestUrl = "/get_bus_numbers_by_source_and_destination";

    //URL for getting all bus routes by source
    String getRoutesBySrcUrl = "list_of_bus_on_bus_stop";

    //URL for getting all routes
    String getRoutesByBusNumUrl = "/get_routes_by_bus_num";

    @GET(getAllBusStopsUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusStops> getAllBusStops();

    @GET(getAllBusNumbersUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusNumbers> getAllBusNumbers();

    @GET(getRoutesBySrcDestUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusRoutes> getRoutesBySrcDest(@Query("source")String source,@Query("destination")String destination);

    @GET(getRoutesBySrcUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusRoutes> getRoutesBySrc(@Query("bus_stop") String busStop);

    @GET(getRoutesByBusNumUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<RouteDetails> getRoutesByBusNum(@Query("bus_num") String busNumber);
}
