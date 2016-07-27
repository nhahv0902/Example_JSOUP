package com.nhahv.parsehtml.realm;

import io.realm.RealmObject;

/**
 * Created by Nhahv on 7/27/2016.
 * <></>
 */
public class Album  extends RealmObject {

    private String name;
    private String author;
    private String link;
    private String images;
    private String like;

    public Album() {
    }

    public Album(String name, String author, String link, String images, String like) {
        this.name = name;
        this.author = author;
        this.link = link;
        this.images = images;
        this.like = like;
    }

    public void setAlbum(String name, String author, String link, String images, String like) {
        this.name = name;
        this.author = author;
        this.link = link;
        this.images = images;
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
