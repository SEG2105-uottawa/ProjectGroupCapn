package com.example.tutronapp;
import android.content.Context;

import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {

    public static void showNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID) // CHANNEL_ID have to change
                .setContentTitle("Purchase approved")
                .setContentText("") // the databse
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
