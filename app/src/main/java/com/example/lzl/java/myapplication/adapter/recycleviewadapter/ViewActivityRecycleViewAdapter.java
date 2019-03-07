package com.example.lzl.java.myapplication.adapter.recycleviewadapter;

import android.support.annotation.Nullable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lzl.java.myapplication.R;
import com.example.lzl.java.myapplication.bean.ViewActivityRecycleViewBTBean;

import java.util.List;

public class ViewActivityRecycleViewAdapter extends BaseQuickAdapter<ViewActivityRecycleViewBTBean,BaseViewHolder> {
    public ViewActivityRecycleViewAdapter(@Nullable List<ViewActivityRecycleViewBTBean> data) {
        super(R.layout.recycleview_bt, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ViewActivityRecycleViewBTBean item) {
        helper.setText(R.id.tv_recycleview,item.getBtName());
    }
}
