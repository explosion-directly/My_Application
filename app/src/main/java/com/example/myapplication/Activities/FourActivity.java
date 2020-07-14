package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FourActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageView;    //该页的头像
    private TextView  tv_notice;    //包裹已经寄出
    private TextView  tv_msg ;      //包裹已经寄出的详细信息
    private Button    bt_consultiong ;  //在线咨询的按钮
    private CircleImageView civ_addr;   //包裹地址的百度地图
    private TextView   tv_addr;         //粗略的地址信息
    private CircleImageView civ_addr_icon ;//地址的图标
    private CircleImageView civ_phone_icon; //电话的图标
    private TextView   tv_order_price ;// 订单价格
    private TextView   tv_paid_price ;  //实付价格
    private TextView   tv_sending_addr; //寄件地址
    private TextView   tv_getting_addr;  //收件地址
    private TextView   tv_order_number;  //订单编号
    private TextView   tv_order_time;   //下单时间
    private TextView   tv_delivery_type;//快递类型
    private TextView   tv_delivery_number; //快递单号


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add(this);
        setContentView(R.layout.activity_four);
        initView();
    }
    public void initView(){
        imageView=findViewById(R.id.iv_activity_four_view);  //大头像图标
        tv_notice=findViewById(R.id.tv_activity_four_notice);  //中间的简要提示信息
        tv_msg =findViewById(R.id.tv_activity_four_msg);  //详细的信息
        bt_consultiong=findViewById(R.id.bt_activity_four_consultion);  //在线咨询按钮
        civ_addr=findViewById(R.id.profile_image);  //地址的头像
        civ_addr_icon=findViewById(R.id.profile_image_addr);   //地址的小地图
        civ_phone_icon=findViewById(R.id.profile_image_phone); //电话的地图
        tv_order_price=findViewById(R.id.tv_activity_order_price); //订单价格
        tv_paid_price=findViewById(R.id.tv_activity_pay_price);   //实付价格
        tv_sending_addr=findViewById(R.id.tv_activity_four_sending_addr);  //寄件地址
        tv_getting_addr=findViewById(R.id.tv_activity_four_getting_addr);  //收件地址
        tv_order_number=findViewById(R.id.tv_activity_four_order_num);    //订单编号
        tv_order_time=findViewById(R.id.tv_activity_four_order_time);   //下单时间
        tv_delivery_type =findViewById(R.id.tv_activity_four_delivery_style);
        tv_delivery_number=findViewById(R.id.tv_activity_four_delivery_num);
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish(this);
    }

    @Override
    public void onClick(View view) {

    }
}
