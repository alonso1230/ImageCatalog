package com.example.imagecatalog.model.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data implements Parcelable {

    @SerializedName("error")
    private String error;
    @SerializedName("request")
    private String request;
    @SerializedName("method")
    private String method;

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("link")
    private String link;
    @SerializedName("datetime")
    private Long datetime;
    @SerializedName("views")
    private Integer views;
    @SerializedName("comment_count")
    private Integer commentCount;
    @SerializedName("favorite_count")
    private Integer favoriteCount;
    @SerializedName("images")
    private ArrayList<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public Data() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeString(this.request);
        dest.writeString(this.method);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.link);
        dest.writeValue(this.datetime);
        dest.writeValue(this.views);
        dest.writeValue(this.commentCount);
        dest.writeValue(this.favoriteCount);
        dest.writeTypedList(this.images);
    }

    protected Data(Parcel in) {
        this.error = in.readString();
        this.request = in.readString();
        this.method = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.link = in.readString();
        this.datetime = (Long) in.readValue(Long.class.getClassLoader());
        this.views = (Integer) in.readValue(Integer.class.getClassLoader());
        this.commentCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.favoriteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.images = in.createTypedArrayList(Image.CREATOR);
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
