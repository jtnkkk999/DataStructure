package com.example.lzl.java.multithread.thread;

public class MyThread2 {
    //1.多线程评价的标准:1)安全性-不损害对象，线程安全的类   Vector线程安全，ArrayList线程不安全的类（通过一些手段让不安全的安全即线程兼容）
    //                  2）生存性-程序不能停止，该处理的一定能被执行。
    //                  |||死锁，安全性和生存性的相互制约
    //                  3）可复用性
    //                  4）性能：吞吐量，响应性，容量。吞吐量响应性相互制约，吞吐量和安全性相互制约
    //                  必要条件：1）2）  提高质量：3）4）
    //2.课后题的知识：1）所有方法都带有synchorized也并不安全，当要保护的字段声明成public
    //
    public static void main(String[] args){

    }
}
