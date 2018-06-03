package com.example.android.housetrinder.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable{
    private String receiverName;
    private String message;
    private long timeSeen;
    private boolean wasSeen;

    protected Contacts(Parcel in) {
        receiverName = in.readString();
        message = in.readString();
        timeSeen = in.readLong();
        wasSeen = in.readByte() != 0;
        urlProfile = in.readString();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    private String urlProfile;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeSeen() {
        return timeSeen;
    }

    public void setTimeSeen(long timeSeen) {
        this.timeSeen = timeSeen;
    }

    public boolean isWasSeen() {
        return wasSeen;
    }

    public void setWasSeen(boolean wasSeen) {
        this.wasSeen = wasSeen;
    }

    Contacts(){

    }

    public Contacts(String name, String lastMessage, String urlProfile){
        setMessage(lastMessage);
        setReceiverName(name);
        setUrlProfile(urlProfile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(receiverName);
        parcel.writeString(message);
        parcel.writeLong(timeSeen);
        parcel.writeByte((byte) (wasSeen ? 1 : 0));
        parcel.writeString(urlProfile);
    }
}
