package com.example.android.housetrinder.Model;

public class PreferenceItem {

    private int idPreferenceItem;

    private String namePreferenceItem;

    public String getResourceDrawable() {
        return resourceDrawable;
    }

    public void setResourceDrawable(String resourceDrawable) {
        this.resourceDrawable = resourceDrawable;
    }

    private String resourceDrawable;


    public PreferenceItem() {
    }

    public PreferenceItem(String name, String resourceDrawable) {
        this.namePreferenceItem = name;
        this.resourceDrawable = resourceDrawable;
    }



    public String getName() {
        return namePreferenceItem;
    }

    public void setName(String name) {
        this.namePreferenceItem = name;
    }



}