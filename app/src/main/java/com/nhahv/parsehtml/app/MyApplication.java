package com.nhahv.parsehtml.app;

import android.app.Application;

import com.nhahv.parsehtml.realm.Album;
import com.nhahv.parsehtml.realm.ItemMusic;
import com.nhahv.parsehtml.realm.Music;
import com.nhahv.parsehtml.realm.MusicTop;
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

    public static final String KEY_TOP = "KEY_TOP";
    public static final String MUSIC_ACTIVITY = "MUSIC_ACTIVITY";

    public static List<Album> mListAlbum = new ArrayList<>();
    public static List<Music> mListMusic = new ArrayList<>();
    public static List<Video> mListVideo = new ArrayList<>();
    public static List<MusicTop> mListTopVN = new ArrayList<>();
    public static List<MusicTop> mListTopUS = new ArrayList<>();
    public static List<MusicTop> mListTopPop = new ArrayList<>();

    public static List<MusicTop> mListAlbumMusic = new ArrayList<>();

    public static ItemMusic itemMusic = new ItemMusic();

    public static final int TOP_VN = 111;
    public static final int TOP_US = 222;
    public static final int TOP_POP = 333;
    public static int TOP = TOP_VN;

    public static final int MUSIC = 11;
    public static final int ALBUM = 22;
    public static final int VIDEO = 33;


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
