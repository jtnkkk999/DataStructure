package com.example.lzl.java.multithread.thread.class5_product_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * 当生产者和消费者都只有一个的时候：Pipe模式
 *
 * 生产者消费者模式跟guardedSuspension
 * interrupt: 使调用的线程进入中断状态。
 * interrupted:检查并清除中断状态。
 * isInterupted:检查中断状态。
 *
 * InterruptedException 使用方法。
 *
 */
public class Class_Product_Consumer {
    public static void main(String[] args){

    }

    class Host{
        /**
         * 抛出异常交给外面处理。可以终止当前的操作。段开线程的方法。
         * @throws InterruptedException
         */
        public void get() throws InterruptedException {
            if(Thread.interrupted()){
                throw new InterruptedException();
            }
        }
    }
}
