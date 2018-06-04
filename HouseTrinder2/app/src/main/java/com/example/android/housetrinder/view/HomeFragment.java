package com.example.android.housetrinder.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.housetrinder.R;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {




    public HomeFragment() {
    }

    String data = "USER_INFO";
    SharedPreferences user_account ;
    ImageView imageViewPicture;
    TextView welcomeTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView   =     inflater.inflate(R.layout.fragment_home, container, false);

        imageViewPicture = (ImageView) rootView.findViewById(R.id.profilePicture);

        welcomeTV = (TextView) rootView.findViewById(R.id.welcome);

        user_account = getContext().getSharedPreferences(data, Context.MODE_PRIVATE);

            String url = user_account.getString("URL",null);
            if(url!=null)
            Log.e("url =",url);
            Picasso.get().load(url).into(imageViewPicture);

        Button logout = (Button) rootView.findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_account.edit().putBoolean("login",false).apply();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        Button preferences = (Button) rootView.findViewById(R.id.preferences_button);
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getContext(),PreferencesActivity.class);
                startActivity(intent);
            }
        });

        try {
            welcomeTV.setText(getString(R.string.welcome)+" "+user_account.getString("name",null));
        }catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }





}
