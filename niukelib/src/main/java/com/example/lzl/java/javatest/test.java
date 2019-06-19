package com.example.lzl.java.javatest;

public class test {
    public static void main(String[] args) {
        System.out.println("程序开始");
        final int[] i = {0};
        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println(""+Thread.interrupted());
                try {
                    while (!Thread.interrupted()) {
                        System.out.println(i[0]++);
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        mThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mThread.interrupt();
    }
}
