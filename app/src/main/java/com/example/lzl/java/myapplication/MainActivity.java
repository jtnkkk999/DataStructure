package com.example.lzl.java.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("yyy","权限前的代码");
//        if (!CameraPermissionHelper.hasCameraPermission(this)) {
//            CameraPermissionHelper.requestCameraPermission(this);
//        }
        if(!PermissionsHelper.hasCameraPermission(this)){
            Log.e("yyy","没有授权");
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE}, 0);
        }
        Log.e("yyy",ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)+"--"
        +ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)+"--"+
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)+"--");
        Log.e("yyy","权限后的代码");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void show(View view) {
        Log.e("yyy",ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)+"--"
                +ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)+"--"+
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)+"--");
    }
}
