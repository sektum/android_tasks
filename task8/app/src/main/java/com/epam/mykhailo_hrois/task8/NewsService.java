package com.epam.mykhailo_hrois.task8;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;


public class NewsService extends JobService {
    private static final String TAG = "ExampleJobService";
    private static final String MY_PREFS_NAME = "JsonData";
    private boolean jobCancelled;
    public static String data;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(final JobParameters params) {
        AsyncTaskNews process = new AsyncTaskNews(this, params);
        process.execute();
        if(jobCancelled){
            return;
        }
        Log.d(TAG, "doBackgroundWork: " + process.data);
        showNotification(this, "Hello there", data);
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("json", data);
        editor.apply();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }

    private void showNotification(Context context, CharSequence title, CharSequence message) {
        final NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 666, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder notificationBuilder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationBuilder = new Notification.Builder(context, "666");
        } else notificationBuilder = new Notification.Builder(context);
        notificationBuilder
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();
        managerCompat.notify(666, notificationBuilder.build());
    }
}
