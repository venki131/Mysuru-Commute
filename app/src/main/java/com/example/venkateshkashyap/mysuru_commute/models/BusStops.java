package com.example.venkateshkashyap.mysuru_commute.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Venkatesh Kashyap on 5/4/2017.
 */

public class BusStops implements Parcelable{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<String> data = null;
    @SerializedName("status_code")
    @Expose
    private int statusCode;


    protected BusStops(Parcel in) {
        message = in.readString();
        data = in.createStringArrayList();
        statusCode = in.readInt();
    }

    public static final Creator<BusStops> CREATOR = new Creator<BusStops>() {
        @Override
        public BusStops createFromParcel(Parcel in) {
            return new BusStops(in);
        }

        @Override
        public BusStops[] newArray(int size) {
            return new BusStops[size];
        }
    };

    public BusStops() {
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeStringList(data);
        parcel.writeInt(statusCode);
    }
}
