package com.example.lzl.java.multithread.thread.class7_thread_per_message;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 每个消息一个线程
 * 1：匿名内部类是在编译时生成类文件。
 * 作用：1）提高响应性，缩短延迟时间。可以通过Worker Thread来缩短启动线程所花的时间。
 *      2）适用于操作顺序没有要求时。
 *      3）适用于不需要返回值时。
 *      4）应用于服务器。
 *      5）
 *
 */
public class Class_Thread_Pre_Message {
    public static void main(String[] args){
        //ThreadFactory的使用方法：
        //1.通过new ThreadFactor，来创建线程
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        threadFactory.newThread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        //2.通过Executors.defaultThreadFactory()创建线程工厂
        ThreadFactory threadFactory1 = Executors.defaultThreadFactory();
        threadFactory1.newThread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        //3.通过Executor执行线程
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        };
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        //4.ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        //5.
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);//5表示无工作也要运行的工作数量
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {

            }
        },100, TimeUnit.SECONDS);

    }
}
