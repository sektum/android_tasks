package com.epam.mykhailo_hrois.task9;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


public class TestLooper extends Thread {
    private Handler mHandler;
    private MainActivity context;

    TestLooper(MainActivity context) {
        this.context = context;
    }

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                context.getTextView().setText((String) msg.obj);
            }
        };
        Looper.loop();
    }

    public Handler getHandler() {
        return mHandler;
    }
}
