package com.example.android.housetrinder.Model;

public class Preferences {

    private int idPreferences;
    private int organization;
    private int visits;
    private int noise;
    private int goOut;
    private boolean sharingHouse;
    private boolean sharingBedroom;
    private double minPrice;
    private double maxPrice;
    private char sharingGender;
    private int userId;


    Preferences(){

    }




    public int getIdPreferences() {
        return idPreferences;
    }

    public void setIdPreferences(int idPreferences) {
        this.idPreferences = idPreferences;
    }

    public int getOrganization() {
        return organization;
    }

    public void setOrganization(int organization) {
        this.organization = organization;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getNoise() {
        return noise;
    }

    public void setNoise(int noise) {
        this.noise = noise;
    }

    public int getGoOut() {
        return goOut;
    }

    public void setGoOut(int goOut) {
        this.goOut = goOut;
    }

    public boolean isSharingHouse() {
        return sharingHouse;
    }

    public void setSharingHouse(boolean sharingHouse) {
        this.sharingHouse = sharingHouse;
    }

    public boolean isSharingBedroom() {
        return sharingBedroom;
    }

    public void setSharingBedroom(boolean sharingBedroom) {
        this.sharingBedroom = sharingBedroom;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public char getSharingGender() {
        return sharingGender;
    }

    public void setSharingGender(char sharingGender) {
        this.sharingGender = sharingGender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
