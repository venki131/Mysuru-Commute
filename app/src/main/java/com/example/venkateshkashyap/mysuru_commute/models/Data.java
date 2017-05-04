package com.example.venkateshkashyap.mysuru_commute.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("bus_num")
@Expose
private String busNum;
@SerializedName("route")
@Expose
private List<String> route = null;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getBusNum() {
return busNum;
}

public void setBusNum(String busNum) {
this.busNum = busNum;
}

public List<String> getRoute() {
return route;
}

public void setRoute(List<String> route) {
this.route = route;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

}