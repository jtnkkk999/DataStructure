package com.example.lzl.java.myapplication;

import android.Manifest;;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.lzl.java.myapplication.activity.TestActivity;
import com.example.lzl.java.myapplication.activity.ViewActivity;
import com.example.lzl.java.myapplication.utils.Misc;
import com.example.lzl.java.myapplication.utils.Misc2;

/**
 * 入口Activity,包含后续添加demo的入口。
 *
 * 当前包含动态获取权限的简单代码
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_view;
    private Button mBt_test;

    private Thread mThread;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mThread = new Thread(new Runnable() {
//            volatile int i = 0;
//            @Override
//            public void run() {
//                Log.e("==========","!!!!!!!!!!!!!!");
//                Log.e("==========",Thread.currentThread().isInterrupted()+"");
//                while (!Thread.currentThread().isInterrupted()){
//                    i++;
//                    Log.e("===",i+"");
//                }
//            }
//        });
//        mThread.start();
//        Log.e("======",mThread.isAlive()+":"+mThread.toString());
//    }

    public void onClick(View view) {
        mThread.interrupt();
    }

    private volatile int p = 0;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intView();

        Log.e("yyy","权限前的代码");
//        if (!CameraPermissionHelper.hasCameraPermission(this)) {
//            CameraPermissionHelper.requestCameraPermission(this);
//        }
        //判断是否授权，如果没有授权，请求权限，请求权限的过程中后续的代码依然会被调用。
        if(!PermissionsHelper.hasCameraPermission(this)){
            Log.e("yyy","没有授权");
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE
                    },
                    0);
        }
        Log.e("yyy",ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)+"--"
        +ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)+"--"+
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)+"--");
        Log.e("yyy","权限后的代码");
        Misc2 misc2 = new Misc2(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    p++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    Looper.prepare();
//                    Misc.showToast(MainActivity.this,p+"");
//                    Looper.loop();
//                    runOnUiThread(()->Misc.showToast(MainActivity.this,p+""));
                    runOnUiThread(()->misc2.toastShow(p+""));
                }
            }
        }).start();
//        mHandler.post()
    }

    private void intView() {
        mBt_view = findViewById(R.id.bt_view);
        mBt_view.setOnClickListener(this);
        mBt_test = findViewById(R.id.bt_test);
        mBt_test.setOnClickListener(this);
    }

    /**
     * 权限回调代码
     * @param requestCode 请求码：也就是自己填入的对应的值
     * @param permissions 权限数组
     * @param grantResults 请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void show(View view) {
        Log.e("yyy",ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)+"--"
                +ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)+"--"+
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)+"--");
    }

    public void crash(View view) {
        int a = 1/0;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.bt_view:
//                startActivity(new Intent(this,ViewActivity.class));
//                break;
//            case R.id.bt_test:
//                startActivity(new Intent(this,TestActivity.class));
//                break;
//        }
//    }

    //调用的Acitivity加上这个
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
}
