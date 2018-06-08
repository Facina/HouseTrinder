package com.example.android.housetrinder.Control.Connection;


import java.net.URL;

import okhttp3.HttpUrl;


/*
 * Classe criada para ajudar a acessar as URLs do Webserbice
 */

public class WebServiceUtil {

    /*
     * string_url armazena a URL raiz para acessar o webservice
     */
    private static final String registerFacebook = "http://suckow.com.br/marcelo/housetrinder/register_facebook.php";
    private static final String checkEmail = "http://suckow.com.br/marcelo/housetrinder/check_email.php";
    private static final String registerRegular = "http://suckow.com.br/marcelo/housetrinder/register_regular.php";
    private static final String fetchInterest = "http://suckow.com.br/marcelo/housetrinder/fetch_interest.php";
    private static final String fetchUsers = "http://suckow.com.br/marcelo/housetrinder/fetch_all_users.php";


    /*
     * Objeto URL que aponta para a raiz do webserive
     */
    public static final  URL REGISTER_FACEBOOK = HttpUrl.parse(registerFacebook).url();
    public static final  URL REGISTER_REGULAR = HttpUrl.parse(registerRegular).url();
    public static final  URL FETCH_INTEREST = HttpUrl.parse(fetchInterest).url();
    public static final  URL FETCH_USERS = HttpUrl.parse(fetchUsers).url();

    public static final HttpUrl CHECK_EMAIL = HttpUrl.parse(checkEmail);
    /*
     * Método que gera a URL para realizar operações de PUT e DELETE sobre um contato especifico
     * @param contact -> o Contato para o qual a URL especifica será gerada.
     */
    public static String getContactURL(String email) {
        String url = CHECK_EMAIL.newBuilder().addQueryParameter("email",email).build().toString();

        return url;
    }
}
