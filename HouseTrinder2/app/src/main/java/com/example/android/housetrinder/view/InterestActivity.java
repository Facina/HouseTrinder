package com.example.android.housetrinder.view;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.android.housetrinder.Control.RecyclerViewDataAdapter;
import com.example.android.housetrinder.Model.PreferenceType;
import com.example.android.housetrinder.Model.PreferenceItem;
import com.example.android.housetrinder.R;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    ArrayList<PreferenceType> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        allSampleData = new ArrayList<PreferenceType>();
        createData();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.recyclerView_interest);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);



    }



    public void createData() {

        String[] theme = getResources().getStringArray(R.array.theme);
        TypedArray namesTag = getResources().obtainTypedArray(R.array.theme_resource);
        TypedArray drawablesTag = getResources().obtainTypedArray(R.array.theme_resource_drawable);
        String[] urls = getResources().getStringArray(R.array.esporte_URL);
        String[] names = getResources().getStringArray(R.array.esporte);
        //ArrayList<String> names = new ArrayList<>();
       // ArrayList<String> images = new ArrayList<>();
       //Log.e("drawable"," "+drawablesTag.getResourceId(0,-1));
        for (int i = 0; i < namesTag.length(); i++) {

           // names.clear();
           // images.clear();
            //String[] image = getResources().getStringArray(drawablesTag.getResourceId(i,-1));

            //TypedArray name =  getResources().obtainTypedArray(namesTag.getResourceId(i,-1));
            PreferenceType dataModel = new PreferenceType();

            dataModel.setName(theme[i]);

            ArrayList<PreferenceItem> singleItem = new ArrayList<PreferenceItem>();




            for (int j = 0; j <9; j++) {


               // images.add(image[j]);
                singleItem.add(new PreferenceItem(names[j],urls[j]));
            }

            dataModel.setAllItemsInSection(singleItem);

            allSampleData.add(dataModel);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }
}
