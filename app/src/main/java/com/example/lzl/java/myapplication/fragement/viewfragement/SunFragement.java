package com.example.lzl.java.myapplication.fragement.viewfragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lzl.java.myapplication.R;
import com.example.lzl.java.myapplication.view.Sun;

public class SunFragement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_fragement_sun,container,false);
        Button bt = view.findViewById(R.id.bt_start);
        final Sun sun = view.findViewById(R.id.view_sun);
        Button bt_restart = view.findViewById(R.id.bt_restart);
        bt_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sun.restart();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sun.startAnimation();
            }
        });
        return view;
    }
}
