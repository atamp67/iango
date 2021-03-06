package com.example.iungo.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onIungoCreate(savedInstanceState);

    }

    protected abstract void onIungoCreate(Bundle savedInstanceState);
}
