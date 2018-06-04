package com.example.android.housetrinder.Model;

import java.util.ArrayList;

public class PreferenceType {


    public int getIdPreferenceType() {
        return idPreferenceType;
    }

    public void setIdPreferenceType(int idPreferenceType) {
        this.idPreferenceType = idPreferenceType;
    }

    private int idPreferenceType;
    private String name;
    private ArrayList<PreferenceItem> allItemsInSection;


    public PreferenceType() {

    }
    public PreferenceType(String name, ArrayList<PreferenceItem> allItemsInSection) {
        this.name = name;
        this.allItemsInSection = allItemsInSection;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PreferenceItem> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<PreferenceItem> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


}
