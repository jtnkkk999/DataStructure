package com.example.lzl.java.multithread.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MyThread {
    //1.thread 的创建
    public static void main(String args){
        //1.线程工厂，讲new Thread 的代码隐藏在了内部
        ThreadFactory factory = Executors.defaultThreadFactory();
        factory.newThread(new print("雪胜")).start();
        //2.线程暂停通过.sleep方法，唤醒需要调用，.interupt()方法
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.interrupted();
        Thread thread = new Thread();
        thread.interrupt();//唤醒线程的方法
        //3.synchronized，判断一个线程是否获得一个对象的锁Thread.holdsLock(对象)
        Thread.holdsLock(thread);//thread是某个对象
        //4.静态方法的锁，锁的对象是（****.class）
        //5.线程执行wait方法后就会进入等待队列，唤醒这个线程的方式：1）notify 2)notifyAll 3)interrupt 4）超时
        //*****要使用wait方法，线程必须持有锁，释放掉锁，进行等待唤醒
        //6.notify,讲当前对象的中的等待队列中线程唤醒。当竞争到锁后，从waite后继续运行
        //7.notufyALL，唤醒该对象等待队列中的所有线程，
        //***没有持有锁的线程调用wait，notify，notifyAll会抛出Monitor异常，这些方法是object里的方法
        //8.线程状态迁移，线程会有6种状态：
        // 1）NEW-初始状态  调用.start（）方法后，
        // 2）进入TUNABLE-可执行/执行状态 Thread.yield()从执行状态转换到可执行状态。
        // 3）run()执行完，进入TERMINATED-终止状态 垃圾回收终止
        //      Thread.sleep() TIMED_WAITING-等待状态 ：1）时间到了2）interrupt3)超时 回到RUNABLE状态
        //      Thread.join() WAITING或者TIMED_WAITING：1）时间到了2）interrupt3)超时 回到RUNABLE状态
        //      Thread.wait() WAITING或者TIMED_WAITING：1）notify/notifyALL2）interrupt3)超时 阻塞到入口队列中，（BLOCKED-阻塞状态），获得锁回到RUNABLE状态
        //      Synchronized 没持有锁阻塞到入口队列中，（BLOCKED-阻塞状态），获得锁回到RUNABLE状态
        //      I/O处理 BLOCKED/WAITING或者TIMED_WAITING 状态  I/O处理完成回到RUNABLE状态
        //9.取消线程处理的中断：interupt,isInterupted，interupted,InteruptedException
        //  线程优先级：setPriority
        //  等待线程终止：join
        //10.阻塞和被阻塞相同的含义
        //11.习题知识点，1）类库妥善的执行了线程互斥的处理
        //              2）thread.sleep()方法调用的是当前线程的.sleep()方法，因为sleep方法是静态方法
        //              3)静态方法和方法的锁不同，所以可以由多个线程同时运行
        //              4）
        thread.getState();//获取线程当前的状态
        try {
            thread.wait();//当前线程进入thread类的等待队列
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class print implements Runnable {
        String message;
        public print(String message){
            this.message = message;
        }
        @Override
        public void run() {
            for(int i = 0;i<100;i++){
                System.out.println(message);
            }
        }
    }
}
