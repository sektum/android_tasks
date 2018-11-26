package com.epam.mykhailo_hrois.task8;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;

import com.epam.mykhailo_hrois.task8.entities.Articles;

import java.util.Date;


public class NewsService extends JobService implements AsyncTaskNews.OnResultReceiverListener {
    private static final String TAG = "JobService";
    private JobParameters param;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");

        this.param = params;
        doBackgroundWork();
        return true;
    }

    private void doBackgroundWork() {
        new AsyncTaskNews(this).execute();
    }

    private String getDate() {
        return DateFormat.format("dd MMM HH:mm:ss", new Date()).toString();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        return true;
    }

    private void showNotification(Context context, CharSequence title, Articles message) {
        final NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 666, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notificationBuilder;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationBuilder = new Notification.Builder(context, "666");
        } else {
            notificationBuilder = new Notification.Builder(context);
        }
        notificationBuilder
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(message.getArticles().get(0).toString())
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_popup_reminder)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .build();
        managerCompat.notify(666, notificationBuilder.build());
    }

    @Override
    public void onResultCallback(Pair<Articles, String> result) {
        showNotification(this, getDate(), result.first);
        Intent intent = new Intent(MainActivity.NEW_DATA);
        intent.putExtra(MainActivity.DATA_ARTICLES, result.first);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        jobFinished(param, false);
    }
}
