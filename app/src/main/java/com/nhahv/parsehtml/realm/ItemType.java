package com.nhahv.parsehtml.realm;

/**
 * Created by Nhahv on 7/28/2016.
 * <></>
 */
public class ItemType {
    private int type;
    private String url;

    public ItemType(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
