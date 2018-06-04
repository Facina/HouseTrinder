package com.example.android.housetrinder.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

public class Message implements Parcelable{


    private char receiver;
    private char sender;
    private int idMessage;
    private char viewed;
    private Date timeViewed;
    private Date timeSent;
    private int idMatch;
    private String message;


    protected Message(Parcel in) {
        receiver = (char) in.readInt();
        sender = (char) in.readInt();
        idMessage = in.readInt();
        viewed = (char) in.readInt();
        idMatch = in.readInt();
        message = in.readString();
        urlProfile = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    private String urlProfile;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





    Message(){

    }

    public Message(String name, String lastMessage, String urlProfile){
        setMessage(lastMessage);
        setUrlProfile(urlProfile);
    }


    public char getReceiver() {
        return receiver;
    }

    public void setReceiver(char receiver) {
        this.receiver = receiver;
    }

    public char getSender() {
        return sender;
    }

    public void setSender(char sender) {
        this.sender = sender;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public char getViewed() {
        return viewed;
    }

    public void setViewed(char viewed) {
        this.viewed = viewed;
    }

    public Date getTimeViewed() {
        return timeViewed;
    }

    public void setTimeViewed(Date timeViewed) {
        this.timeViewed = timeViewed;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt((int) receiver);
        parcel.writeInt((int) sender);
        parcel.writeInt(idMessage);
        parcel.writeInt((int) viewed);
        parcel.writeInt(idMatch);
        parcel.writeString(message);
        parcel.writeString(urlProfile);
    }
}
