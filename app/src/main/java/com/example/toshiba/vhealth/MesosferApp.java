package com.example.toshiba.vhealth;

import android.app.Application;

import com.eyro.mesosfer.Mesosfer;

/**
 * Created by asus on 06-Nov-16.
 */

public class MesosferApp extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Mesosfer.setPushNotification(true);
        Mesosfer.initialize(this, "RaDL1hrgEx", "8wuvHkzgztPuNXGRDkn6pJ0yX5daa6Ky");

    }
}
