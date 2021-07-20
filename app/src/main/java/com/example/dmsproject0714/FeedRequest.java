package com.example.dmsproject0714;

import com.google.gson.JsonObject;

import java.util.List;

public class FeedRequest {
    private List<JsonObject> posts;

    public List<JsonObject> getPosts() {
        return posts;
    }
}
