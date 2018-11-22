package com.epam.mykhailo_hrois.task8;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskNews extends AsyncTask<Context, Void, Void> {
    private static final String API_KEY = "eefa8f5b92b24ff7993231986bfa9a96";
    String data = "";

    @Override
    protected Void doInBackground(Context... voids) {
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=ua&apiKey=" + API_KEY);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        NewsService.data = this.data;
    }

}
