package com.rana_aditya.parent;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the existing main screen.
        setContentView(R.layout.activity_main);

        createNotificationChannel();
        subscribeToExistingTopic();
    }

    private void createNotificationChannel() {
        // Notification channels are available on Android 8 and later.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "mychannel",
                    "إشعارات وليّ الأمر",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager =
                    (NotificationManager) getSystemService(
                            Context.NOTIFICATION_SERVICE
                    );

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private void subscribeToExistingTopic() {
        // Preserve the existing messaging behavior.
        FirebaseMessaging.getInstance()
                .subscribeToTopic("parentclient")
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (isFinishing()) {
                                    return;
                                }

                                String message;

                                if (task.isSuccessful()) {
                                    message = "تم الاشتراك في موضوع الإشعارات";
                                } else {
                                    message = "تعذر الاشتراك في موضوع الإشعارات";
                                }

                                Toast.makeText(
                                        MainActivity.this,
                                        message,
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                );
    }

    public void about_app(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                aboutapp.class
        );

        startActivity(intent);
    }

    public void next(View view) {
        Intent intent = new Intent(
                MainActivity.this,
                choice.class
        );

        startActivity(intent);
    }
}
