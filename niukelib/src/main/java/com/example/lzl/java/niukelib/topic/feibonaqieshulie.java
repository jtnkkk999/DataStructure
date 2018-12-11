package com.example.lzl.java.niukelib.topic;

public class feibonaqieshulie {
    public static void main(String[] args){
        long[] array = new long[10000];
        array[0] = 1;
        array[1] = 1;
        System.out.println(getValue1(array,50));
    }

    /**
     * 效率最低版本的斐波那契数列
     * @param array
     * @param i
     * @return
     */
    public static long getValue(long[] array,int i){
        if(i == 0){
            return array[0];
        }
        if(i == 1){
            return array[1];
        }
        return getValue(array,i-1)+getValue(array,i-2);
    }

    /**
     * 记忆化搜索法 通过数组保存之前的结果
     * @param array
     * @param i
     * @return
     */
    public static long getValue1(long[] array,int i){
        if(i == 0){
            return array[0];
        }
        if(i == 1){
            return array[1];
        }
        if(array[i]!=0){
            return array[i];
        }
        array[i] = getValue1(array,i-1)+getValue1(array,i-2);
        return array[i];
    }

    /**
     * 动态规划方法，待补充
     * @param array
     * @param i
     * @return
     */
    public  static long getValue2(long[] array,int i){

        return 12;
    }
}
