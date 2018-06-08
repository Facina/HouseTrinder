package com.example.android.housetrinder.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    public static ArrayList<PreferenceType> getListInterest(String string) {
        ArrayList<PreferenceType> lista = new ArrayList<PreferenceType>();
        int idPrevious =0;
        int j=0;

        String jsonData = string;
           try {
               JSONArray js = new JSONArray(jsonData);
               JSONObject object = null;

               ArrayList<PreferenceItem> listaItem = null;
               for (int i = 0; i < js.length(); i++) {
                   PreferenceItem item = new PreferenceItem();

                   object = js.getJSONObject(i);
                   if (i == 0) {
                       idPrevious = object.getInt("idPreferenceType");
                       lista.add(new PreferenceType());
                       lista.get(j).setIdPreferenceType(object.getInt("idPreferenceType"));
                       lista.get(j).setName(object.getString("name"));
                       listaItem = new ArrayList<>();

                   }
                   if (object.getInt("idPreferenceType") != idPrevious) {

                       lista.get(j).setAllItemsInSection(listaItem);
                       listaItem = new ArrayList<>();
                       idPrevious = object.getInt("idPreferenceType");
                       j++;
                       lista.add(new PreferenceType());
                       lista.get(j).setIdPreferenceType(object.getInt("idPreferenceType"));
                       lista.get(j).setName(object.getString("name"));

                   }
                   item.setNamePreferenceItem(object.getString("namePreferenceItem"));
                   item.setIdPreferenceItem(object.getInt("idPreferenceItem"));
                   if (!object.isNull("urlPhotoItem")) {
                       item.setResourceDrawable(object.getString("urlPhotoItem"));
                   }
                   listaItem.add(item);
               }
               lista.get(j).setAllItemsInSection(listaItem);


           } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;

    }
}
