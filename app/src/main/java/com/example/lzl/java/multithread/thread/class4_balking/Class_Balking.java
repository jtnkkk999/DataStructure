package com.example.lzl.java.multithread.thread.class4_balking;

/**
 * balking模式，停止并返回
 * 1.并不需要执行时。
 * 2.不需要等待守护条件成立时。（条件不成立，要进入下一个操作）
 * 3.守护条件仅在第一次成立时。（初始化执行一次后，不再执行）
 *
 * 4.对guardedsuspension模式
 *
 *
 * 习题知识点：每个线程实例只能执行一次start（）方法(只能在thread处于Thread.State.NEW的时候调用.start（）方法)
 */
public class Class_Balking {
    public static void main(String[] args){
        Data data = new Data("lalala","hha");
        new SaveThread("saveThread",data).start();
        new ChangeThread("changeThread",data).start();
    }
}
