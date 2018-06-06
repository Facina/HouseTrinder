package com.example.android.housetrinder.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.housetrinder.Control.RecyclerViewMatches;
import com.example.android.housetrinder.Model.Message;
import com.example.android.housetrinder.R;

import java.util.ArrayList;

public class ChatFragment extends Fragment implements RecyclerViewMatches.RecyclerViewClickListener {

    public ChatFragment() {
        // Required empty public constructor
    }

    String data = "USER_INFO";
    SharedPreferences user_account ;
    private boolean DEBBUGING = true;
    private RecyclerView recyclerView;
    private RecyclerViewMatches myAdapter;
    private ArrayList<Message> listaDeContatos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);

        listaDeContatos = new ArrayList<Message>();
        user_account = getContext().getSharedPreferences(data, Context.MODE_PRIVATE);

        getContacts();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.matches_rv);

        //Log.e("numero de contatos",""+listaDeContatos.size()+"nome ="+ listaDeContatos.get(0).getReceiverName());
        myAdapter = new RecyclerViewMatches(getContext(), listaDeContatos , this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);



        return rootView;
    }


    private void getContacts(){


        String url = user_account.getString("URL",null);
        String name = user_account.getString("name","falhou");
        listaDeContatos.add(new Message(name,"Tudo bom?",url));

        return;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        Intent intent = new Intent(getContext(),ChatActivity.class);
        intent.putExtra("contact",listaDeContatos.get(position));
        startActivity(intent);

    }
}
