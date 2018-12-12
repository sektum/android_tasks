package com.epam.mykhailo_hrois.task9;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE_UP = "UP";
    public static final String MESSAGE_DOWN = "DOWN";
    
    private ProgressBar progressBar;
    private TextView textView;
    private TestLooper testLooper = new TestLooper(this);
    private TestHandlerThread handlerThread = new TestHandlerThread("TestHandlerThread", this);
    private boolean isPostButtonClicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.textView);

        testLooper.start();
        handlerThread.start();
    }

    public void startLooper(View v) {
        Message message = Message.obtain();
        message.obj = R.string.looper;
        testLooper.getHandler().sendMessage(message);
    }

    public void startHandler(View v) {
        Handler handler = new Handler(handlerThread.getLooper());
        Message message = Message.obtain();
        if (isPostButtonClicked()) {
            message.obj = MESSAGE_UP;
            setPostButtonClicked(false);
        } else {
            message.obj = MESSAGE_DOWN;
            setPostButtonClicked(true);
        }
        handlerThread.getHandler().sendMessage(message);
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 4; i++) {
                    Toast.makeText(MainActivity.this, "toast" + i, Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Logger.getLogger("TestLooper").info("Thread: " + Thread.currentThread().getName() + " background - " + i);
                }
            }
        });
    }

    public void startAsyncTask(View v) {
        TestAsyncTask task = new TestAsyncTask(this);
        task.execute(10);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public TextView getTextView() {
        return textView;
    }

    public boolean isPostButtonClicked() {
        return isPostButtonClicked;
    }

    public void setPostButtonClicked(boolean postButtonClicked) {
        isPostButtonClicked = postButtonClicked;
    }
}
