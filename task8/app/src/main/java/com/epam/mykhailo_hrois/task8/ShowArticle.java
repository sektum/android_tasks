package com.epam.mykhailo_hrois.task8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.mykhailo_hrois.task8.entities.Constants;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ShowArticle extends AppCompatActivity {
    private static final String TAG = "ShowArticle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);
        ImageView imageView = findViewById(R.id.fullViewImage);
        String urlToImage = getIntent().getStringExtra(Constants.URL_TO_IMAGE);
        Picasso.with(this)
                .load(urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
        imageView.setVisibility(urlToImage != null ? View.VISIBLE : View.GONE);
        TextView titleTextView = findViewById(R.id.titleText);
        TextView descriptionTextView = findViewById(R.id.descriptionText);
        TextView urlTextView = findViewById(R.id.urlText);
        TextView dateTextView = findViewById(R.id.dateText);
        titleTextView.setText(getIntent().getStringExtra(Constants.TITLE));
        descriptionTextView.setText(getIntent().getStringExtra(Constants.DESCRIPTION));
        urlTextView.setText(getIntent().getStringExtra(Constants.URL));
        String dateText = getIntent().getStringExtra(Constants.DATE);
        Log.d(TAG, "onCreate: " + dateText);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat format2 = new SimpleDateFormat("dd MMM HH:mm:ss", Locale.US);
        String date = "Published at: ";
        try {
            date = date + format2.format(format1.parse(dateText));
        } catch (ParseException e) {
            date = "no date";
        }
        dateTextView.setText(date);
    }
}
