package com.epam.mykhailo_hrois.task9;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class TestAsyncTask extends AsyncTask<Integer, Integer, String> {
    private WeakReference<MainActivity> activityWeakReference;

    TestAsyncTask(MainActivity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        MainActivity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        activity.getProgressBar().setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        for (int i = 0; i < integers[0]; i++) {
            publishProgress((i * 100) / integers[0]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return String.valueOf(R.string.finished);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        MainActivity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        activity.getProgressBar().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MainActivity activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
        activity.getTextView().setText(R.string.async_task_completed);
        activity.getProgressBar().setProgress(0);
        activity.getProgressBar().setVisibility(View.INVISIBLE);
    }
}

