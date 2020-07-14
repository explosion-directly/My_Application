package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ListView listview;
    private String[] title_arr = new String[]{"未签收", "已签收", "转运中", "清关中", "已到达", "配送中", "已送达", "已完成"};
    ;
    private String[] content_arr = new String[]{"请仔细核对 确认订单号正确",
            "国内签收后，七天内会转运", "仓库打包后，已发送菲律宾", "时间不可控 通常需要一周", "请下单预约 才能安排配送"
            , "预约时间内 保持通话畅通", "希望您满意 我们会更努力", "订单交易结束"};
    private int[] icon_arr = new int[]{R.drawable.unreceive, R.drawable.received, R.drawable.transfer,
            R.drawable.custom_clear, R.drawable.arrived, R.drawable.distribute, R.drawable.sent, R.drawable.finished};
    private int[] icon_arr_onclick = new int[]{R.drawable.unreceive_onclick, R.drawable.received_onclick, R.drawable.transfer_onclick,
            R.drawable.custom_clear_onclick, R.drawable.arrived_onclick, R.drawable.distribute_onclick, R.drawable.sent_onclick, R.drawable.finished_onclick};
    private ViewHolder viewHolder = null;
    private int flag = -1;  //判断是否进行view页面的刷新
    private ArrayList<View> viewlist = new ArrayList<>();
    private MyAdapter myAdapter;
    private int[] str = {0, 0, 0, 0, 0, 0, 0, 0};  //用来存储是否点击的效果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.activity_listview);
        myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                updateView(i);
                if (str[i] == 1) {
                    str[i] = 0;
                } else {
                    str[i] = 1;
                }
                System.out.println(str[i]);
                myAdapter.notifyDataSetChanged();

            }
        });


    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return title_arr.length;
        }

        @Override
        public Object getItem(int i) {
            View view = View.inflate(MainActivity.this, R.layout.listview_activity, null);
            TextView title = view.findViewById(R.id.tv_my_view_title);
            TextView content = view.findViewById(R.id.tv_my_view_context);
            title.setText(title_arr[i]);
            content.setText(content_arr[i]);
            return view;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            View v = null;
            TextView title = null;
            TextView content = null;
            ImageView imagev = null;
            switch (str[i]) {
                case 0:
                    v = View.inflate(MainActivity.this, R.layout.listview_activity, null);
                    title = v.findViewById(R.id.tv_my_view_title);
                    content = v.findViewById(R.id.tv_my_view_context);
                    imagev = v.findViewById(R.id.iv_my_view);
                    title.setText(title_arr[i]);
                    content.setText(content_arr[i]);
                    imagev.setImageResource(icon_arr[i]);
                    break;
                case 1:
                    v = View.inflate(MainActivity.this, R.layout.listview_onclick, null);
                    title = v.findViewById(R.id.tv_my_view_title);
                    content = v.findViewById(R.id.tv_my_view_context);
                    imagev = v.findViewById(R.id.iv_my_view_onclick);
                    title.setText(title_arr[i]);
                    content.setText(content_arr[i]);
                    imagev.setImageResource(icon_arr_onclick[i]);
                    break;

            }


            return v;
        }
    }

    class ViewHolder {
        private TextView title_view;
        private TextView content_view;
    }


}
