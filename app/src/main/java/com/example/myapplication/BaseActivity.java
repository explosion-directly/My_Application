package com.example.myapplication;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class BaseActivity extends AppCompatActivity {
   private HashSet<Activity> activities =new HashSet<>();
   public void add(Activity activity){
       activities.add(activity);
   }
   public void finish(Activity activity){
        activities.remove(activity);
   }
}
