package com.example.e_voting_mobile.service;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SessionManager {
    private static final String EMAIL_KEY = "LOGGED_EMAIL";
    private static SessionManager sessionManager;
    private final static String TOKEN_KEY = "BEARER";
    private static final String ROLES_KEY = "ROLES";

    private SharedPreferences sharedPreferences;

    private SessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("userDetails", Context.MODE_PRIVATE);
    }

    public static SessionManager getInstance(Context context) {
        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }
        return sessionManager;
    }

    public static SessionManager getInstance() {
        return sessionManager;
    }

    public String getAuthenticationToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public void setAuthenticationToken(String authenticationToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, authenticationToken);
        editor.apply();
    }

    public String getAuthenticationEmail() {
        return sharedPreferences.getString(EMAIL_KEY, null);
    }

    public void setAuthenticationEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL_KEY, email);
        editor.apply();
    }

    public String getAuthenticatedRoles() {
        return sharedPreferences.getString(ROLES_KEY, null);
    }

    public void setAuthenticatedRoles(List<String> roles) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ROLES_KEY, roles.toString());
        editor.apply();
    }

}
