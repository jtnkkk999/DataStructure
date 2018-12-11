package com.example.lzl.java.niukelib;

import java.util.jar.JarEntry;

public class MyClass {
    public static void main(String[] args){
        int[] array = {5,5,5,5};
        swap(array,0,0);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }

    }
    public static void swap(int[] st,int i ,int j){
        st[i] = st[i]^st[j];
        st[j] = st[j]^st[i];
        st[i] = st[j]^st[i];
    }
}
