package com.example.lzl.java.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

public class Misc2 {
    private Context context;  //在此窗口提示信息

    private Toast toast = null;  //用于判断是否已有Toast执行
    public Misc2(Context context) {
        this.context = context;
    }
    public void toastShow(String text) {
        if(toast == null)
        {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);  //正常执行
        }
        else {
            toast.setText(text);  //用于覆盖前面未消失的提示信息
        }
        toast.show();
    }
}
