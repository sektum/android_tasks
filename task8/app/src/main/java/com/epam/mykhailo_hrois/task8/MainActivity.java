package com.epam.mykhailo_hrois.task8;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.epam.mykhailo_hrois.task8.adapter.ArticlesAdapter;
import com.epam.mykhailo_hrois.task8.entities.Article;
import com.epam.mykhailo_hrois.task8.entities.Articles;
import com.epam.mykhailo_hrois.task8.entities.Constants;

public class MainActivity extends AppCompatActivity {
    public static final String NEW_DATA = "data.new";
    public static final String DATA_ARTICLES = "data.articles";
    private static final String TAG = "JobService";
    private static final int JOB_ID = 123;
    private Articles articles;
    private ArticlesAdapter articlesAdapter;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            articles = (Articles) intent.getSerializableExtra(DATA_ARTICLES);
            refresh();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleJob();
        initRecycler();
    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.news_recycler);
        LinearLayoutManager linearVertical = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        articlesAdapter = new ArticlesAdapter(this, articles);
        recyclerView.setAdapter(articlesAdapter);
        recyclerView.setLayoutManager(linearVertical);
        articlesAdapter.setOnItemClickListener(new ArticlesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Article article = articles.getArticles().get(position);
                Intent intent = new Intent(MainActivity.this, ShowArticle.class);
                intent.putExtra(Constants.TITLE, article.getTitle());
                intent.putExtra(Constants.DESCRIPTION, article.getDescription());
                intent.putExtra(Constants.URL_TO_IMAGE, article.getUrlToImage());
                intent.putExtra(Constants.URL, article.getUrl());
                intent.putExtra(Constants.DATE, article.getPublishedAt());
                startActivity(intent);
            }
        });
        articlesAdapter.notifyDataSetChanged();
    }

    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, NewsService.class);
        JobInfo info = new JobInfo.Builder(JOB_ID, componentName)
                .setRequiresCharging(false)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(NEW_DATA));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

    private void refresh() {
        if (articles != null && articles.getArticles() != null && articles.getArticles().size() > 0) {
            articlesAdapter.setArticles(articles);
            articlesAdapter.notifyDataSetChanged();
        }
    }
}
