package com.example.lzl.java.jinjieniuke.test;


import java.util.ArrayList;
import java.util.LinkedList;

public class Text {
    public static void main(String[] args){
        //KMP
//        System.out.print(KMP("abcdefghijk","cdf"));
        //Manacher
//        System.out.print(Manacher("abdba"));
        //BFPRT
        int[] a = {4,3,5,2,7,1,1};
//        System.out.print(BFPRT(a,4));
        //滑动窗口：子数组中最大值，最小值
        MoveWindow(a,3);

    }
    //============================滑动窗口=========================
    private static void MoveWindow(int[] a, int i) {
        LinkedList<Integer> max = new LinkedList<>();
        int l =0;
        int r = 0;
        int[] result = new int[a.length-i+1];
        int count = 0;
        while(r<a.length){
            //1.加数逻辑
            while(!max.isEmpty()&&a[r]>=a[max.peekLast()]){
                max.pollLast();
            }
            max.addLast(r);

            if(max.peekFirst()<=r-i){
                max.pollFirst();
            }
//            if(r-l==i){
//                l++;
//                if(max.peekFirst()<=l){
//                    max.pollFirst();
//                }
//            }
            if(r>=i-1){
                result[count++] = a[max.peekFirst()];
            }
            r++;

        }
        for(int j = 0;j<result.length;j++){
            System.out.println(result[j]);
        }
    }

    //============================BFPRT===========================
    private static int BFPRT(int[] a, int i) {
        return bfprt(a,0,a.length-1,i-1);
    }

    private static int bfprt(int[] a, int i, int i1, int i2) {
        if(i == i1){
            return a[i];
        }
        //1.获取中间值,bfprt的优势在于这里
        int center = getCenter(a,i,i1);
        //2.partition操作，小于放左面，等于放中间，大于放右面。
        int[] res = partition(a,i,i1,center);
        if(i2<res[0]){
            return bfprt(a,i,res[0]-1,i2);
        }else if(i2>res[1]){
            return bfprt(a,res[1]+1,i1,i2);
        }else{
            return a[i2];
        }
    }

    private static int[] partition(int[] a, int i, int i1, int center) {
        int l = i-1;
        int r = i1+1;
        int count = i;
        int[] result = new int[2];
        while(count<r){
            if(a[count]<center){
                int temp = a[++l];
                a[l] = a[count];
                a[count++] = temp;
            }else if(a[count]== center){
                count++;
            }else{
                int temp = a[--r];
                a[r] = a[count];
                a[count] = temp;
            }
        }
        result[0] = l+1;
        result[1] = r-1;
        return result;
    }

    private static int getCenter(int[] a, int i, int i1) {
        int count = i1-i+1;
        int[] array = new int[count/5+count%5==0?0:1];
        for(int j =0;j<array.length;j++){
            int start = i+5*j;//0,5
            int end = start +4;
            array[j] = center(a,start,Math.min(end,i1));
        }
        return bfprt(array,0,array.length-1,array.length/2);
    }

    private static int center(int[] a, int start, int end) {
        for(int i =start+1;i<= end;i++){
            int j;
            int temp = a[i];
            for(j =i-1;j>=start;j--){
                if(a[j]>temp){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = temp;
        }
        return a[(start+end)/2+(start+end)%2];
    }


    //============================Manacher========================
    private static int Manacher(String string) {
        char[] array = ManacherString(string);
        int C = 0;
        int R = -1;
        int[] ring = new int[array.length];
        int i = 0;
        int result = Integer.MIN_VALUE;
        while(i<array.length){
            if(i>=R){
                ring[i] = 1;
            }else{
                ring[i] = Math.min(ring[2*C-i],R-i);
            }
            while(i+ring[i]<array.length&&i-ring[i]>=0){
                if(array[i+ring[i]] == array[i-ring[i]]){
                    ring[i]++;
                }else{
                    break;
                }
            }
            if(i+ring[i]>R){
                R = i + ring[i];
                C = i;
            }
            result = Math.max(result,ring[i]);
            i++;
        }
        return result-1;
    }

    private static char[] ManacherString(String string) {
        char[] result = new char[string.length()*2+1];
        for(int i =0;i<result.length;i++){
            if(i%2 == 0){
                result[i] = '#';
            }else{
                result[i] = string.charAt(i/2);
            }
        }
        return result;
    }


    //============================KMP==============================
    private static int KMP(String st1, String st2) {
        //1.计算next数组
        char[] array1 = st1.toCharArray();
        char[] array2 = st2.toCharArray();
        int[] next = getNext(array2);
        int count = 0;
        int index = 0;
        while(count<st1.length()&&index<st2.length()){
            if(array1[count] == array2[index]){
                count++;
                index++;
            }else{
                if(next[index] == -1){
                    count++;
                }else{
                    index = next[index];
                }
            }
        }
        if(index == st2.length()){
            return count-index;
        }
        return -1;
    }

    private static int[] getNext(char[] st2) {
        //next数组是求这个数前面的前缀和后缀，相等时候的最长值。
        int[] next  = new int[st2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int index = next[i-1];
        while(i<st2.length){
            if(st2[i-1] == st2[index]){
                next[i++] = ++index;
            }else{
                if(next[index] ==-1){
                    i++;
                }else{
                    index = next[index];
                }
            }
        }
        return next;
    }
}
