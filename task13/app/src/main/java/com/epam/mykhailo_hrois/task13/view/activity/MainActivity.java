package com.epam.mykhailo_hrois.task13.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epam.mykhailo_hrois.task13.R;
import com.epam.mykhailo_hrois.task13.presenter.ExchangeRatePresenter;
import com.epam.mykhailo_hrois.task13.presenter.Presenter;
import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;

public class MainActivity extends AppCompatActivity implements ViewContract {

    private Presenter mPresenter;

    private EditText editFirst;
    private EditText editSecond;
    private Button buttonShow;
    private TextView showText;
    private TextView showRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFirst = findViewById(R.id.editFirst);
        editSecond = findViewById(R.id.editSecond);
        buttonShow = findViewById(R.id.buttonShow);
        showText = findViewById(R.id.showText);
        showRate = findViewById(R.id.showRate);

        mPresenter = new ExchangeRatePresenter(this);

        buttonShow.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if (editFirst.getText() != null && editSecond.getText() != null) {
                    mPresenter.textEdited(editFirst.getText().toString(), editSecond.getText().toString());
                }
                hideSoftKeyboard(editFirst);
                hideSoftKeyboard(editSecond);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showRate(RatesModel ratesModel, boolean isPositive) {
        if (isPositive) {
            showText.setTextColor(Color.GREEN);
        } else {
            showText.setTextColor(Color.RED);
        }
        showText.setText(String.valueOf(ratesModel.getChangeCourse()));
        showRate.setText(String.valueOf(ratesModel.getCurrentCourse()));
    }

    protected void hideSoftKeyboard(EditText input) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }
}
