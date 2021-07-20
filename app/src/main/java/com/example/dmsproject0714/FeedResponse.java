package com.example.dmsproject0714;

import java.util.Formatter;

public class FeedResponse {

    private String title;
    private String content;

    public FeedResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}