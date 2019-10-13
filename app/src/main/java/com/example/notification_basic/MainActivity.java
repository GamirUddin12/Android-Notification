package com.example.notification_basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void displayNotification() {

        Intent intent = new Intent(this, AlertDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent yesIntent = new Intent(this, YesActivity.class);
        yesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent yesPendingIntent = PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent noIntent = new Intent(this, NoActivity.class);
        noIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent noPendingIntent = PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_ONE_SHOT);


        final NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Simplified coding", "fff", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        /*RemoteViews normal_layout = new RemoteViews(getPackageName(), R.layout.custom_normal);
        RemoteViews expended_layout = new RemoteViews(getPackageName(), R.layout.custom_expended);*/


        final NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "Simplified coding")
                .setContentTitle("Simplified coding")
                .setContentText("fff")
               .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_ac_unit_black_24dp, "Yes", yesPendingIntent)
                .addAction(R.drawable.ic_airport_shuttle_black_24dp, "No", noPendingIntent)
                /*.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(normal_layout)
                .setCustomBigContentView(expended_layout)
                .setProgress(100, 0, false)*/
                .setSmallIcon(R.drawable.ic_beach_access_black_24dp);
        manager.notify(Constants.NOTIFICATION_ID, builder.build());

/*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                try {
                    while (count <= 100) {
                        count = count + 10;
                        sleep(1000);
                        builder.setProgress(100, count, false);
                        manager.notify(Constants.NOTIFICATION_ID, builder.build());
                    }
                    builder.setContentText("Download Complete");
                    builder.setProgress(0, 0, false);
                    manager.notify(Constants.NOTIFICATION_ID, builder.build());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();

        */

    }

    public void onNotify(View view) {
        displayNotification();
    }
}
