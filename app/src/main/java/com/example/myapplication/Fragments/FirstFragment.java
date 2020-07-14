package com.example.myapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.R;

public class FirstFragment extends Fragment {
    private ListView lv_fragment;
    //Fragment 的生命週期 Activity oncreate()- onstart()-oncreateview()-onactivitycreate()-onresume()-onpause()-onstop()-on
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_first,null,false);
        lv_fragment =view.findViewById(R.id.lv_fragment);
        lv_fragment.setAdapter(new MyAdapter());

        return view;
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
            return 1;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(FirstFragment.this.getActivity(),R.layout.frament_third_activity,null);
            return v;
        }
    }
}

