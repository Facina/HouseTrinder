package com.example.android.housetrinder.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.housetrinder.Control.Connection.WebServiceUtil;
import com.example.android.housetrinder.Control.RecyclerViewMatches;
import com.example.android.housetrinder.Model.User;
import com.example.android.housetrinder.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChatFragment extends Fragment{

    public ChatFragment() {
        // Required empty public constructor
    }

    private static final int FETCH_CONTACTS = 11;


    String data = "USER_INFO";
    SharedPreferences user_account ;
    private boolean DEBBUGING = true;
    private RecyclerView recyclerView;
    private RecyclerViewMatches myAdapter;
    private ArrayList<User> listaDeContatos;
    private LoaderManager.LoaderCallbacks<ArrayList<User>> callbacksContacts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        user_account = getContext().getSharedPreferences(data, Context.MODE_PRIVATE);

        getContacts();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.matches_rv);

        //Log.e("numero de contatos",""+listaDeContatos.size()+"nome ="+ listaDeContatos.get(0).getReceiverName());



        return rootView;
    }


    private void getContacts(){


        callbacksContacts = new LoaderManager.LoaderCallbacks<ArrayList<User>>() {
            @Override
            public Loader<ArrayList<User>> onCreateLoader(int id, final Bundle args) {
                return new AsyncTaskLoader<ArrayList<User>>(getActivity()) {

                    @Override
                    protected void onStartLoading(){

                        forceLoad();
                    }

                    @Override
                    public ArrayList<User> loadInBackground() {
                        try {

                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(WebServiceUtil.FETCH_USERS)
                                    .build();
                            Response response = client.newCall(request).execute();
                            Log.e("Response",response.toString());
                            if (response.isSuccessful()){

                                try {
                                    User u = new User();
                                    return u.JSON2Array(response.body().string());
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
            public void onLoadFinished(android.support.v4.content.Loader<ArrayList<User>> loader, ArrayList<User> user) {
                Log.e("data minha",""+user);
                if(user==null){
                    Toast.makeText(
                        getActivity(),
                            "Cant load contact list",

                            Toast.LENGTH_LONG).show();
                }
                else{

                    listaDeContatos = user;
                    myAdapter = new RecyclerViewMatches(getContext(), listaDeContatos, new RecyclerViewMatches.RecyclerViewClickListener() {
                        @Override
                        public void recyclerViewListClicked(View v, int position) {
                            Intent intent = new Intent(getContext(),ChatActivity.class);
                            intent.putExtra("contact",listaDeContatos.get(position));
                            startActivity(intent);
                        }
                    });

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(myAdapter);




                }
            }

            @Override
            public void onLoaderReset(android.support.v4.content.Loader<ArrayList<User>> loader) {

            }
        };



        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        android.support.v4.content.Loader<User> emailLoader = loaderManager.getLoader(FETCH_CONTACTS);
        loaderManager.restartLoader(FETCH_CONTACTS, null, callbacksContacts).forceLoad();






        return;
    }

}
