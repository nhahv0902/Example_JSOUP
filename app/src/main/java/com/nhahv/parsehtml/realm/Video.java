package com.nhahv.parsehtml.realm;

import io.realm.RealmObject;

/**
 * Created by Nhahv on 7/27/2016.
 * <></>
 */
public class Video extends RealmObject {

    private String name;
    private String author;
    private String link;
    private String images;
    private String like;

    public Video() {
    }

    public Video(String name, String author, String link, String images, String like) {
        this.name = name;
        this.author = author;
        this.link = link;
        this.images = images;
        this.like = like;
    }

    public void setVideo(String name, String author, String link, String images, String like) {
        this.name = name;
        this.author = author;
        this.link = link;
        this.images = images;
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }

    public String getImages() {
        return images;
    }

    public String getLike() {
        return like;
    }
}
