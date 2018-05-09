package com.example.mayur.pdm;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Dulip on 4/25/2018.
 */

public class Firefuel extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
