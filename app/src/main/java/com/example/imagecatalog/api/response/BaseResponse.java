package com.example.imagecatalog.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.imagecatalog.model.dataclass.Data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseResponse implements Parcelable {

    private Data dataObject;
    private ArrayList<Data> dataArray;
    @SerializedName("success")
    private boolean success;
    @SerializedName("status")
    private int status;

    public Data getDataObject() {
        return dataObject;
    }

    public void setDataObject(Data dataObject) {
        this.dataObject = dataObject;
    }

    public ArrayList<Data> getDataArray() {
        return dataArray;
    }

    public void setDataArray(ArrayList<Data> dataArray) {
        this.dataArray = dataArray;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseResponse() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.dataObject, flags);
        dest.writeTypedList(this.dataArray);
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeInt(this.status);
    }

    protected BaseResponse(Parcel in) {
        this.dataObject = in.readParcelable(Data.class.getClassLoader());
        this.dataArray = in.createTypedArrayList(Data.CREATOR);
        this.success = in.readByte() != 0;
        this.status = in.readInt();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel source) {
            return new BaseResponse(source);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };
}
