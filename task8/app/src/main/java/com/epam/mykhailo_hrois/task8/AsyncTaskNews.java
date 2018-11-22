package com.epam.mykhailo_hrois.task8;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskNews extends AsyncTask<Void,Void,Void> {
    private static final String API_KEY = "eefa8f5b92b24ff7993231986bfa9a96";
    private final JobParameters params;
    String data ="";
    JobService context;

    AsyncTaskNews(JobService context, JobParameters params){
        this.context = context;
        this.params = params;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=ua&apiKey=" + API_KEY);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            context.jobFinished(params, false);
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
