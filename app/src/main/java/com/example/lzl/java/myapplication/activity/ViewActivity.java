package com.example.lzl.java.myapplication.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lzl.java.myapplication.R;
import com.example.lzl.java.myapplication.adapter.recycleviewadapter.ViewActivityRecycleViewAdapter;
import com.example.lzl.java.myapplication.bean.ViewActivityRecycleViewBTBean;
import com.example.lzl.java.myapplication.fragement.viewfragement.SunFragement;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewBt;
    private String[] array = {"动感小太阳"};
    private String[] tag = {"sun"};
    private String currentTag = tag[0];
    private Fragment currentFragment;
    private ArrayList<ViewActivityRecycleViewBTBean> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initData();
        initView();
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for(int i = 0;i<array.length;i++){
            ViewActivityRecycleViewBTBean bean = new ViewActivityRecycleViewBTBean();
            bean.setBtName(array[0]);
            mDataList.add(bean);
        }
        replaceFragement();

    }

    private void replaceFragement() {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().detach(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(currentTag);
        if (currentFragment == null) {
            switch (currentTag) {
                case "sun":
                    currentFragment = new SunFragement();
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.ac_view_fragemnt, currentFragment, currentTag).commit();
        }else {
            getSupportFragmentManager().beginTransaction().attach(currentFragment).commit();
        }
    }

    private void initView() {
        //recycleView的使用
        mRecyclerViewBt = findViewById(R.id.ac_view_bt_recycle);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        mRecyclerViewBt.setLayoutManager(gridLayoutManager);
        ViewActivityRecycleViewAdapter adapter = new ViewActivityRecycleViewAdapter(mDataList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("yyy","position的值："+position);
            }
        });
        mRecyclerViewBt.setAdapter(adapter);
    }
}
