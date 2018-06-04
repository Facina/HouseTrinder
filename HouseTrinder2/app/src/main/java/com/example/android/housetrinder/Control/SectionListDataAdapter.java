package com.example.android.housetrinder.Control;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.housetrinder.Model.PreferenceItem;
import com.example.android.housetrinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<PreferenceItem> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<PreferenceItem> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        PreferenceItem singleItem = itemsList.get(i);

        holder.tvTitle.setText(singleItem.getName());

        Picasso.get().load(singleItem.getResourceDrawable()).fit().into(holder.itemImage);
       // holder.itemImage.setImageResource(singleItem.getResourceDrawable());
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;

        protected FrameLayout frameLayoutCheck;

        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
            this.frameLayoutCheck = (FrameLayout) view.findViewById(R.id.card_foreground);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    if(frameLayoutCheck.getVisibility() == View.VISIBLE)
                        frameLayoutCheck.setVisibility(View.INVISIBLE);
                    else frameLayoutCheck.setVisibility(View.VISIBLE);
                }
            });


        }

    }

}