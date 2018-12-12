package com.epam.mykhailo_hrois.task9;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;


public class TestHandlerThread extends HandlerThread {
    private Handler mHandler;
    private MainActivity context;

    public TestHandlerThread(String name, MainActivity context) {
        super(name);
        this.context = context;
    }

    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                final String message = (String) msg.obj;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        context.getTextView().setText(message);
                    }
                });
            }
        };
    }

    public Handler getHandler() {
        return mHandler;
    }
}
