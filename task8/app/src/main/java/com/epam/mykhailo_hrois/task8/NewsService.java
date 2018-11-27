package com.epam.mykhailo_hrois.task8;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
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
        final int ID = 322;
        final String CHANNEL_ID = "666";

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message.getArticles().get(0).toString())
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{666, 111, 333, 666})
                        .setSmallIcon(android.R.drawable.ic_dialog_info);
        notificationManager.notify(ID, builder.build());
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
