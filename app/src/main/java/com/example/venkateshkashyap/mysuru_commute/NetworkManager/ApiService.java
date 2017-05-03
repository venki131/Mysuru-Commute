package com.example.venkateshkashyap.mysuru_commute.NetworkManager;

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

    //  Earlier URL - String newsUrl = "news/news.jsp";
    //URL for News
    String newsUrl = "/m/api/news/list.jsp";

    //    http://www.saintjoehigh.com/m/news/news_item.jsp?REC_ID=407369&id=1
    //http://www.educationalnetworks.net/m/api/news/item.jsp


    //URL for News Details
    String newsDetailsUrl = "/m/api/news/item.jsp";

    //Earlier URL - "bbmessages/bbmessages.jsp";
    //URL for BulletinBoard
    //http://www.educationalnetworks.net/m/api/bulletin_board/list.jsp

    String bulletinUrl = "/m/api/bulletin_board/list.jsp";

    //http://www.educationalnetworks.net/m/api/bulletin_board/item.jsp

    //URL for BulletinBoard
    String bulletinDetailsUrl = "/m/api/bulletin_board/item.jsp";

    //URL for Links
    String linksUrl = "/m/api/links/list.jsp";

    //URL for Videos
    String videosUrl = "/m/video/list.jsp";

    //URL for Verifying School Code
    // Earlier URL - "/m/api/verify_code.jsp";

    String verifyCodeUrl = "/m/api/verify.jsp";

    //URL for Events
    String eventsUrl = "/m/events/events.jsp";

    //URL for Events
    String eventsDetailsUrl = "/m/events/show_event.jsp";

    //URL for Staff
    String staffUrl = "/m/staff/staff.jsp";

    //Earlier URL - "/m/album2/albums.jsp"

    //URL for PhotoAlbum
    String photoAlbumUrl = "/m/api/photo_album/list.jsp";

    //URL for Bell Schedules
    String bellSchedulesUrl = "/m/api/bell_schedules/list.jsp";

    //URL for Spotlight
    String spotlightUrl = "/m/api/spotlight/list.jsp";

    //URL for departments
    String departmentsURL = "/m/api/departments/list.jsp";

    //URL for department details
    String departmentDetailsUrl = "/m/api/pages/list.jsp";

    //URL for Classes
    String classesURL = "/m/api/classes/list.jsp";

    //URL for Homework
    String homeworkURL = "/m/api/homework/list.jsp";


    //URL for sending message to Staff
    String postEmailtoStaffURL = "/m/api/staff/contact.jsp";

    //URL for Dashboard
    String dashboardURL ="/m/api/dashboard.jsp";


    @GET(getAllBusStopsUrl)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<BusStops> getAllBusStops();


}
