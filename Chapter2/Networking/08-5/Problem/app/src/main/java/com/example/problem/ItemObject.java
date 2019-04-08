package com.example.problem;

public class ItemObject {

    private String imgUrl;
    private String title;
    private String date;
    private String context;
    private String kind;

    public ItemObject(String imgUrl, String title, String date, String context, String kind) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.date = date;
        this.context = context;
        this.kind = kind;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
