package com.example.businessmanagement;

import android.os.Parcel;
import android.os.Parcelable;

public class UserID implements Parcelable {
    private String id;
    private String password;

    public UserID(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserID(Parcel src) {
        id = src.readString();
        password = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public UserID createFromParcel(Parcel in) {
            return new UserID(in);
        }
        public UserID[] newArray(int size) {
            return new UserID[size];
        }
    } ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(password);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
