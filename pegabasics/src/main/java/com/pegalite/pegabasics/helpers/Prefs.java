package com.pegalite.pegabasics.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created By: Sahil The Geek, on: 14-01-2022
 * A helper class for managing SharedPreferences to store and retrieve app data efficiently.
 * Provides methods to save, retrieve, and clear preferences.
 */
public class Prefs {

    /**
     * Constant used as a default value when no data is found for a given key.
     */
    public static final String NO_DATA = "no-data";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    /**
     * Constructor initializes SharedPreferences with the specified context.
     *
     * @param context the application context.
     */
    public Prefs(Context context) {
        this.sharedPreferences = context.getSharedPreferences("app-data", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    /**
     * Saves a String value to preferences.
     *
     * @param name  the key name.
     * @param value the value to store.
     */
    public void setPref(String name, String value) {
        editor.putString(name, value).apply();
    }

    /**
     * Saves an integer value to preferences.
     *
     * @param name  the key name.
     * @param value the value to store.
     */
    public void setPref(String name, int value) {
        editor.putInt(name, value).apply();
    }

    /**
     * Saves a boolean value to preferences.
     *
     * @param name  the key name.
     * @param value the value to store.
     */
    public void setPref(String name, boolean value) {
        editor.putBoolean(name, value).apply();
    }

    /**
     * Retrieves a String value from preferences.
     *
     * @param name the key name.
     * @return the stored value, or "no-data" if not found.
     */
    public String getPref(String name) {
        return sharedPreferences.getString(name, NO_DATA);
    }

    /**
     * Retrieves the SharedPreferences instance.
     *
     * @return the SharedPreferences instance.
     */
    public SharedPreferences getPref() {
        return sharedPreferences;
    }

    /**
     * Retrieves an integer value from preferences.
     *
     * @param name the key name.
     * @return the stored value, or 0 if not found.
     */
    public int getPrefInt(String name) {
        return sharedPreferences.getInt(name, 0);
    }

    /**
     * Retrieves the stored JWT token.
     *
     * @return the JWT token, or "no-data" if not found.
     */
    public String getJWT() {
        return sharedPreferences.getString("jwt", NO_DATA);
    }

    /**
     * Saves a JWT token to preferences.
     *
     * @param value the JWT token to store.
     */
    public void setJWT(String value) {
        editor.putString("jwt", value).apply();
    }

    /**
     * Retrieves the stored email address.
     *
     * @return the email address, or "no-data" if not found.
     */
    public String getEmail() {
        return sharedPreferences.getString("email", NO_DATA);
    }

    /**
     * Saves an email address to preferences.
     *
     * @param value the email address to store.
     */
    public void setEmail(String value) {
        editor.putString("email", value).apply();
    }

    /**
     * Checks if the user is logged in by verifying the presence of a JWT token.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean checkLogin() {
        return !getJWT().equals(NO_DATA);
    }

    /**
     * Checks if the walkthrough has been watched.
     *
     * @return true if watched, false otherwise.
     */
    public boolean isWalkThroughWatched() {
        return sharedPreferences.getBoolean("watched-walk-through", false);
    }

    /**
     * Marks the walkthrough as watched.
     */
    public void walkThroughWatched() {
        editor.putBoolean("watched-walk-through", true).apply();
    }

    /**
     * Clears all data stored in preferences.
     */
    public void clearAll() {
        editor.clear().apply();
    }
}
