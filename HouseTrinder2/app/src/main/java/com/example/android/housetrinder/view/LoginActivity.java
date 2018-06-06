package com.example.android.housetrinder.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;

import com.example.android.housetrinder.Control.Connection.WebServiceUtil;
import com.example.android.housetrinder.Model.User;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private boolean DEBBUG = false;
    LoginButton loginButton;
    CallbackManager callbackManager;
    SharedPreferences.Editor user_account;
    String data = "USER_INFO";
    Button regularLogin;
    Button registerButton;
    AutoCompleteTextView usernameEditText;
    EditText passwordEditText;
    private User mUser;
    LoaderManager.LoaderCallbacks<String> callbacksRegisterFacebook;
    private static final int POST_LOADER = 22;
    private static final String EXTRA_CONTACT = "EXTRA_CONTACT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUser =new User();
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

        registerButton  = (Button) findViewById(R.id.register_login) ;

        usernameEditText = (AutoCompleteTextView) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);


        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

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

        user_account.putInt("accountType",getResources().getInteger(R.integer.facebookAccount));
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
                            public void onCompleted(final JSONObject object, GraphResponse response) {
                                //Log.v("LoginActivity", response.toString());

                                Log.e("json",object.toString());
                                // Application code


                                user_account.putString("facebookID",loginResult.getAccessToken().getUserId());
                                user_account.putBoolean("login",true);

                                try {

                                    String email = object.getString("email");
                                    mUser.setEmail(object.getString("email"));
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
                                    mUser.setNameUser(object.getString("name"));
                                    user_account.putString("name",name);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }

                                try{
                                    String pictureURL = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                    mUser.setUrlProfile(pictureURL);
                                    user_account.putString("URL",pictureURL);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }




                                user_account.apply();

                                callbacksRegisterFacebook = new LoaderManager.LoaderCallbacks<String>() {
                                    @Override
                                    public Loader<String> onCreateLoader(int id, final Bundle args) {
                                        return new AsyncTaskLoader<String>(getApplicationContext()) {

                                            @Override
                                            protected void onStartLoading(){
                                                if(args==null){
                                                    return;
                                                }

                                                forceLoad();
                                            }

                                            @Override
                                            public String loadInBackground() {
                                                Log.e("runRegister", "loading");

                                                User contact = args.getParcelable(EXTRA_CONTACT);

                                                if(contact == null){
                                                    return null;
                                                }

                                                ObjectMapper mapper= new ObjectMapper();
                                                try {

                                                    OkHttpClient client = new OkHttpClient();

                                                    RequestBody body = null;
                                                    try {
                                                        body = new FormBody.Builder()
                                                                    .add("email",object.getString("email"))
                                                                    .add("nameUser",object.getString("name"))
                                                                    .add("urlProfile",object.getJSONObject("picture").getJSONObject("data").getString("url"))
                                                                    .build();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                    Request request = new Request.Builder()
                                                            .url(WebServiceUtil.REGISTER_FACEBOOK)
                                                            .post(body)
                                                            .build();
                                                    Response response = client.newCall(request).execute();
                                                    Log.e("Response",response.body().string());
                                                    if (response.isSuccessful()){


                                                        return response.message();
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
                                    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
                                        Log.e("onLoadFinished",data);
                                        if(!data.equals("OK")){

                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "Error adding contact",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "Contact added",
                                                    Toast.LENGTH_LONG).show();
                                            goToMainActivity();

                                        }
                                    }

                                    @Override
                                    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {

                                    }
                                };



                                Bundle postBundle = new Bundle();
                                postBundle.putParcelable(EXTRA_CONTACT,mUser);
                                LoaderManager loaderManager = getSupportLoaderManager();
                                android.support.v4.content.Loader<User> postLoader = loaderManager.getLoader(POST_LOADER);
                                loaderManager.initLoader(POST_LOADER, postBundle,  callbacksRegisterFacebook).forceLoad();

                                Log.e("runRegister", "end");





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

