package com.example.dmsproject0714;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private ImageButton ib_back;
    private ImageButton ib_next;

    private EditText et_title;
    private EditText et_content;

    public static String post_title;
    public static String post_content ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_content = (EditText) findViewById(R.id.et_content);
        et_title = (EditText) findViewById(R.id.et_title);

        // 게시글 수정으로 넘어온 경우 1회용
        if(MainAdapter.editStatus) {

            et_title.setText(MainAdapter.editTitle);
            et_content.setText(MainAdapter.editContent);
        }

        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainAdapter.editStatus) { // 수정 모드 : 뒤로가기 X
                    Toast.makeText(AddActivity.this, "수정 모드에서는 뒤로가기가 불가능합니다.", Toast.LENGTH_SHORT).show();
                } else finish();
            }
        });

        ib_next = (ImageButton) findViewById(R.id.ib_next);
        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String et_title_content = et_title.getText().toString();
                String et_content_content = et_content.getText().toString();

                System.out.println("test" + et_content_content);

                if(!(et_title_content.getBytes().length <= 0 && et_content_content.getBytes().length <= 0)) { // EditText가 비지 않는 경우

                    // 백엔드로 데이터 보내주기
                    post_title = et_title.getText().toString();
                    post_content = et_content.getText().toString();
                    if(MainAdapter.editStatus) {
                        MainActivity.editPost(MainAdapter.id, post_title, post_content);
                    }
                    else {
                        MainActivity.createPost(post_title, post_content);
                    }

                    Toast.makeText(AddActivity.this, "success", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "제목 또는 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                if(MainAdapter.editStatus = true) MainAdapter.editStatus = false;
            }
        });

    }
}