package com.nhahv.parsehtml.parse;

import android.os.AsyncTask;
import android.util.Log;

import com.nhahv.parsehtml.app.MyApplication;
import com.nhahv.parsehtml.realm.ItemMusic;
import com.nhahv.parsehtml.realm.ItemType;
import com.nhahv.parsehtml.realm.Music;
import com.nhahv.parsehtml.realm.MusicTop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv on 7/28/2016.
 * <></>
 */
public class ParseHtmlRunTime extends AsyncTask<ItemType, Void, Void> {

    public static boolean isFinish = false;
    private final String TAG = getClass().getSimpleName();

    public void parseItemAlbum(String textSearch) {
        isFinish = false;
        List<MusicTop> mListMusic = new ArrayList<>();
        try {
//            String string = "http://tainhacmp3.net/album/hanh-phuc-noi-nao/ZWZCZ696";
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Elements listSog = dom.select(".content>.play-album>.album-list-song>.list-item");
//            Elements listSog = dom.select(".content>.play-album");
            if (listSog == null) {
                return;
            }

            int size = listSog.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {
                //item number
                String number = listSog
                        .get(i)
                        .select(".item-number>.number")
                        .first()
                        .text();

                // information
                Element information = listSog
                        .get(i)
                        .select(".item-info")
                        .first();
                String name = information
                        .select(".item-name")
                        .first()
                        .text();
                String author = information
                        .select(".item-alias")
                        .first()
                        .text();

                String link = listSog
                        .get(i)
                        .select(".item-download")
                        .first()
                        .select("a.btn")
                        .first()
                        .attr("abs:href");

                Log.d(TAG, "Video " + "\n"
                        + "number " + number + "\n"
                        + "name " + name + "\n"
                        + "author " + author + "\n"
                        + "link " + link);

//                mListMusic.add(new MusicTop(number, name, author, link));
            }
//            if (mListMusic.size() > 0) {
//                MyApplication.mListAlbumMusic.clear();
//                MyApplication.mListAlbumMusic.addAll(mListMusic);
//            }
            isFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseItemMusic(String textSearch) {
        isFinish = false;
        List<Music> mListMusic = new ArrayList<>();
        try {
//            String string = "http://tainhacmp3.net/bai-hat/one-dance/ZW7UIOBA";
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Element listSog = dom.select(".content>.play-song").first();
            if (listSog == null) {
                return;
            }

            Element information = listSog.select(".list-item").first();
            // thumbnail
            String thumbnail = information
                    .select(".item-thumb")
                    .first()
                    .select("img")
                    .first()
                    .absUrl("src");

            String nameMusic = information.select(".item-name").first().text();

            // item information

            String author = information
                    .select(".item-alias")
                    .first()
                    .text();

            String itemPlay = information
                    .select(".item-play")
                    .first()
                    .text();

            String lyric = listSog
                    .select(".song-lyric")
                    .first()
                    .select(".lyric-body")
                    .first()
                    .text();


            Elements listMusic = listSog.select(".related-song>.list-item");

            int size = listMusic.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {

                // information
                Element infor = listMusic.get(i).select(".item-info").first();
                String name = infor.select(".item-name").first().text();
                String link = infor.select(".item-name").first().select("a").first().attr("href");
                String authorInfor = infor.select(".item-alias").first().text();
                String like = infor.select(".item-play").first().text();
                Log.d(TAG, "Video " + "\n"
                        + "images " + thumbnail + "\n"
                        + "author " + author + "\n"
                        + "itemPlay " + itemPlay + "\n"
                        + "lyric " + lyric + "\n"
                        + "name " + name + "\n"
                        + "authorInfor " + authorInfor + "\n"
                        + "link " + link);

                mListMusic.add(new Music(name, author, link, like));

            }
            ItemMusic item = new ItemMusic(nameMusic, author, thumbnail, itemPlay, lyric);
            item.setListMusic(mListMusic);

            if (item != null) {
                MyApplication.itemMusic = item;
            }
            isFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseItemVideo(String textSearch) {
        List<MusicTop> mListMusic = new ArrayList<>();
        try {
            String string = "http://tainhacmp3.net/video-clip/tan-vo/ZW7UDUI8";
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Element listSog = dom.select(".content>.play-video").first();
            if (listSog == null) {
                return;
            }
            // play song
            String video = listSog
                    .select(".song-player").first()
                    .select("video").first()
                    .absUrl("src");

            // item information
            Element information = listSog.select(".list-item").first();

            // thumbnail
            String thumbnail = information
                    .select(".item-thumb")
                    .first()
                    .select("img")
                    .first()
                    .absUrl("src");

            String name = information
                    .select(".item-info").first()
                    .select(".item-name").first()
                    .text();

            String author = information
                    .select(".item-info").first()
                    .select(".item-alias").first()
                    .text();

            String itemPlay = information
                    .select(".item-info").first()
                    .select(".item-play").first()
                    .text();

            Elements listMusic = listSog.select(".related-video>.list-item");

            int size = listMusic.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {

                // information
                String images = listMusic.get(i)
                        .select(".item-thumb").first()
                        .select("a").first().attr("abs:href");

                Log.d(TAG, "parseItemVideo: " + images);

                Element infor = listMusic.get(i).select(".item-info").first();

                String nameVideo = infor
                        .select(".item-name").first()
                        .text();

                String link = infor
                        .select(".item-name").first()
                        .select("a").first()
                        .attr("abs:href");

                String authorVideo = infor.select(".item-alias").first().text();
                String itemPlayVideo = infor.select(".item-play").first().text();

                Log.d(TAG, "parseItemVideo " + "\n"
                        + "video " + video + "\n"
                        + "thumbnail " + thumbnail + "\n"
                        + "nameVideo " + nameVideo + "\n"
                        + "link " + link + "\n"
                        + "link " + author + "\n"
                        + "itemPlay " + itemPlay + "\n"
                        + "authorVideo " + authorVideo + "\n"
                        + "name " + name + "\n"
                        + "itemPlayVideo " + itemPlayVideo + "\n"
                        + "link " + link);

//                mListMusic.add(new MusicTop(number, name, author, link));
            }
//            if (mListMusic.size() > 0) {
//                MyApplication.mListAlbumMusic.clear();
//                MyApplication.mListAlbumMusic.addAll(mListMusic);
//            }
//            isFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(ItemType... itemTypes) {
        ItemType item = itemTypes[0];
        if (item != null) {
            switch (item.getType()) {
                case MyApplication.MUSIC:
                    parseItemMusic(item.getUrl());
                    break;
                case MyApplication.ALBUM:
                    parseItemAlbum(item.getUrl());
                    break;
                case MyApplication.VIDEO:
                    parseItemVideo(item.getUrl());
                    break;
                default:
                    break;
            }
        }
        return null;
    }
}
