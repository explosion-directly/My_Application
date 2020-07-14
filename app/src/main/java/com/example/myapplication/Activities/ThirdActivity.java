package com.example.myapplication.Activities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.Fragments.FirstFragment;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
/*
* 展示全部订单的页面，
*
*
* */
public class ThirdActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private LinearLayout all_linearLayout;
    private LinearLayout paying_linearlayout;
    private LinearLayout getting_linearlayout;
    private LinearLayout finished_linearlayout;
    private LinearLayout closed_linearlayout;
    private ImageView iv;
    private MyHandler myHandler;
    private ViewPager vp;
    private int offset;  //移动条图片的偏移量
    private int bmpwidth;  //图片的宽度
    private int curindex;  //当前页面的编号
    private int one; //移动一页的距离
    private int two; //移动两页的距离
    private ArrayList<View> viewlist;
    private ArrayList<Fragment> fragment_list;
    private int screenw;
    private int origin_layout_width_Params;  //当前imageview的原宽度
    private ViewGroup.LayoutParams layoutParams;
    private int origin_position_pixels;
    private int mViewPagerIndex;
    private GestureDetectorCompat  mDetector;



    @Override
    public boolean onTouchEvent(MotionEvent event) {


        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int left = iv.getLeft();
            int right = iv.getRight();
            int bottom = iv.getBottom();
            int top = iv.getTop();
            if(msg.what==1) {
                iv.layout(left - 4, top, right, bottom);
            }else{
                iv.layout(left,top,right+4,bottom);
            }

        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int gainwidth = 0;
        if(mViewPagerIndex>position){
            System.out.println("向右拉伸");

            if(mViewPagerIndex!=0) {
                if (positionOffsetPixels>300) {
                    new MyHandler().sendEmptyMessage(1);
                }

            }
        }else {
            /*gainwidth = Math.round((positionOffsetPixels * (one * 2) / params_width) / params_density);
            layoutParams.width = origin_layout_width_Params + gainwidth;*/
            if(mViewPagerIndex!=4) {
                if (positionOffsetPixels<800) {
                    new MyHandler().sendEmptyMessage(2);
                }

            }
        }
        System.out.println("当前拖动位置:" + positionOffset + "拖动后的位置为:" + positionOffsetPixels + "当前位置:" + position);

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = null;
        layoutParams.width=origin_layout_width_Params;
        iv.setLayoutParams(layoutParams);
        switch (position) {
            case 1:
                if (curindex == 0) {
                    animation = new TranslateAnimation(0, one, 0, 0);
                } else if (curindex > 1) {
                    animation = new TranslateAnimation(curindex * one, one, 0, 0);
                }
                break;
            case 0:
                animation = new TranslateAnimation(curindex * one, 0, 0, 0);
                break;
            case 2:
                animation = new TranslateAnimation(curindex * one, 2 * one, 0, 0);
                break;
            case 3:
                animation = new TranslateAnimation(curindex * one, 3 * one, 0, 0);
                break;
            case 4:
                animation = new TranslateAnimation(curindex * one, 4 * one, 0, 0);
                break;

        }
        curindex = position;

        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        iv.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1) {//state有三种状态下文会将，当手指刚触碰屏幕时state的值为1，我们就在这个时候给mViewPagerIndex 赋值。
            mViewPagerIndex = vp.getCurrentItem();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        all_linearLayout = findViewById(R.id.ll_all);
        paying_linearlayout = findViewById(R.id.ll_waiting_paid);
        getting_linearlayout = findViewById(R.id.ll_waiting_thing);
        finished_linearlayout = findViewById(R.id.ll_finished);
        closed_linearlayout = findViewById(R.id.ll_closed);
        mDetector=new GestureDetectorCompat(this,new MyGestureListener());
        all_linearLayout.setOnClickListener(this);
        paying_linearlayout.setOnClickListener(this);
        getting_linearlayout.setOnClickListener(this);
        finished_linearlayout.setOnClickListener(this);
        closed_linearlayout.setOnClickListener(this);
        vp = findViewById(R.id.viewPager);
        iv = findViewById(R.id.iv_thirdActivity);
        initView();
//        myHandler =new MyHandler();
        List<String> list = new ArrayList<>();
        viewlist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
//            View v =LayoutInflater.from(this).inflate(R.layout.)
            list.add("第" + i + "个页面");
        }
        Defaultset();
        curindex = 0;
        fragment_list=new ArrayList<>();
        fragment_list.add(new FirstFragment());
        fragment_list.add(new FirstFragment());
        fragment_list.add(new FirstFragment());
        fragment_list.add(new FirstFragment());
        fragment_list.add(new FirstFragment());
//        vp.setAdapter(new MyPageAdapter(this, list));
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragment_list.get(position);
            }

            @Override
            public int getCount() {
                return fragment_list.size();
            }
        });
        vp.addOnPageChangeListener(this);


    }

    public void Defaultset() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        layoutParams = iv.getLayoutParams();
        origin_layout_width_Params = layoutParams.width;
    }


    @Override
    public void onClick(View view) {
        Animation animation =null;
        int num=-1;
        switch (view.getId()) {
            case R.id.ll_all:
                num=0;
                break;
            case R.id.ll_waiting_paid:
                num=1;
                break;
            case R.id.ll_waiting_thing:
               num=2;
                break;
            case R.id.ll_finished:
                num=3;
                break;
            case R.id.ll_closed:
                num=4;
                break;
        }
        vp.setCurrentItem(num);
        animation=new TranslateAnimation(vp.getCurrentItem()*one,num*one,0,0);
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        iv.startAnimation(animation);//开始动画

    }

    public void initView() {
        bmpwidth = BitmapFactory.decodeResource(getResources(), R.drawable.test3).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenw = dm.widthPixels;
        offset = (screenw / 5 - bmpwidth) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        iv.setImageMatrix(matrix);
        one = bmpwidth + 2 * offset;

    }

    @Override
    protected void onResume() {
        super.onResume();
        origin_position_pixels = 0;
    }

    class MyPageAdapter extends PagerAdapter {
        private Context mcontext;
        private List<String> mData;

        public MyPageAdapter(Context mcontext, List<String> mData) {
            this.mcontext = mcontext;
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = View.inflate(mcontext, R.layout.viewpager_page, null);
            TextView tv = view.findViewById(R.id.tv_pager);
            tv.setText(mData.get(position));
            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            System.out.println("onDown");
            return true;
        }
    }

}


