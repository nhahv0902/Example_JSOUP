package com.nhahv.parsehtml.app;

import android.app.Application;

import com.nhahv.parsehtml.realm.Album;
import com.nhahv.parsehtml.realm.Music;
import com.nhahv.parsehtml.realm.RealmController;
import com.nhahv.parsehtml.realm.Video;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Nhahv on 7/27/2016.
 * <></>
 */
public class MyApplication extends Application {

    public static List<Album> mListAlbum = new ArrayList<>();
    public static List<Music> mListMusic = new ArrayList<>();
    public static List<Video> mListVideo = new ArrayList<>();
    public static RealmController mRealm;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mRealm = RealmController.with(this);
    }


}
