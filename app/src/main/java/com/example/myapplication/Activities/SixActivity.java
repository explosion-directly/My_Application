package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.CustomView.MyListView;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Utility;

import java.util.ArrayList;

public class SixActivity extends BaseActivity {
    private int[] space =new int[]{0,1,2};
    private RecyclerView recyclerView;
    private ArrayList<String> list=new ArrayList<>();
    private ListView listView;
    private MyListView containerListview;
    private ContentAdapter contentAdapter;
    private View ve;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add(this);
        setContentView(R.layout.activity_six);
        init();
        initList();

        NewAdapter newAdapter =new NewAdapter();
        listView.setAdapter(newAdapter);

    }

    public void init(){
        listView=findViewById(R.id.lv_activity_six_test);
        ve=View.inflate(SixActivity.this,R.layout.activity_part_two,null);
        containerListview=ve.findViewById(R.id.listview);
        contentAdapter=new ContentAdapter();
        containerListview.setAdapter(contentAdapter);
        Utility.setListViewHeightBasedOnChildren(containerListview);

    }

    public void initList(){
        for(int i=0;i<100;i++){
            list.add(i+" ");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish(this);
    }
    /*
    * 视图填充的listview
    *
    * */
    class NewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return space.length;
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
            View v =null;
            switch (space[i]){
                case 0:
                    v=View.inflate(SixActivity.this,R.layout.activity_part_one,null);
                    break;
                case 1:
                    v=ve;
                    break;
                case 2:
                    v=View.inflate(SixActivity.this,R.layout.activity_part_three,null);
                    break;
            }

            return v;
        }
    }
    /*
    * 填充内容的listview Adapter
    *
    *
    * */


    class ContentAdapter extends BaseAdapter{

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
            View v =View.inflate(SixActivity.this,R.layout.listview_activity_six,null);
            return v;
        }
    }
}
