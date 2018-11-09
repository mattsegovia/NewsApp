package com.example.rkjc.news_app_2;

public class NewsItem {

    private String title;
    private String description;
    private String publishedAt;
    private String url;

    public NewsItem(String title, String description, String publishedAt, String url) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Date: " + getPublishedAt() + "\n";
    }
}
