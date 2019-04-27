package com.example.lzl.java.multithread.thread.class1_singlethreadedexecution;

/**
 * 门类：1）统计人数。
 *      2）判断当前人和地址是否对应
 *      3）打印结果
 */
public class Gate {
    private String name= "NO";
    private String address="No";
    private int count = 0;
    public synchronized void pass(String name){
        count++;
        this.name = name;
        this.address = name;
        check();
    }
    //===========因为toString和check方法都在pass方法内部。所以只需要同步pass方法就可以。==========
    public String toString(){
        return "NO."+count+"name:"+name+"address"+address;
    }
    private void check(){
        if(name !=address){
            System.out.println("*****BROKEN*******"+toString());
        }
    }
}
