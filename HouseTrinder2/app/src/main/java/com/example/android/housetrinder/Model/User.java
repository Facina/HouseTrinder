package com.example.android.housetrinder.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User implements Parcelable {


    private int idUser;
    private String nameUser;
    private String urlProfile;
    private String email;
    private String userPassword;
    private Date birthDate;
    private String gender;
    private String type;
    private String status;
    private Date lastSeen;


    public User(){

    }

    protected User(Parcel in) {
        idUser = in.readInt();
        nameUser = in.readString();
        urlProfile = in.readString();
        email = in.readString();
        userPassword = in.readString();
        gender = in.readString();
        type = in.readString();
        status = in.readString();
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

    public ArrayList<User> JSON2Array(String jsonString) {
        String jsonData = jsonString;
        ArrayList<User> lista = new ArrayList<>();
        try {
            JSONArray js = new JSONArray(jsonData);
            JSONObject object = null;

            for (int i = 0; i < js.length(); i++) {
                User u = new User();
                object = js.getJSONObject(i);
                u.setEmail(object.getString("email"));
                u.setNameUser(object.getString("nameUser"));
                u.setUrlProfile(object.getString("urlProfile"));
                u.setIdUser(object.getInt("idUser"));

                if(!object.isNull("gender") ){
                    u.setGender(object.getString("gender"));
                }
                if(!object.isNull("status")){
                    u.setStatus(object.getString("status"));
                }
                if(!object.isNull("userPassword")){
                    u.setUserPassword(object.getString("userPassword"));
                }
                if(!object.isNull("type")){
                    u.setType(object.getString("type"));
                }
                if(!object.isNull("birthDate") && !object.getString("birthDate").equals("")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        u.setBirthDate( new Date(dateFormat.parse(object.getString("birthDate")).getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(!object.isNull("lastSeen") && !object.getString("lastSeen").equals("")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        u.setLastSeen( new Date(dateFormat.parse(object.getString("lastSeen")).getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                lista.add(u);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;

    }


    public User(String jsonString) {
        String jsonData = jsonString;
        if(jsonString!=null) {
            try {
                JSONArray js = new JSONArray(jsonData);
                JSONObject object = null;

                for (int i = 0; i < js.length(); i++) {
                    object = js.getJSONObject(i);
                    setEmail(object.getString("email"));
                    setNameUser(object.getString("nameUser"));
                    setUrlProfile(object.getString("urlProfile"));
                    setIdUser(object.getInt("idUser"));

                    if (!object.isNull("gender")) {
                        setGender(object.getString("gender"));
                    }
                    if (!object.isNull("status")) {
                        setStatus(object.getString("status"));
                    }
                    if (!object.isNull("userPassword")) {
                        setUserPassword(object.getString("userPassword"));
                    }
                    if (!object.isNull("type")) {
                        setType(object.getString("type"));
                    }
                    if (!object.isNull("birthDate")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            setBirthDate(new Date(dateFormat.parse(object.getString("birthDate")).getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!object.isNull("lastSeen")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            setLastSeen(new Date(dateFormat.parse(object.getString("lastSeen")).getTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }



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
        parcel.writeString(gender);
        parcel.writeString(type);
        parcel.writeString(status);
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
