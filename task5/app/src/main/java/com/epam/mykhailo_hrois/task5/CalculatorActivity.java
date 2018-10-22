package com.epam.mykhailo_hrois.task5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    static int view;


    public static Intent start(Context context, String layout) {
        Intent intent = new Intent(context, CalculatorActivity.class);
        if (layout.equals("FrameLayout")) {
            view = R.layout.frame_layout_calculator;
        }
        if (layout.equals("LinearLayout")) {
            view = R.layout.linear_layout_calculator;
        }
        if (layout.equals("RelativeLayout")) {
            view = R.layout.relative_layout_calculator;
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
    }
}
