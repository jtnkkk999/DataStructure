package com.example.lzl.java.multithread.thread.class2_Immutable;
/**
 *不需要同步的线程安全的类。
 *1.字符串和实例用+号相连接的时候，会调用实例内部的toString的方法。
 *2.多个字符串组成新的字符串StringBuffer比String速度块
 *3.基本类型的包装类都是immutable类。Boolean,Byte,Character,Integer,Short,Float,Double,Long 8种，Void用在反射和序列化中
 *4.Point不是Immutable类
 *5.String的replace方法是不会改变之前的实例，二十替换后返回新的实例。
 *6.获取和释放锁所用的时间是不用锁的2倍，还不算阻塞同步的问题。
 *7.immutable的类，get方法如果返回对象，会使得该类失去不可变性。
 *8.没有声明运算符的变量，只有同包下可见。
 *9.immutablePerson和Mutable类之间的切换。对Mutable类要进行同步操作。
 */
public class Class2_immutable {

    public static void main(String[] args){
        B a = new B();
        B.getA();
    }
}
