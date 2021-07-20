package com.example.dmsproject0714;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private Context mCtx;

    private ArrayList<MainData> arraylist; // MainData를 리스트배열 arraylist에 넣음

    public static String id;

    public static String editTitle;
    public static String editContent;
    public static boolean editStatus = false;

    public MainAdapter(ArrayList<MainData> arraylist, Context mCtx) { // 생성자
        this.arraylist = arraylist;
        this.mCtx = mCtx;
    }

    @NonNull // null을 허용하지 않는다.
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolder와 레이아웃 파일을 연결해주는 역할

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view); // View를 가져온다.

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        // 뷰 홀더가 필요한 위치에 할당 될 때 호출
        // 실제 각 뷰 홀더에 데이터를 연결해주는 함수


        holder.tv_title.setText(arraylist.get(position).getTv_title());
        holder.tv_content.setText(arraylist.get(position).getTv_content());
        holder.tv_id.setText(arraylist.get(position).getTv_id());
        holder.tv_createDate.setText(arraylist.get(position).getTv_createDate());
        holder.tv_modifiedDate.setText(arraylist.get(position).getTv_modifyDate());


        holder.itemView.setTag(position);

        holder.ib_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업 메뉴
                PopupMenu popupMenu = new PopupMenu(mCtx, v);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        // 수정 클릭
                        if (item.getItemId() == R.id.action_edit) {
                            // 데이터 저장
                            editTitle = arraylist.get(position).getTv_title();
                            editContent = arraylist.get(position).getTv_content();


                            System.out.println("테스트" + editContent);


                            editStatus = true;


                            id = arraylist.get(position).getTv_id();

                            Intent intent = new Intent(v.getContext(), AddActivity.class);
                            v.getContext().startActivity(intent);

                        } // 삭제 클릭
                        else if (item.getItemId() == R.id.action_delete) {

                            // 뷰 (서버 게시물 아이디)
                            id = arraylist.get(position).getTv_id();
                            MainActivity.deletePost(id);
                            remove(holder.getAdapterPosition());

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arraylist ? arraylist.size() : 0);
    } // arraylist가 null이 아닐 경우 arraylist.size() 반환

    public void remove(int position) {
        try { // 예외사항이 생겨도 강제실행
            arraylist.remove(position);
            notifyItemRemoved(position); // 새로고침
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_title;
        protected TextView tv_content;
        protected TextView tv_id;
        protected TextView tv_createDate;
        protected TextView tv_modifiedDate;

        protected ImageButton ib_more;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ib_more = (ImageButton) itemView.findViewById(R.id.ib_more);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_conent);
            this.tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            this.tv_createDate = (TextView) itemView.findViewById(R.id.tv_createDate);
            this.tv_modifiedDate = (TextView) itemView.findViewById(R.id.tv_modifiedDate);
        }
    }
}