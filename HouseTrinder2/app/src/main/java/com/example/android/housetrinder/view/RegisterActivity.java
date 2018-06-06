package com.example.android.housetrinder.view;

import android.annotation.SuppressLint;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.housetrinder.Control.Connection.WebServiceUtil;
import com.example.android.housetrinder.Model.User;
import com.example.android.housetrinder.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView emailEditText;
    private EditText nameEditText;
    private EditText passwordEditText;
    private EditText confirmationPasswordEditText;
    private SwitchCompat switchGender;
    private TextView genderTextView;
    private DatePicker datePicker;
    private Button saveButton;
    private LoaderManager.LoaderCallbacks<User> callbacksRegister;
    private static final int CHECK_EMAIL_LOADER = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = (AutoCompleteTextView) findViewById(R.id.email_register_activity);
        nameEditText = (EditText) findViewById(R.id.name_register_activity);
        passwordEditText = (EditText) findViewById(R.id.password_register_activity);
        confirmationPasswordEditText = (EditText) findViewById(R.id.confirmation_password_register_activity);
        switchGender = (SwitchCompat) findViewById(R.id.register_switch_gender);
        genderTextView = (TextView) findViewById(R.id.register_gender);
        datePicker = (DatePicker) findViewById(R.id.datePicker_register);
        saveButton = (Button) findViewById(R.id.save_button_register);


        initiate();

        switchGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switching(b);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyData()){
                    Log.d("checkIfAccountExist","starting...");
                    checkIfAccountExistsAlready();
                }
            }
        });

    }

    private void checkIfAccountExistsAlready() {

        callbacksRegister = new LoaderManager.LoaderCallbacks<User>() {
            @Override
            public Loader<User> onCreateLoader(int id, final Bundle args) {
                return new AsyncTaskLoader<User>(getApplicationContext()) {

                    @Override
                    protected void onStartLoading(){

                        forceLoad();
                    }

                    @Override
                    public User loadInBackground() {
                        Log.e("checkingEMail", "loading");



                        try {

                            OkHttpClient client = new OkHttpClient();



                            Request request = new Request.Builder()
                                    .url(WebServiceUtil.getContactURL(emailEditText.getText().toString()))
                                    .build();
                            //Log.e("Request =",request.toString());
                            Response response = client.newCall(request).execute();
                            //Log.e("Response",response.toString());
                            if (response.isSuccessful()){


                                return new User(response.body().string());
                            }

                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
            }

            @Override
            public void onLoadFinished(android.support.v4.content.Loader<User> loader, User data) {
                Log.e("data minha",data.toString());
                if(data.getEmail()==null){

                    Toast.makeText(
                            getApplicationContext(),
                            "Account Successfully created!",

                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Email adress already registered",

                            Toast.LENGTH_LONG).show();
                    //Log.e("User =", data.getEmail());

                }
            }

            @Override
            public void onLoaderReset(android.support.v4.content.Loader<User> loader) {

            }
        };



        LoaderManager loaderManager = getSupportLoaderManager();
        android.support.v4.content.Loader<User> emailLoader = loaderManager.getLoader(CHECK_EMAIL_LOADER);
        loaderManager.restartLoader(CHECK_EMAIL_LOADER, null,  callbacksRegister).forceLoad();

        Log.e("runRegister", "end");





    }




    private boolean verifyData() {

        if(isEmailValid(emailEditText.getText().toString())) {
            Log.d("checkIfAccountExist","starting...1");
            Toast.makeText(getApplicationContext(),getString(R.string.emailFormatError),Toast.LENGTH_LONG).show();
            if (passwordMatch()) {
                Log.d("checkIfAccountExist","starting...2");
                Toast.makeText(getApplicationContext(),getString(R.string.passwordMatchError),Toast.LENGTH_LONG).show();
                if (!nameEditText.getText().toString().matches("")) {
                    Log.d("checkIfAccountExist","starting...3");
                    Toast.makeText(getApplicationContext(),getString(R.string.nameError),Toast.LENGTH_LONG).show();

                    if (age() >= 15) {

                        Log.d("checkIfAccountExist", "starting...4");
                        return true;
                    }
                    }
                }
            }


        return false;
    }

    private int age() {

       int age;
       int year = datePicker.getYear();

       Calendar c =  Calendar.getInstance();

       int mYear = c.get(Calendar.YEAR);

       age = mYear - year;
       return age;
    }

    private boolean passwordMatch() {
        return passwordEditText.getText().toString().equals(confirmationPasswordEditText.getText().toString());
    }


    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void initiate() {
        //datePicker;
        switchGender.setChecked(true);
        switching(true);


    }

    @SuppressLint("ResourceType")
    private void switching(boolean b) {
        if(switchGender.isChecked()){
            genderTextView.setText(getString(R.string.male));
            genderTextView.setTextColor(getResources().getInteger(R.color.colorAccent));
        }else{
            genderTextView.setText(getString(R.string.female));
            genderTextView.setTextColor(getResources().getInteger(R.color.pink));
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
