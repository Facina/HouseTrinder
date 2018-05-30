package com.example.android.housetrinder.Model;

public class SingleItemModel {


    private String name;

    public int getResourceDrawable() {
        return resourceDrawable;
    }

    public void setResourceDrawable(int resourceDrawable) {
        this.resourceDrawable = resourceDrawable;
    }

    private int resourceDrawable;


    public SingleItemModel() {
    }

    public SingleItemModel(String name, int resourceDrawable) {
        this.name = name;
        this.resourceDrawable = resourceDrawable;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}