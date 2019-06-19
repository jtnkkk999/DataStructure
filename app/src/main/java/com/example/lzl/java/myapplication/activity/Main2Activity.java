package com.example.lzl.java.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.lzl.java.myapplication.R;

public class Main2Activity extends AppCompatActivity {

    private Thread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mThread = new Thread(new Runnable() {
            volatile int i = 0;
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    i++;
                    Log.e("===",i+"");
                }

//                if(Thread.interrupted()){
//                    try {
//                        throw new InterruptedException();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });
    }

    public void onClick(View view) {
        mThread.interrupt();
    }
}
