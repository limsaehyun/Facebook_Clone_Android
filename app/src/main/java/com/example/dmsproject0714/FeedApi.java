package com.example.dmsproject0714;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeedApi {

    // 조회
    @GET("posts")
    Call<FeedRequest> reposForUser();

    // 삭제
    @DELETE("posts/{feed_id}")
    Call<FeedResponse> getFeed(@Path("feed_id") String feed_id);

    // 추가
    @POST("posts")
    Call<FeedResponse> createPost(@Body FeedResponse feedResponse);

    // 수정
    @PATCH("posts/{feed_id}")
    Call<FeedResponse> editFeed(
            @Path("feed_id") String feed_id,
            @Body FeedResponse feedResponse
    );

}
