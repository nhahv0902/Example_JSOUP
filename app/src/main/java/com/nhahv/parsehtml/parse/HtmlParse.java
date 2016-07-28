package com.nhahv.parsehtml.parse;

import android.os.AsyncTask;
import android.util.Log;

import com.nhahv.parsehtml.app.MyApplication;
import com.nhahv.parsehtml.realm.Album;
import com.nhahv.parsehtml.realm.Music;
import com.nhahv.parsehtml.realm.MusicTop;
import com.nhahv.parsehtml.realm.Video;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv on 7/26/2016.
 * <></>
 */
public class HtmlParse extends AsyncTask<Void, Void, Void> {

    private static final String URL_ALBUM = "http://tainhacmp3.net/";
    private static final String URL_MUSIC = "http://tainhacmp3.net/mp3";
    private static final String URL_VIDEO = "http://tainhacmp3.net/video";
    private static final String URL_TOP_VN = "http://tainhacmp3.net/album/Top100-Nhac-Tre/ZWZB969E";
    private static final String URL_TOP_UK = "http://tainhacmp3.net/album/Top100-Pop/ZWZB96AB";
    private static final String URL_TOP_POP = "http://tainhacmp3.net/album/Top100-Han-Quoc/ZWZB96DC";

    private final String TAG = getClass().getSimpleName();
    public static boolean isFinish = false;

    public void parseAlbum(String textSearch) {
        List<Album> mListAlbum = new ArrayList<>();
        try {
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Elements listSog = dom.select(".content>.list-item");
            if (listSog == null) {
                return;
            }

            int size = listSog.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {
                // thumbnail
                Element song = listSog.get(i).select(".item-thumb").first();

                Element image = song.select(".img-circle").first();
                String stringImages = image.attr("src");

                // information
                Element information = listSog.get(i).select(".item-info").first();
                Element name = information.select(".item-name").first();
                String stringName = name.text();

                Element link = information.select("a").first();
                String linkDownloads = link.attr("href"); // == "/"

                Element author = information.select(".item-alias").first();
                String stringAuthor = author.text();


                Element like = information.select(".item-play").first();
                String stringLike = like.text();

                mListAlbum.add(new Album(stringName, stringAuthor, linkDownloads, stringImages, stringLike));
                Log.d(TAG, "Album " + "\n"
                        + "linkDownloads " + linkDownloads + "\n"
                        + "stringImages " + stringImages + "\n"
                        + "stringName " + stringName + "\n"
                        + "author " + stringAuthor + "\n"
                        + "stringLike " + stringLike);
            }
            if (mListAlbum.size() > 0) {
                MyApplication.mListAlbum.clear();
                MyApplication.mListAlbum.addAll(mListAlbum);
            }
            parseMusic(URL_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseMusic(String textSearch) {
        List<Music> mListMusic = new ArrayList<>();
        try {
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Elements listSog = dom.select(".content>.song-list>.list-item");
            if (listSog == null) {
                return;
            }

            int size = listSog.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {

                // information
                Element information = listSog.get(i).select(".item-info").first();
                Element name = information.select(".item-name").first();
                String stringName = name.text();

                Element link = name.select("a").first();
                String linkDownloads = link.attr("href"); // == "/"

                Element author = information.select(".item-alias").first();
                String stringAuthor = author.text();

                Element like = information.select(".item-play").first();
                String stringLike = like.text();

                Log.d(TAG, "Music " + "\n"
                        + "linkDownloads " + linkDownloads + "\n"
                        + "stringName " + stringName + "\n"
                        + "author " + stringAuthor + "\n"
                        + "stringLike " + stringLike);

                mListMusic.add(new Music(stringName, stringAuthor, linkDownloads, stringLike));
            }
            if (mListMusic.size() > 0) {
                MyApplication.mListMusic.clear();
                MyApplication.mListMusic.addAll(mListMusic);
            }
            parseVideo(URL_VIDEO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseVideo(String textSearch) {
        List<Video> mListVideo = new ArrayList<>();
        try {
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Elements listSog = dom.select(".content>.video-list>.list-item");
            if (listSog == null) {
                return;
            }

            int size = listSog.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {

                //thumbnail

                Element eThumbnail = listSog.get(i).select(".item-thumb").first();

                Element eImages = eThumbnail.select(".video-thumb").first();
                String strThumbnail = eImages.attr("src");


                // information
                Element information = listSog.get(i).select(".item-info").first();
                Element name = information.select(".item-name").first();
                String stringName = name.text();

                Element link = name.select("a").first();
                String linkDownloads = link.attr("href");

                Element author = information.select(".item-alias").first();
                String stringAuthor = author.text();

                Element like = information.select(".item-play").first();
                String stringLike = like.text();

                Log.d(TAG, "Video " + "\n"
                        + "linkDownloads " + linkDownloads + "\n"
                        + "stringName " + stringName + "\n"
                        + "strThumbnail " + strThumbnail + "\n"
                        + "author " + stringAuthor + "\n"
                        + "stringLike " + stringLike);
                mListVideo.add(new Video(stringName, stringAuthor, linkDownloads, strThumbnail, stringLike));
            }
            if (mListVideo.size() > 0) {
                MyApplication.mListVideo.clear();
                MyApplication.mListVideo.addAll(mListVideo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseTop(String textSearch, int type) {
        List<MusicTop> mListMusic = new ArrayList<>();
        try {
            Document dom = Jsoup.connect(textSearch).followRedirects(true).get();
            Elements listSog = dom.select(".content>.play-album>.album-list-song>.list-item");
            if (listSog == null) {
                return;
            }

            int size = listSog.size();
            Log.d(TAG, "HtmlParse " + size);
            for (int i = 0; i < size; i++) {

                // number
                String number = listSog.get(i)
                        .select(".item-number>.number")
                        .first().text();

                // name
                String name = listSog.get(i)
                        .select(".item-info>.item-name")
                        .first().text();
                //author
                String author = listSog.get(i)
                        .select(".item-info>.item-alias")
                        .first().text();

                // link downloads
                String link = listSog.get(i)
                        .select(".item-download")
                        .first().select("a").first().attr("href");
                //thumbnail


                Log.d(TAG, "parseTopVN " + "\n"
                        + "number " + number + "\n"
                        + "name " + name + "\n"
                        + "author " + author + "\n"
                        + "link " + link + "\n");
                mListMusic.add(new MusicTop(number, name, author, link));
            }
            if (mListMusic.size() > 0) {
                switch (type) {
                    case MyApplication.TOP_VN:
                        MyApplication.mListTopVN.clear();
                        MyApplication.mListTopVN.addAll(mListMusic);
                        parseTop(URL_TOP_UK, MyApplication.TOP_US);
                        break;
                    case MyApplication.TOP_US:
                        MyApplication.mListTopUS.clear();
                        MyApplication.mListTopUS.addAll(mListMusic);
                        parseTop(URL_TOP_POP, MyApplication.TOP_POP);
                        break;
                    case MyApplication.TOP_POP:
                        MyApplication.mListTopPop.clear();
                        MyApplication.mListTopPop.addAll(mListMusic);
                        isFinish = true;
                        break;
                    default:
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected Void doInBackground(Void... voids) {
        parseAlbum(URL_ALBUM);
        parseTop(URL_TOP_VN, MyApplication.TOP_VN);
        publishProgress();
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
