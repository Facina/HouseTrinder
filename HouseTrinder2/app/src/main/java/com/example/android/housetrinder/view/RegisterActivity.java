package com.example.android.housetrinder.view;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.housetrinder.Control.Connection.WebServiceUtil;
import com.example.android.housetrinder.Model.User;
import com.example.android.housetrinder.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    String data = "USER_INFO";
    private AutoCompleteTextView emailEditText;
    private EditText nameEditText;
    private EditText passwordEditText;
    private EditText confirmationPasswordEditText;
    private TextView genderMaleTextView;
    private TextView genderFemaleTextView;
    private int gender = 1;
    private DatePicker datePicker;
    private Button saveButton;
    private LoaderManager.LoaderCallbacks<User> callbacksCheckEmail;
    private LoaderManager.LoaderCallbacks<String> callbacksRegister;

    private static final int CHECK_EMAIL_LOADER = 11;
    private static final int REGISTER_REGULAR_LOADER = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = (AutoCompleteTextView) findViewById(R.id.email_register_activity);
        nameEditText = (EditText) findViewById(R.id.name_register_activity);
        passwordEditText = (EditText) findViewById(R.id.password_register_activity);
        confirmationPasswordEditText = (EditText) findViewById(R.id.confirmation_password_register_activity);
        genderMaleTextView = (TextView) findViewById(R.id.register_gender_male);
        datePicker = (DatePicker) findViewById(R.id.datePicker_register);
        saveButton = (Button) findViewById(R.id.save_button_register);
        genderFemaleTextView = (TextView) findViewById(R.id.register_gender_female);
        genderMaleTextView = (TextView) findViewById(R.id.register_gender_male);


        initiate();

        genderFemaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gender==1) {
                    gender = 2;
                    switching(gender);
                }else{
                    gender =1;
                    switching(gender);
                }
            }
        });

        genderMaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(gender ==2) {
                   gender = 1;
                   switching(gender);
               }else{
                   gender =2;
                   switching(gender);
               }
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

        callbacksCheckEmail = new LoaderManager.LoaderCallbacks<User>() {
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
                            Log.e("Response",response.toString());
                            if (response.isSuccessful()){

                                try {
                                    return new User(response.body().string());
                                }catch (Exception e){
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
            public void onLoadFinished(android.support.v4.content.Loader<User> loader, User data) {
                Log.e("data minha",""+data);
                if(data.getEmail()==null){
                    insert();
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
        loaderManager.restartLoader(CHECK_EMAIL_LOADER, null, callbacksCheckEmail).forceLoad();

        Log.e("runRegister", "end");





    }

    private void insert() {

        final User mUser = new User();
        mUser.setNameUser(nameEditText.getText().toString());
        mUser.setEmail(emailEditText.getText().toString());
        mUser.setUserPassword(passwordEditText.getText().toString());
        if(gender==2)mUser.setGender("M");
        else mUser.setGender("F");

        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);


        java.util.Date dataUtil = calendar.getTime();
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
        mUser.setBirthDate(dataSql);

        callbacksRegister = new LoaderManager.LoaderCallbacks<String>() {
            @Override
            public Loader<String> onCreateLoader(int id, final Bundle args) {
         return new AsyncTaskLoader<String>(getApplicationContext()) {

                @Override
                protected void onStartLoading(){

                    forceLoad();
                }

                @Override
                public String loadInBackground() {
                    Log.e("checkingEMail", "loading");



                    try {

                        OkHttpClient client = new OkHttpClient();


                        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
                        RequestBody body = null;
                        body = new FormBody.Builder()
                                .add("email",mUser.getEmail())
                                .add("nameUser",mUser.getNameUser())
                                .add("gender",""+mUser.getGender())
                                .add("birthDate",sdt.format(mUser.getBirthDate()))
                                .add("userPassword",mUser.getUserPassword())
                                .build();


                        Request request = new Request.Builder()
                                .url(WebServiceUtil.REGISTER_REGULAR)
                                .post(body)
                                .build();
                        Response response = client.newCall(request).execute();
                        Log.e("registrando",response.toString());
                        if (response.isSuccessful()){



                                return response.body().string();


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }



        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            Log.e("REGISTER REGULAR", data);
            if(data.equals("Registration successful")){
                Toast.makeText(getApplicationContext(),getString(R.string.cadastro_sucesso),Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Failed to register.",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }
        };


        LoaderManager loaderManager = getSupportLoaderManager();
        android.support.v4.content.Loader<User> emailLoader = loaderManager.getLoader(REGISTER_REGULAR_LOADER);
        loaderManager.restartLoader(REGISTER_REGULAR_LOADER, null, callbacksRegister).forceLoad();

    }


    private boolean verifyData() {

        if(isEmailValid(emailEditText.getText().toString())) {
            Log.d("checkIfAccountExist","starting...1");
            if (passwordMatch()) {
                Log.d("checkIfAccountExist","starting...2");
                if (!nameEditText.getText().toString().matches("")) {
                    Log.d("checkIfAccountExist","starting...3");

                    if (age() >= 15) {

                        Log.d("checkIfAccountExist", "starting...4");
                        return true;
                    }
                }else  Toast.makeText(getApplicationContext(),getString(R.string.nameError),Toast.LENGTH_LONG).show();

            } else  Toast.makeText(getApplicationContext(),getString(R.string.passwordMatchError),Toast.LENGTH_LONG).show();

        }else Toast.makeText(getApplicationContext(),getString(R.string.emailFormatError),Toast.LENGTH_LONG).show();



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

        switching(gender);


    }

    private void switching(int i) {
        if(i==1){
            if (Build.VERSION.SDK_INT < 23) {
                genderFemaleTextView.setTextAppearance(getApplicationContext(), R.style.female_register);
                genderMaleTextView.setTextAppearance(getApplicationContext(),R.style.regular_register);

            } else {
                genderFemaleTextView.setTextAppearance(R.style.female_register);
                genderMaleTextView.setTextAppearance(R.style.regular_register);

            }
        }else{
            if (Build.VERSION.SDK_INT < 23) {
                genderMaleTextView.setTextAppearance(getApplicationContext(),R.style.male_register);
                genderFemaleTextView.setTextAppearance(getApplicationContext(),R.style.regular_register);

            }else {
                genderMaleTextView.setTextAppearance(R.style.male_register);
                genderFemaleTextView.setTextAppearance(R.style.regular_register);
            }
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
