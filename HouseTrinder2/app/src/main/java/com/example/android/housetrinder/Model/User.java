package com.example.android.housetrinder.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class User implements Parcelable {


    private int idUser;
    private String nameUser;
    private String urlProfile;
    private String email;
    private String userPassword;
    private Date birthDate;
    private char gender;
    private char type;
    private char status;
    private Date lastSeen;


    public User(){

    }

    public User(String jsonString) {
        String jsonData = jsonString;

        try {
            JSONArray js = new JSONArray(jsonData);
            JSONObject object = null;

            for (int i = 0; i < js.length(); i++) {
                object = js.getJSONObject(i);
                this.email = object.getString("email");
                this.nameUser = object.getString("nameUser");
                this.urlProfile = object.getString("urlProfile");
                this.idUser = object.getInt("idUser");

                if(!object.isNull("gender")){
                    this.gender=object.getString("gender").charAt(0);
                }
                if(!object.isNull("status")){
                    this.status=object.getString("status").charAt(0);
                }
                if(!object.isNull("userPassword")){
                    this.userPassword=object.getString("userPassword");
                }
                if(!object.isNull("type")){
                    this.type=object.getString("type").charAt(0);
                }
                if(!object.isNull("birthDate")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        this.birthDate = new Date(dateFormat.parse(object.getString("birthDate")).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(!object.isNull("lastSeen")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        this.birthDate = new Date(dateFormat.parse(object.getString("lastSeen")).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    protected User(Parcel in) {
        idUser = in.readInt();
        nameUser = in.readString();
        urlProfile = in.readString();
        email = in.readString();
        userPassword = in.readString();
        gender = (char) in.readInt();
        type = (char) in.readInt();
        status = (char) in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idUser);
        parcel.writeString(nameUser);
        parcel.writeString(urlProfile);
        parcel.writeString(email);
        parcel.writeString(userPassword);
        parcel.writeInt((int) gender);
        parcel.writeInt((int) type);
        parcel.writeInt((int) status);
    }
}
