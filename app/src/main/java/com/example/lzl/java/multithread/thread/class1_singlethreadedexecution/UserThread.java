package com.example.lzl.java.multithread.thread.class1_singlethreadedexecution;

public class UserThread extends Thread {
    private String name;
    private String address;
    private Gate gate;
    public UserThread(String name,Gate gate){
        this.name = name;
        this.address = name;
        this.gate = gate;
    }

    @Override
    public void run() {
        //判断每个线程开始执行的时间
        System.out.println("BEGIN");
        //每个人，反反复复的进入门中。
        while(true){
            gate.pass(name);
        }
    }
}
