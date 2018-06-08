package com.example.android.housetrinder.view;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.housetrinder.Control.Connection.WebServiceUtil;
import com.example.android.housetrinder.Control.RecyclerViewDataAdapter;
import com.example.android.housetrinder.Model.PreferenceType;
import com.example.android.housetrinder.Model.User;
import com.example.android.housetrinder.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class InterestActivity extends AppCompatActivity {

    private static final int FETCH_INTEREST = 11;
    ArrayList<PreferenceType> allSampleData;
    RecyclerView my_recycler_view;
    RecyclerViewDataAdapter adapter;

    LoaderManager.LoaderCallbacks<ArrayList<PreferenceType>> callbacksFetchInterest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        allSampleData = new ArrayList<PreferenceType>();
        createData();





    }



    public void createData() {


        callbacksFetchInterest = new LoaderManager.LoaderCallbacks<ArrayList<PreferenceType>>() {
            @Override
            public Loader<ArrayList<PreferenceType>> onCreateLoader(int id, final Bundle args) {
                return new AsyncTaskLoader<ArrayList<PreferenceType>>(getApplicationContext()) {

                    @Override
                    protected void onStartLoading(){

                        forceLoad();
                    }

                    @Override
                    public ArrayList<PreferenceType> loadInBackground() {
                        Log.e("checkingEMail", "loading");



                        try {

                            OkHttpClient client = new OkHttpClient();



                            Request request = new Request.Builder()
                                    .url(WebServiceUtil.FETCH_INTEREST)
                                    .build();
                            //Log.e("Request =",request.toString());
                            Response response = client.newCall(request).execute();
                           // Log.e("Response",response.body().string());
                            if (response.isSuccessful()){

                                try {
                                    return PreferenceType.getListInterest(response.body().string());
                                }catch (Exception e){
                                    e.printStackTrace();
                                    return null;
                                }

                            }
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }

            @Override
            public void onLoadFinished(android.support.v4.content.Loader<ArrayList<PreferenceType>> loader, ArrayList<PreferenceType> user) {
                if(user == null){
                    Toast.makeText(
                            getApplicationContext(),
                            "Wrong username or password",

                            Toast.LENGTH_LONG).show();
                }
                else{

                    allSampleData = user;

                    my_recycler_view = (RecyclerView) findViewById(R.id.recyclerView_interest);

                    my_recycler_view.setHasFixedSize(true);


                    my_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                    adapter = new RecyclerViewDataAdapter(getApplicationContext(), allSampleData);


                    my_recycler_view.setAdapter(adapter);



                }



                    //Log.e("User =", data.getEmail());

                }

            @Override
            public void onLoaderReset(Loader<ArrayList<PreferenceType>> loader) {

            }
        };


        LoaderManager loaderManager = getSupportLoaderManager();
        android.support.v4.content.Loader<User> emailLoader = loaderManager.getLoader(FETCH_INTEREST);
        loaderManager.restartLoader(FETCH_INTEREST, null, callbacksFetchInterest).forceLoad();


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
