package com.example.android.housetrinder.Control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.housetrinder.Model.HouseExemple;
import com.example.android.housetrinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder> {
    private ArrayList<HouseExemple> listHouses ;
    private static RecyclerViewClickListener itemListener;

    public interface RecyclerViewClickListener
    {
        public void recyclerViewListClicked(View v, int position);
    }

    public RecyclerViewAdapter(Context applicationContext, ArrayList<HouseExemple> houses, RecyclerViewClickListener itemListener){
        this.listHouses = houses;
        this.itemListener = itemListener;
    }


    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView price, adress,area,quartos,banheiro;
        public ImageView img;


        public WordViewHolder(View view) {
            super(view);
            price = (TextView) view.findViewById(R.id.price);
            adress = (TextView) view.findViewById(R.id.adress);
            area = (TextView) view.findViewById(R.id.area);
            quartos = (TextView) view.findViewById(R.id.quartos);
            banheiro = (TextView) view.findViewById(R.id.banheiros);
            img = (ImageView) view.findViewById(R.id.house_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());

        }
    }




    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_houses, parent, false);

        return new WordViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        HouseExemple house = listHouses.get(position);
        holder.price.setText(String.format("%.2f R$",house.getPrice()));
        holder.adress.setText(house.getAdress());
        holder.area.setText(""+house.getArea()+" m\u00B2");
        holder.quartos.setText(""+house.getRoom_nb());
        holder.banheiro.setText(""+house.getToilet_nb());
        Picasso.get().load(house.getImgLink()).fit().centerCrop().into(holder.img);



    }

    @Override
    public int getItemCount() {
        return listHouses.size();
    }




}
