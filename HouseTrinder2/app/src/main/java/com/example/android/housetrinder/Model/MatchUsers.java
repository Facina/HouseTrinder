package com.example.android.housetrinder.Model;

public class MatchUsers {


    private int idMatch;
    private int userA;
    private int userB;
    private double matchRate;
    private boolean connected;


    MatchUsers(){

    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public int getUserA() {
        return userA;
    }

    public void setUserA(int userA) {
        this.userA = userA;
    }

    public int getUserB() {
        return userB;
    }

    public void setUserB(int userB) {
        this.userB = userB;
    }

    public double getMatchRate() {
        return matchRate;
    }

    public void setMatchRate(double matchRate) {
        this.matchRate = matchRate;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
