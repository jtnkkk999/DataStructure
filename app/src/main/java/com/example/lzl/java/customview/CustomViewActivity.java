package com.example.lzl.java.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lzl.java.customview.sun.SunActivity;
import com.example.lzl.java.myapplication.R;

public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {
        mBt_sun = findViewById(R.id.bt_sun);
        mBt_sun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_sun:
                startActivity(new Intent(this,SunActivity.class));
                break;
        }
    }
}
