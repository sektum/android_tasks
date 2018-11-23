package com.epam.mykhailo_hrois.task8;

import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.util.Pair;

import com.epam.mykhailo_hrois.task8.entities.Articles;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskNews extends AsyncTask<Void, Void, Pair<Articles, String>> {
    private static final String API_KEY = "eefa8f5b92b24ff7993231986bfa9a96";
    private OnResultReceiverListener onResultReceiverListener;

    @MainThread
    public AsyncTaskNews(OnResultReceiverListener onResultReceiverListener) {
        this.onResultReceiverListener = onResultReceiverListener;
    }

    @Override
    @WorkerThread
    protected Pair<Articles, String> doInBackground(Void... voids) {
        StringBuilder data = new StringBuilder();

        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=ua&apiKey=" + API_KEY);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                data.append(line);
            }
            return new Pair<>(new Gson().fromJson(data.toString(), Articles.class), data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    @MainThread
    protected void onPostExecute(Pair<Articles, String> result) {
        if (onResultReceiverListener != null) {
            onResultReceiverListener.onResultCallback(result);
        }
    }

    public interface OnResultReceiverListener {
        void onResultCallback(Pair<Articles, String> result);
    }

}
