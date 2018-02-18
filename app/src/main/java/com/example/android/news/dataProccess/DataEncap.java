package com.example.android.news.dataProccess;

/**
 * Created by Abdo on 18/02/2018.
 */

public class DataEncap {
    private String url;
    private String img;
    private String desc;
    private String title;

    public DataEncap() {
    }

    public DataEncap(String url, String img, String desc, String title) {
        this.url = url;
        this.img = img;
        this.desc = desc;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getImg() {
        return img;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }
}