package com.example.lzl.java.customview.sun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lzl.java.myapplication.R;

public class SunActivity extends AppCompatActivity {

    private SunView mSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun);
        mSun = findViewById(R.id.sun);
        mSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSun.startAnim();
            }
        });
    }
}
