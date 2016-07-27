package com.nhahv.parsehtml.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Nhahv on 7/27/2016.
 * <></>
 */
public class RealmController {

    private static RealmController instance;
    private Realm mRealm;

    public RealmController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }

        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return mRealm;
    }


    /* =============== Album =============*/
    public void addListAlbum(List<Album> albums) {

        mRealm.beginTransaction();
        List<Album> listAlbum = getListAlbum();
        if (listAlbum != null) {
            for (Album album : listAlbum) {
                album.deleteFromRealm();
            }
        }

        for (Album album : albums) {
            Album album1 = mRealm.createObject(Album.class);
            album.setAlbum(album1.getName(), album1.getAuthor(),
                    album1.getLink(), album1.getImages(), album1.getLike());
        }
        mRealm.commitTransaction();
    }

    public RealmResults<Album> getListAlbum() {
        return mRealm
                .where(Album.class)
                .findAll();
    }


    /* =============== Music=============*/
    public void addListMusic(List<Music> musics) {
        mRealm.beginTransaction();
        List<Music> listMusic = getListMusic();
        if (listMusic != null) {
            for (Music music : listMusic) {
                music.deleteFromRealm();
            }
        }

        for (Music music : musics) {
            Music item = mRealm.createObject(Music.class);
            item.setMusic(music.getName(), music.getAuthor(),
                    music.getLink(), music.getLike());
        }
        mRealm.commitTransaction();

    }

    public RealmResults<Music> getListMusic() {
        return mRealm
                .where(Music.class)
                .findAll();
    }

    /* =============== Video =============*/
    public void addListVideo(List<Video> albums) {
        mRealm.beginTransaction();
        List<Video> listAlbum = getListVideo();
        if (listAlbum != null) {
            for (Video album : listAlbum) {
                album.deleteFromRealm();
            }
        }

        for (Video album : albums) {
            Video album1 = mRealm.createObject(Video.class);
            album.setVideo(album1.getName(), album1.getAuthor(),
                    album1.getLink(), album1.getImages(), album1.getLike());
        }

        mRealm.commitTransaction();
    }

    public RealmResults<Video> getListVideo() {
        return mRealm
                .where(Video.class)
                .findAll();
    }

}
