package com.example.venkateshkashyap.mysuru_commute.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusRoutes {

@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List<String> data = null;
@SerializedName("status_code")
@Expose
private Integer statusCode;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public List<String> getData() {
return data;
}

public void setData(List<String> data) {
this.data = data;
}

public Integer getStatusCode() {
return statusCode;
}

public void setStatusCode(Integer statusCode) {
this.statusCode = statusCode;
}

}