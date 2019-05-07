package com.example.lzl.java.multithread.thread.class4_balking;

public class SaveThread extends Thread{
    private Data mData;
    public SaveThread(String name,Data data){
        super(name);
        mData = data;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mData.save();
        }
    }
}
