package com.example.android.housetrinder.Control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.housetrinder.Model.Message;
import com.example.android.housetrinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewMatches extends RecyclerView.Adapter<RecyclerViewMatches.PeopleViewHolder>{
    private ArrayList<Message> listPeople ;
    private static RecyclerViewMatches.RecyclerViewClickListener itemListener;

    public interface RecyclerViewClickListener
    {
        public void recyclerViewListClicked(View v, int position);
    }

    public RecyclerViewMatches(Context applicationContext, ArrayList<Message> people, RecyclerViewMatches.RecyclerViewClickListener itemListener){
        this.listPeople = people;
        this.itemListener = itemListener;
    }


    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameReceiver, lastMessage;
        public ImageView imgProfile;


        public PeopleViewHolder(View view) {
            super(view);
            nameReceiver = (TextView) view.findViewById(R.id.name_text_layout);
            lastMessage = (TextView) view.findViewById(R.id.message_text_layout);
            imgProfile = (ImageView) view.findViewById(R.id.message_profile_layout);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());

        }
    }




    @Override
    public RecyclerViewMatches.PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_item, parent, false);

        return new RecyclerViewMatches.PeopleViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerViewMatches.PeopleViewHolder holder, int position) {
        Message contact = listPeople.get(position);
        //holder.nameReceiver.setText(contact.getReceiverName());
        holder.lastMessage.setText(contact.getMessage());

        Picasso.get().load(contact.getUrlProfile()).into(holder.imgProfile);



    }

    @Override
    public int getItemCount() {
        return listPeople.size();
    }




}
