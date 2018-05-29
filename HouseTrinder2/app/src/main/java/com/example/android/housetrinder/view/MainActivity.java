package com.example.android.housetrinder.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.housetrinder.Control.FragmentAdapter;
import com.example.android.housetrinder.R;
import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(AccessToken.getCurrentAccessToken() == null){
            goToLoginScreen();
        }


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        FragmentAdapter adapter = new FragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.house);
        tabLayout.getTabAt(1).setIcon(R.drawable.lupa);
        tabLayout.getTabAt(2).setIcon(R.drawable.chat);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int color = ContextCompat.getColor(getApplicationContext(),R.color.white);
                tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int color = ContextCompat.getColor(getApplicationContext(),R.color.black);
                tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.getTabAt(0).select();
        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getApplicationContext(),R.color.white),PorterDuff.Mode.SRC_IN);

    }


    void goToLoginScreen(){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
