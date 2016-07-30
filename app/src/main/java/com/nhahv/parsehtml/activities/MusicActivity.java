package com.nhahv.parsehtml.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhahv.parsehtml.R;
import com.nhahv.parsehtml.adapters.MusicTopAdapter;
import com.nhahv.parsehtml.app.MyApplication;
import com.nhahv.parsehtml.parse.ParseHtmlRunTime;
import com.nhahv.parsehtml.realm.ItemMusic;
import com.nhahv.parsehtml.realm.ItemType;
import com.nhahv.parsehtml.realm.Music;
import com.nhahv.parsehtml.realm.MusicTop;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, Runnable {

    private static final String TAG = "MusicActivity";
    private static final int HTML_PARSE = 666;
    private List<MusicTop> mListMusic = new ArrayList<>();
    private ItemMusic mMusic = new ItemMusic();
    private Handler mHandler;
    private MusicTopAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == HTML_PARSE) {
                    if (msg.arg1 == 1) {
                        SystemClock.sleep(100);
                        findViewById(R.id.progressBar_music).setVisibility(View.GONE);
                        findViewById(R.id.list_view_music).setEnabled(true);
                        findViewById(R.id.list_view_music).setClickable(true);
                        ParseHtmlRunTime.isFinish = false;

                        mMusic = MyApplication.itemMusic;
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        };

        intData();
        initViews();
    }

    private void intData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Music music = (Music) bundle.getSerializable(MyApplication.MUSIC_ACTIVITY);
            if (music != null) {
                new ParseHtmlRunTime().execute(new ItemType(MyApplication.MUSIC, music.getLink()));
                new Thread(this).start();
                Log.d(TAG, music.getLink());
            }
        }
    }

    private void initViews() {

        mListMusic.addAll(MyApplication.mListAlbumMusic);
        ListView listView = (ListView) findViewById(R.id.list_view_music);
        mAdapter = new MusicTopAdapter(this, R.layout.item_music_top, mListMusic);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void run() {
        while (!ParseHtmlRunTime.isFinish) {
            SystemClock.sleep(100);
        }

        Message message = new Message();
        message.what = HTML_PARSE;
        message.setTarget(mHandler);
        message.arg1 = 1;
        message.sendToTarget();
    }
}
