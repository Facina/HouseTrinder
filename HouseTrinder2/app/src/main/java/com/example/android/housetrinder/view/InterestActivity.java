package com.example.android.housetrinder.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;

import com.example.android.housetrinder.Control.RecyclerViewDataAdapter;
import com.example.android.housetrinder.Model.SectionDataModel;
import com.example.android.housetrinder.Model.SingleItemModel;
import com.example.android.housetrinder.R;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    ArrayList<SectionDataModel> allSampleData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        allSampleData = new ArrayList<SectionDataModel>();
        createData();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.recyclerView_interest);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);



    }



    public void createData() {

        String[] theme = getResources().getStringArray(R.array.theme);

        for (int i = 0; i < theme.length; i++) {

            SectionDataModel dataModel = new SectionDataModel();

            dataModel.setHeaderTitle(theme[i]);

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
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
