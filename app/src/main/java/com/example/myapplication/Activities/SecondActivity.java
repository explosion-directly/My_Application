package com.example.myapplication.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
/*
* 智能物流平台
*
* */


public class SecondActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        finish(this);
    }
}
