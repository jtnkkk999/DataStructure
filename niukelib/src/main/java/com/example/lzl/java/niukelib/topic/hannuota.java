package com.example.lzl.java.niukelib.topic;

public class hannuota {
    public static void main(String[] args){
        getResult(3,'A','B','C');
    }

    /**
     *递归问题的经典处理，只找一个小的单元进行处理。
     * 只当成两个盘子的操作 A,B全在第一个棍子上。
     *  从第一个棍子到第三个棍子需要通过第二个棍子所需要的操作行为是：
     *      1.将A放到第二个棍子上
     *      2.将B放在第三个棍子上
     *      3.将A放在第三个棍子上
     *
     *      A,B,C代表每一个棍子的逻辑，首先盘子都在A上
     *      1. A->B
     *      2. A->C
     *      3. B->C
     * @param i 几个数据
     * @param from 从哪里
     * @param inter 通过哪个
     * @param to 到哪里
     */
    private static void getResult(int i, char from, char inter, char to) {
        if(i ==1){
            System.out.println(from+" to "+to);
        }else{
            getResult(i-1,from,to,inter);
            System.out.println(from+" to "+ to);
            getResult(i-1,inter,from,to);
        }
    }
}
