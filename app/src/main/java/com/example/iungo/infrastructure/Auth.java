package com.example.iungo.infrastructure;

import android.content.Context;

public class Auth {
    private final Context context;

    public Auth(Context context) {
        this.context = context;
    }


    public Context getContext() {
        return context;
    }
}
