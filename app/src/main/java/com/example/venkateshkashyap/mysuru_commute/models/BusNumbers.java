package com.example.venkateshkashyap.mysuru_commute.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Venkatesh Kashyap on 5/4/2017.
 */

public class BusNumbers implements Parcelable{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<String> data = null;
    @SerializedName("status_code")
    @Expose
    private int statusCode;

    protected BusNumbers(Parcel in) {
        message = in.readString();
        data = in.createStringArrayList();
        statusCode = in.readInt();
    }

    public static final Creator<BusNumbers> CREATOR = new Creator<BusNumbers>() {
        @Override
        public BusNumbers createFromParcel(Parcel in) {
            return new BusNumbers(in);
        }

        @Override
        public BusNumbers[] newArray(int size) {
            return new BusNumbers[size];
        }
    };

    public BusNumbers() {
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
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

