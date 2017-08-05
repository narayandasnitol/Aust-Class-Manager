package com.nitol.aust.cse.austclassmanager;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver{

    QuizDatabaseHelper myDb;
    String title, details;

    @Override
    public void onReceive(Context context, Intent intent) {

        int id= intent.getIntExtra("ID", 1);

        myDb = new QuizDatabaseHelper(context);
        Cursor data = myDb.getQuizTitle(String.valueOf(id));

        while(data.moveToNext()) {
            title = data.getString(1);
            details = data.getString(2);
        }


        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, QuizReminder_Activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, id , repeating_intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.notepad)
                .setContentTitle(" ' "+title+" ' "+" Quiz Tomorrow")
                .setDefaults(android.support.v4.app.NotificationCompat.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE)
                .setContentText("Syllabus: "+details)
                .setAutoCancel(true);

        notificationManager.notify(id, builder.build());

    }


}
