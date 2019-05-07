package com.example.lzl.java.multithread.thread.class3_guardedsuspension;

/**
 * 先处理完当前事，让别人等待一会。
 * 厨师还没做出来饭，那就先等待一会，做完了再吃。
 * 习题问题：1.wait方法会让执行该方法的线程进入这个类的等待队列。
 *          2.应该用while进行判断，而不是用if()获取到锁后应该再次判断守护条件是否成立。notify/notifyALL只是提醒的作用，真是的操作还是要自己确认。
 *          3.线程和同步时候的try（）Catch（）要锁定到具体的某个方法上，否则当其它线程调用interrupt方法后会导致异常。
 *          4.代码让某个方法一直持有锁，守护条件一直不成立，锁也是释放不了，一直在空跑自己的循环---------活锁
 *          5.忽略异常，在守护进程判断的时候会导致interupt方法无法跳出希望的地方，此时可以通过抛出异常让上层的代码去处理这个异常。
 */
public class class3_GuardedSuspension {
    public static void main(String[] args){
        //需要三个东西：1.两个线程，一个生产的线程 2.一个消费的线程 3.一个存储数据的类，也是要进行数据存取进行同步的类。

    }
}
