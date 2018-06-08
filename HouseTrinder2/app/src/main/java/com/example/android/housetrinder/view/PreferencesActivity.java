package com.example.android.housetrinder.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.housetrinder.R;

public class PreferencesActivity extends AppCompatActivity {

    SeekBar seekBarQ1;
    TextView answer1TV;
    SeekBar seekBarQ2;
    TextView answer2TV;
    SeekBar seekBarQ3;
    TextView answer3TV;
    SeekBar seekBarQ4;
    TextView answer4TV;
    String preferences = "USER_PREFERENCES";
    SharedPreferences user_account ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        user_account = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        seekBarQ1 = (SeekBar) findViewById(R.id.seekbar_Q1);


        seekBarQ1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Question1(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarQ1.setProgress(0);
        Question1(seekBarQ1.getProgress());

        seekBarQ2 = (SeekBar) findViewById(R.id.seekbar_Q2);


        seekBarQ2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Question2(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarQ1.setProgress(0);
        Question2(seekBarQ2.getProgress());


        seekBarQ3 = (SeekBar) findViewById(R.id.seekbar_Q3);
        seekBarQ3.setProgress(0);
        seekBarQ3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Question3(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Question3(seekBarQ3.getProgress());

        seekBarQ4 = (SeekBar) findViewById(R.id.seekbar_Q4);
        seekBarQ4.setProgress(0);
        seekBarQ4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Question4(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Question4(seekBarQ4.getProgress());


        Button next = (Button) findViewById(R.id.preferences_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAnswer();
                Intent interest = new Intent(getApplicationContext(),InterestActivity.class);
                startActivity(interest);
            }
        });


    }

    void saveAnswer(){

        user_account.edit()
                .putInt("answer1",seekBarQ1.getProgress())
                .putInt("answer2",seekBarQ2.getProgress())
                .putInt("answer3",seekBarQ3.getProgress())
                .putInt("answer4",seekBarQ4.getProgress())
                .apply();


    }

    void Question4(int i){
        answer4TV = (TextView) findViewById(R.id.answer4);

        switch (i){
            case 0:
                answer4TV.setText(getString(R.string.nunca));
                break;
            case 1:
                answer4TV.setText(getString(R.string.raramente));

                break;
            case 2:
                answer4TV.setText(getString(R.string.as_vezes));

                break;
            case 3:
                answer4TV.setText(getString(R.string.frequentemente));

                break;
            case 4:
                answer4TV.setText(getString(R.string.sempre));

                break;
        }

    }

    void Question3(int i){
        answer3TV = (TextView) findViewById(R.id.answer3);

        switch (i){
            case 0:
                answer3TV.setText(getString(R.string.silencioso));
                break;
            case 1:
                answer3TV.setText(getString(R.string.discreto));

                break;
            case 2:
                answer3TV.setText(getString(R.string.normal));

                break;
            case 3:
                answer3TV.setText(getString(R.string.notavel));

                break;
            case 4:
                answer3TV.setText(getString(R.string.barulhento));

                break;
        }

    }

    void Question2(int i){
        answer2TV = (TextView) findViewById(R.id.answer2);

        switch (i){
            case 0:
                answer2TV.setText(getString(R.string.nunca));
                break;
            case 1:
                answer2TV.setText(getString(R.string.raramente));

                break;
            case 2:
                answer2TV.setText(getString(R.string.as_vezes));

                break;
            case 3:
                answer2TV.setText(getString(R.string.frequentemente));

                break;
            case 4:
                answer2TV.setText(getString(R.string.sempre));

                break;
        }

    }

    void Question1(int i){
        answer1TV = (TextView) findViewById(R.id.answer1);

        switch (i){
            case 0:
                answer1TV.setText(getString(R.string.muito_desorganizado));
                break;
            case 1:
                answer1TV.setText(getString(R.string.desorganizado));

                break;
            case 2:
                answer1TV.setText(getString(R.string.normal));

                break;
            case 3:
                answer1TV.setText(getString(R.string.organizado));

                break;
            case 4:
                answer1TV.setText(getString(R.string.muito_organizado));

                break;
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
