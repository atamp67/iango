package com.example.iungo.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iungo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onIungoCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.activity_main_default_notification_channel_id);
            String channelName = getString(R.string.activity_main_default_notification_channel_name);

            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
            Log.e(TAG, "notification completed");
        }

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);

                Log.e(TAG, "Key : " + key + " Value : " + value);
            }
        }

        Button subscribeButton = (Button) findViewById(R.id.activity_main_subscribe_button);
        subscribeButton.setOnClickListener(this);

        Button logTokenButton = (Button) findViewById(R.id.activity_main_log_token_button);
        logTokenButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_main_subscribe_button) {
            Log.e(TAG, "Subscribing to Weather topic");
            FirebaseMessaging.getInstance().subscribeToTopic("weather")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String message = getString(R.string.message_subscribe);
                            if (!task.isSuccessful()) {
                                message = getString(R.string.message_subscribe_failed);
                            }
                            Log.e(TAG, message);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else if (view.getId() == R.id.activity_main_log_token_button) {
//            FirebaseInstanceId.getInstance().getInstanceId()
//                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                            if (!task.isSuccessful()) {
//                                Log.e(TAG, "get Instance Failed", task.getException());
//                                return;
//                            }
//
//                            String token = task.getResult().getToken();
//
//                            String message = getString(R.string.message_token_fmt, token);
//                            Log.e(TAG, message + "123");
//
//                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//                        }
//
//                    });

            startActivity(new Intent(this,MapsActivity.class));
        }
    }
}
