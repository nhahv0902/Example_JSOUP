package com.nhahv.parsehtml.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.parse.HtmlParse;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {

    private static final long TIME_SLEEP = 2000;
    private static final int HTML_PARSE = 100;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new HtmlParse().execute();
        new Thread(this).start();


        ((ProgressBar) findViewById(R.id.progressBar)).
                getIndeterminateDrawable()
                .setColorFilter(this.getResources()
                                .getColor(R.color.colorPrimaryDark),
                        PorterDuff.Mode.SRC_IN);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == HTML_PARSE) {

                    SystemClock.sleep(200);
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    public void run() {
        while (!HtmlParse.isFinish) {
            SystemClock.sleep(100);
        }

        Message message = new Message();
        message.what = HTML_PARSE;
        message.setTarget(mHandler);
        message.arg1 = 1;
        message.sendToTarget();
    }
}
