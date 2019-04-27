package com.example.lzl.java.multithread.thread.class1_singlethreadedexecution;

import java.util.concurrent.locks.Lock;

public class Class1_SingleThreadedExecution {
    //1.同一时间只允许一个线程执行
    //2.实现代码，多线程情况下，只允许一个人经过，打印出名字和地址，两个变量参数相同
    //结论，当三个人穿行的时候，第100W次才出现错误：1）测试也很难发现错误 2）调试信息也不可靠。
    //      解决方案：代码评审
    //该模式下会发生死锁问题，当满足下列条件：1）存在多个共享角色（类）2）线程持有一个锁后，还想持有另一个锁3）获取两个锁的顺序还不固定。
    //      破坏死锁现象，只需要一个条件不满足即可。
    //3.在多线程中，继承会引起线程安全的问题，继承反常。
    //小知识：HashTable和ConcurrentHashMap功能相似，都是线程安全的，但相应的方法不一样。
    //lock和unlock方法中间出现了return 或者抛出了以常都会导致，锁无法释放，死锁产生。所以需要在lock后进行before/after处理。
    //      **return也会进入finally方法中
    //4.long和double引用和赋值操作不是原子性的。（编程规范这样定义的）
    //5.当赋值操作没有带volatile和synchronized,其它线程不会立刻看到赋值结果。可见性问题。
    //6.semaphore 资源个数有限，计数信号量 semaphore.acquire();//有资源往下走，没资源，线程会阻塞在方法里。
    //7.习题知识点：1）为了尽快的暴露出线程安全性的错误，可以通过在两个赋值语句之间添加睡眠的操作。或者通过Thread.yield()方法，加快线程的切换
    //              2）prtected字段只能被子类和同意包下的类调用
    //8.安全性通过看所有的代码可以确定。生存性看所有代码后都不一定能发现。
    //9.a++ 等同于 int b = a+1; a = b;
    //10.手动加锁一定要写好逻辑，预防死锁问题
    public static void main(String[] args){
        Gate gate = new Gate();
        new UserThread("雪胜",gate).start();
        new UserThread("星星",gate).start();

    }
}
