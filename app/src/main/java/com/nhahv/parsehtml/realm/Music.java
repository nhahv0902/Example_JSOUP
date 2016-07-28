package com.nhahv.parsehtml.realm;

import java.io.Serializable;

/**
 * Created by Nhahv on 7/27/2016.
 * <></>
 */
public class Music  implements Serializable{

    private String name;
    private String author;
    private String link;
    private String like;

    public Music() {
    }

    public Music(String name, String author, String link, String like) {
        this.name = name;
        this.author = author;
        this.link = link;
        this.like = like;
    }

    public void setMusic(String name, String author, String link, String like) {
        this.name = name;
        this.author = author;
        this.link = link;
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

    public String getLike() {
        return like;
    }
}
