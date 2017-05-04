package com.example.venkateshkashyap.mysuru_commute.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RouteDetails {

@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("status_code")
@Expose
private Integer statusCode;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Data getRoutesList() {
return data;
}

public void setRoutesList(Data data) {
this.data = data;
}

public Integer getStatusCode() {
return statusCode;
}

public void setStatusCode(Integer statusCode) {
this.statusCode = statusCode;
}

}