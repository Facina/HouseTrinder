package com.example.android.housetrinder.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.housetrinder.Control.RecyclerViewAdapter;
import com.example.android.housetrinder.Model.HouseExemple;
import com.example.android.housetrinder.R;



public class SearchFragment extends Fragment implements RecyclerViewAdapter.RecyclerViewClickListener{

    static boolean DEBBUGING = true;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter myAdapter;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);


        myAdapter = new RecyclerViewAdapter(getContext(),new HouseExemple().getListExemple() , this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);




        return rootView;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        if(DEBBUGING)
            Log.d("recyclerViewClicked"," item "+position+" clicked");


    }
}
