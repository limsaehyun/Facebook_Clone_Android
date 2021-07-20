package com.example.dmsproject0714;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ArrayList<MainData> arrayList; // MaindData를 담은 배열 리스트인 arrayList를 선언
    private MainAdapter mainAdapter; // 인스턴스 mainAdapter를 선언
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager; // 레이아웃 관리자 선언


    public static boolean changestatus = false;
    private ListView list;
    private ImageButton ib_add;

    // Server

    private FeedResponse responseValue;
    private FeedRequest requestValue;
    private Button btn_Load;
    private Button btn_edit;
    private EditText et_inputId;
    private TextView content_content;

    private FeedApi feedApi;
    private ApiProvider apiProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager); // recyclerView를 통해 리사이클러뷰를 지정


        arrayList = new ArrayList<>();

        mainAdapter = new MainAdapter(arrayList, getApplicationContext()); // 인스턴스 생성
        recyclerView.setAdapter(mainAdapter); // 어댑터를 mainAdapter로 설정

        // move AddActivity
        ib_add = (ImageButton) findViewById(R.id.ib_add);
        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        feedApi = ApiProvider.getInstance().create(FeedApi.class);
    }


    // Resume에서 넘어온 시작 세팅
    private void StartSetPost(FeedRequest feedRequest) {
        int totleElements = feedRequest.getPosts().size();
        for(int i = 0; i < totleElements; i++) {
            JsonObject jsonObject = feedRequest.getPosts().get(i);

            String startId = jsonObject.get("id").toString();
            String startTitle = jsonObject.get("title").toString();
            String startContent = jsonObject.get("content").toString();
            String startCreateDate = jsonObject.get("createDate").toString();
            String startModifyDate = jsonObject.get("modifiedDate").toString();

            startTitle = startTitle.replaceAll("\"", "");
            startContent = startContent.replaceAll("\"", "");
            startContent = startContent.replaceAll("\\\\n" , "\n");

            System.out.println("테스트2" + startTitle);
            System.out.println("테스트2" + startContent);

            MainData mainData = new MainData(startTitle, startContent, startId, startCreateDate, startModifyDate);
            arrayList.add(mainData);

            mainAdapter.notifyDataSetChanged();
        }
    }

    // PATCH
    public static void editPost(String data, String title, String content) {
        FeedApi feedApi = ApiProvider.getInstance().create(FeedApi.class);

        FeedResponse post = new FeedResponse(title, content);

        Call<FeedResponse> call = feedApi.editFeed(data, post);

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {}
            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {}
        });
    }

    // POST
    public static void createPost(String title, String content) {

        FeedApi feedApi = ApiProvider.getInstance().create(FeedApi.class);

        FeedResponse post = new FeedResponse(title, content);
        Call<FeedResponse> call = feedApi.createPost(post);

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {}

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {}
        });

    }

    // DELETE
    public static void deletePost(String data) {
        FeedApi feedApi = ApiProvider.getInstance().create(FeedApi.class);
        feedApi.getFeed(data).enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {}
            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {}
        });
    }

    // 진입
    @Override
    protected void onResume() {
        super.onResume();

        arrayList.clear();
        mainAdapter.notifyDataSetChanged();

        FeedApi feedApi = ApiProvider.getInstance().create(FeedApi.class);

        Call<FeedRequest> call = feedApi.reposForUser();

        call.enqueue(new Callback<FeedRequest>() {
            @Override
            public void onResponse(Call<FeedRequest> call, Response<FeedRequest> response) {
                requestValue = response.body();
                StartSetPost(requestValue);
            }

            @Override
            public void onFailure(Call<FeedRequest> call, Throwable t) {}
        });


    }
}