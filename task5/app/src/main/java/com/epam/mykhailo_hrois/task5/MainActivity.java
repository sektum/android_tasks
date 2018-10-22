package com.epam.mykhailo_hrois.task5;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean detailButtonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare layout params, find root layout
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        final LinearLayout linearLayout = findViewById(R.id.root_container);

        //initializing scroll view and inflater
        final ScrollView scrollView = new ScrollView(this);
        final AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater(this);

        //work with textView
        TextView textView = new TextView(this);
        textView.setText(makeSpannableString());
        textView.setTextSize(20f);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        //work with button
        Button btnShow = new Button(this);
        btnShow.setText(R.string.buttonText);
        btnShow.setOnClickListener((v) -> {
            if (isDetailButtonPressed()) {
                scrollView.removeAllViews();
                setDetailButtonPressed(false);
            } else {
                asyncLayoutInflater.inflate(R.layout.complicate_layout, scrollView,
                        (view, i, viewGroup) -> {
                            if (viewGroup != null) {
                                viewGroup.addView(view, params);
                            }
                        });
                setDetailButtonPressed(true);
            }
        });
        linearLayout.addView(textView, params);
        linearLayout.addView(btnShow, params);
        linearLayout.addView(scrollView, params);
    }

    public boolean isDetailButtonPressed() {
        return detailButtonPressed;
    }

    public void setDetailButtonPressed(boolean detailButtonPressed) {
        this.detailButtonPressed = detailButtonPressed;
    }

    private SpannableString makeSpannableString() {
        final SpannableString spannableString = new SpannableString(getResources().getString(R.string.showTimeText));
        String[] textArray = getResources().getString(R.string.showTimeText).split(" ");
        String[] colors = getResources().getStringArray(R.array.colors);
        String[] layouts = getResources().getStringArray(R.array.layouts);
        int[] codes = getResources().getIntArray(R.array.codes);
        int currentPosition = 0;
        int color;
        String layout;
        for (int i = 0; i < textArray.length; i++) {
            for (int j = 0; j < colors.length; j++) {
                if (textArray[i].equals(colors[j])) {
                    color = codes[j];
                    spannableString.setSpan(new ForegroundColorSpan(color), currentPosition, currentPosition + textArray[i].length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                }
            }
            for (int k = 0; k < layouts.length; k++) {
                if (textArray[i].equals(layouts[k])) {
                    layout = layouts[k].substring(0, layouts[k].length() - 1);
                    Log.d("col", layout);
                    final String finalLayout = layout;
                    final Context context = this;
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            startActivity(CalculatorActivity.start(context, finalLayout));
                        }
                    }, currentPosition, currentPosition + textArray[i].length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    break;
                }
            }
            currentPosition = currentPosition + textArray[i].length() + 1;
        }
        return spannableString;
    }
}
