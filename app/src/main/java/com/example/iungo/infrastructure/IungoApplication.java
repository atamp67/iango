package com.example.iungo.infrastructure;

import android.app.Application;

import com.squareup.otto.Bus;

public class IungoApplication extends Application {
    private Auth auth;
    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
        bus = new Bus();
    }

    public Bus getBus() {
        return bus;
    }

    public Auth getAuth() {
        return auth;
    }
}
