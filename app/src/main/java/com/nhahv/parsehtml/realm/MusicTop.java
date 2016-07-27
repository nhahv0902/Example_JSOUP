package com.nhahv.parsehtml.realm;

/**
 * Created by Nhahv on 7/28/2016.
 * <.
 */
public class MusicTop {
    private String number;
    private String name;
    private String author;
    private String link;

    public MusicTop() {
    }

    public MusicTop(String number, String name, String author, String link) {
        this.number = number;
        this.name = name;
        this.author = author;
        this.link = link;
    }

    public String getNumber() {
        return number;
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
}
