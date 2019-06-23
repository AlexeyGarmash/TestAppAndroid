package com.example.testapp.mvp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentPost implements Parcelable
{

    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("text")
    @Expose
    private String text;

    public CommentPost(Integer rate, String text) {
        this.rate = rate;
        this.text = text;
    }

    public final static Parcelable.Creator<CommentPost> CREATOR = new Creator<CommentPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CommentPost createFromParcel(Parcel in) {
            return new CommentPost(in);
        }

        public CommentPost[] newArray(int size) {
            return (new CommentPost[size]);
        }

    }
            ;

    protected CommentPost(Parcel in) {
        this.rate = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CommentPost() {
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(rate);
        dest.writeValue(text);
    }

    public int describeContents() {
        return 0;
    }

}