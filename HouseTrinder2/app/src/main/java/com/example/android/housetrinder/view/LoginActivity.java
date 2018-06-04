package com.example.android.housetrinder.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.android.housetrinder.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private boolean DEBBUG = false;
    LoginButton loginButton;
    CallbackManager callbackManager;
    SharedPreferences.Editor user_account;
    String data = "USER_INFO";
    Button regularLogin;
    AutoCompleteTextView usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFacebook();
            }
        });
        regularLogin = (Button) findViewById(R.id.email_sign_in_button);
        regularLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRegular();
            }
        });
        usernameEditText = (AutoCompleteTextView) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);

        if(DEBBUG) {
            try {
                ActivityInfo[] list = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).activities;

                Log.e("list", " " + list.length);
                for (int i = 0; i < list.length; i++) {
                    Log.e("List of activities", " " + list[i].name);

                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }







    }



    void loginFacebook(){
        callbackManager = CallbackManager.Factory.create();
        user_account = getSharedPreferences(data,Context.MODE_PRIVATE).edit();

        loginButton.setReadPermissions("public_profile", "email", "user_birthday", "user_gender");
        // If using in a fragment

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                Log.e("onSucessLogin",""+loginResult.getAccessToken().getUserId());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                //Log.v("LoginActivity", response.toString());

                                Log.e("json",object.toString());
                                // Application code

                                user_account.putString("facebookID",loginResult.getAccessToken().getUserId());
                                user_account.putBoolean("login",true);

                                try {

                                    String email = object.getString("email");
                                    //Log.e("onCompleted",email);
                                    user_account.putString("email",email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {

                                    String birthday = object.getString("birthday"); // 01/31/1980 format
                                    user_account.putString("birthday",birthday);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    String gender = object.getString("gender");
                                    user_account.putString("gender",gender);
                                    // Log.e("gender",""+gender);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                try{
                                    String name = object.getString("name");
                                    user_account.putString("name",name);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }

                                try{
                                    String pictureURL = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                    user_account.putString("URL",pictureURL);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }




                                user_account.apply();

                                goToMainActivity();

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,birthday,gender,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));



    }


    void goToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    void loginRegular(){
        user_account = getSharedPreferences(data,Context.MODE_PRIVATE).edit();
        user_account.putBoolean("login",true);


        user_account.apply();
        goToMainActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}

