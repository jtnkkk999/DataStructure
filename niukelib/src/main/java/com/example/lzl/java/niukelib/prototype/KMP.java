package com.example.lzl.java.niukelib.prototype;

/**
 * kmp算法查找S1中是否有s2的子元素：
 * 重点求S1的next数组:求每个数之前数字的前缀后缀相等时候的最大长度。
 */
public class KMP {
    public static void main(String[] args){
        String s1 = "qasdfec";
        String s2 = "asd";
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        int[] next = getNext(a2);
        int l1 = 0;
        int l2 = 0;
        while(l1<s1.length()&&l2<s2.length()){
            if(a1[l1]==a2[l2]){
                l1++;
                l2++;
            }else{
                if(next[l2]==-1){
                    l2=0;
                    l1++;
                }else{
                    l2 = next[l2];
                }
            }
        }
        if(l2 == s2.length()){
            System.out.println("存在子集，位置在"+(l1-l2));//下标位置
        }else{
            System.out.println("不存在子集");
        }

    }

    /**
     * 数组表示的是。某个数之前的数中，不算自己的后缀和前缀相等时候的最大长度
     * @param array
     * @return
     */
    public static int[] getNext(char[] array){
        int[] next = new int[array.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int temp = 0;//即表示前缀和后缀相等的最长长度。也表示要验证的前缀数的位置。
        while(i<next.length){
            if(array[i-1]==array[temp]){
                next[i] = ++temp;
            }else{
                if(temp>0){
                    temp = next[temp];
                }else{
                    next[i++] = 0;
                }
            }
        }
        return next;
    }

}
