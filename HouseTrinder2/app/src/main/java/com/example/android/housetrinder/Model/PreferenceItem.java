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
        this.setNamePreferenceItem(name);
        this.setResourceDrawable(resourceDrawable);
    }



    public int getIdPreferenceItem() {
        return idPreferenceItem;
    }

    public void setIdPreferenceItem(int idPreferenceItem) {
        this.idPreferenceItem = idPreferenceItem;
    }

    public String getNamePreferenceItem() {
        return namePreferenceItem;
    }

    public void setNamePreferenceItem(String namePreferenceItem) {
        this.namePreferenceItem = namePreferenceItem;
    }
}