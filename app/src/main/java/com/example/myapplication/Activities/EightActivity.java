package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
/*
* 海运说明的设计图
*
* */
public class EightActivity extends BaseActivity {
    private ListView listView_activity_eight;
    private EightAdapter eightAdapter ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add(this);
        setContentView(R.layout.activity_eight);
        listView_activity_eight=findViewById(R.id.lv_activity_eight);
        eightAdapter =new EightAdapter();
        listView_activity_eight.setAdapter(eightAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish(this);
    }
    class EightAdapter extends BaseAdapter{

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
            View v =View.inflate(EightActivity.this,R.layout.test_page,null);

            return v;
        }
    }
}
