package com.example.filter;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * created by Atiye Mousavi
 * Date: 12/17/2021
 * Time: 9:06 AM
 **/


public class Customer {
    private String name;
    private int points;
    private String profilePhotoUrl;

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
    public boolean hasOver(int points) {
        return this.points > points;
    }

    public boolean hasOverHundredPoints() {
        return this.points > 100;
    }

    public boolean hasValidProfilePhoto() throws IOException {
        URL url = new URL(this.profilePhotoUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public boolean hasValidProfilePhotoWithoutCheckedException() {
        try {
            URL url = new URL(this.profilePhotoUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Customer(String name, int points) {
        this(name, points, "");
    }

    public Customer(String name, int points, String profilePhotoUrl) {
        this.name = name;
        this.points = points;
        this.profilePhotoUrl = profilePhotoUrl;
    }
}
