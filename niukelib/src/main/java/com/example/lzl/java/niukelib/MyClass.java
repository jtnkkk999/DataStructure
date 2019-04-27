package com.example.lzl.java.niukelib;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.jar.JarEntry;

public class MyClass {
    public static void main(String[] args){
//        int[] array = {5,5,5,5};
//        swap(array,0,0);
//        for(int i=0;i<array.length;i++){
//            System.out.println(array[i]);
//        }
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        que.offer(2);
        que.offer(3);
        que.offer(4);
        que.poll();
        Iterator<Integer> iterator = que.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
    public static void swap(int[] st,int i ,int j){
        st[i] = st[i]^st[j];
        st[j] = st[j]^st[i];
        st[i] = st[j]^st[i];
    }
}
