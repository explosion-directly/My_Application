package com.example.myapplication.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.CustomView.MyListView;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Utility;
import com.ramotion.foldingcell.FoldingCell;
/*
*
* 这是一个没有作用的效果图，但是蕴含了很多的知识，所以暂且（我思顾我在）。
*
* */
public class SevenActivity extends BaseActivity {
    private ListView listview_item7;
    private ListView listview_item7_click;
    private MyAdapter myAdapter;
    private CommentAdapter commentAdapter;
    private MyListView comment_listview;
    private MyListView com_listview;
    private View v;

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                CommentAdapter ca =new CommentAdapter();
                com_listview.setAdapter(ca);
                Utility.setListViewHeightBasedOnChildren(com_listview);
                myAdapter.notifyDataSetChanged();
                listview_item7.setAdapter(myAdapter);
            }


        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add(this);
        setContentView(R.layout.activity_seven);
        init();
        comment_listview.setAdapter(commentAdapter);

        Utility.setListViewHeightBasedOnChildren(comment_listview);

        listview_item7.setAdapter(myAdapter);

    }
    public void init(){
        listview_item7 =findViewById(R.id.listview_activity_seven);
        v = View.inflate(SevenActivity.this,R.layout.listview_item_seven_illustration,null);
        comment_listview=v.findViewById(R.id.listview_activity8_comment);
        com_listview=v.findViewById(R.id.listview_activity8_comment_click);
        myAdapter =new MyAdapter();
        commentAdapter=new CommentAdapter();


    }


    @Override
    protected void onStop() {
        super.onStop();
        finish(this);
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            final FoldingCell fc = (FoldingCell) v.findViewById(R.id.folding_cell);
            LinearLayout ll_down_drag=v.findViewById(R.id.ll_activity8_down_show);
            fc.initialize(1000, Color.DKGRAY, 2);
            ll_down_drag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new MyHandler().sendEmptyMessage(1);
                    fc.toggle(true);
                }
            });
            /*fc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fc.toggle(true);
                }
            });*/
            return v;
        }
    }

    class CommentAdapter extends  BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v1 =View.inflate(SevenActivity.this,R.layout.activity_comment_style,null);
            return v1;
        }
    }
}
