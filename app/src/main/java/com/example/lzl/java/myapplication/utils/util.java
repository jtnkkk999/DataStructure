package com.example.lzl.java.myapplication.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class util {
    //dp转px
    public static float dip2px(float dp) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
