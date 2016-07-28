package com.nhahv.parsehtml.realm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv on 7/28/2016.
 * <></>
 */
public class ItemMusic {

    private String name;
    private String author;
    private String thumbnail;
    private String itemPlay;
    private String lyric;
    private List<Music> listMusic = new ArrayList<>();

    public ItemMusic() {
    }

    public ItemMusic(String name, String author, String thumbnail,
                     String itemPlay, String lyric) {
        this.name = name;
        this.author = author;
        this.thumbnail = thumbnail;
        this.itemPlay = itemPlay;
        this.lyric = lyric;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getItemPlay() {
        return itemPlay;
    }

    public String getLyric() {
        return lyric;
    }

    public List<Music> getListMusic() {
        return listMusic;
    }

    public void setListMusic(List<Music> listMusic){
        if (listMusic != null){
            this.listMusic.clear();
            this.listMusic.addAll(listMusic);
        }
    }
}
