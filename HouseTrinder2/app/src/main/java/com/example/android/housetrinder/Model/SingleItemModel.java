package com.example.android.housetrinder.Model;

public class SingleItemModel {


    private String name;

    public String getResourceDrawable() {
        return resourceDrawable;
    }

    public void setResourceDrawable(String resourceDrawable) {
        this.resourceDrawable = resourceDrawable;
    }

    private String resourceDrawable;


    public SingleItemModel() {
    }

    public SingleItemModel(String name, String resourceDrawable) {
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