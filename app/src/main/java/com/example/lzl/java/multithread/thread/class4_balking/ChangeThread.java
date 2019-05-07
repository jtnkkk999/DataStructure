package com.example.lzl.java.multithread.thread.class4_balking;

import java.util.Random;

public class ChangeThread extends Thread {
    private Data mData;
    private Random ran = new Random();
    public ChangeThread(String name,Data data){
        super(name);
        mData = data;
    }

    @Override
    public void run() {
        for(int i = 0;true;i++){
            mData.change("NO."+i);
            try {
                sleep(ran.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mData.save();
        }
    }
}
