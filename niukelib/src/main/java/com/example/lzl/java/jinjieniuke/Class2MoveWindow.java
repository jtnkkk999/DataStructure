package com.example.lzl.java.jinjieniuke;


import java.util.LinkedList;

/**
 * 窗口L,R只能往右走，且L不能超过R:1)获取窗口中的最大值的更新结构：双端队列结构（从头到尾是从大到小的，相等也不行）
 * 题目：1）数组中，窗口为3的时候的最大值返回
 *      2）最大值减去最小值小于或等于num的子数组数量
 *
 *
 * 单调栈：
 * 题目：1）求出数组中所有数左面最近的比他大的，右面最近的比他大的数。（O(N)的时间复杂度完成）
 *       2）最大子矩阵大小。
 *
 *
 */
public class Class2MoveWindow {
    public static void main(String[] args){
        //题目1 窗口长度为3，然后一直获取最大值
        int[] a ={4,3,5,4,3,3,6,7};
//        int[] b  =getMoveWindowMax(a,3);
//        for(int i = 0;i<b.length;i++){
//            System.out.println(b[i]);
//        }
        //题目2
    }
    //=================================题目二=================================

    /**
     * 找出所有子数组，其最大值-最小值《=num
     * @param a
     * @param num
     * @return
     */
    public static int getNumber(int[] a,int num){
        LinkedList<Integer> minList = new LinkedList<>();
        LinkedList<Integer> maxList = new LinkedList<>();
        int l = 0;
        int r = 0;
        int res = 0;
        while(l<a.length){
            while(r<a.length){
                //窗口最大值更新结构
                while(!maxList.isEmpty()&&a[maxList.peekLast()]<=a[r]){
                    maxList.pollLast();
                }
                maxList.addLast(r);
                //窗口最小值更新结构
                while(!minList.isEmpty()&&a[minList.peekLast()]>=a[r]){
                    minList.pollLast();
                }
                minList.addLast(r);
                if(a[maxList.peekFirst()] - a[minList.peekFirst()]>num){
                    //此时r的位置的值被存入链表，并且是最大值或者最小值，此时以l开头的所有子串都符合要求。跳出进行计算
                    break;
                }
                r++;
            }
            //因为是不满足条件跳出，所以当跳出的时候双端链表结构收缩。
            if(maxList.peekFirst() ==l){
                maxList.pollFirst();
            }
            if(minList.peekFirst() == l){
                minList.pollFirst();
            }
            res = res+r-l;
            l++;
        }
        return res;
    }

    //======================题目一=====================
    private static int[] getMoveWindowMax(int[] a, int count) {
        LinkedList<Integer> list = new LinkedList<>();
        int[] result = new int[a.length - count + 1];
        int index = 0;
        //数是一个个的进入的~所以不会出现list中数多出来的情况。
        for(int i =0;i<a.length;i++){
            //1.更新加入逻辑
            //不符合要求的出栈，
            while(!list.isEmpty()&&a[list.peekLast()]<=a[i]){
                list.pollLast();
            }
            //加入符合要求的数。
            list.addLast(i);
            //2.删除逻辑
            //判断前面的数是否没有满足要求
            if(list.peekFirst()==i-count){
                list.pollFirst();
            }
            //达到长度的计算返回值。
            if(i>=count-1){
                result[index++] = a[list.peekFirst()];
            }
//            if(list.isEmpty()){
//                list.addLast(i);
//            }else{
//                if(a[list.getLast()]>a[i]){
//                    list.addLast(i);
//                }else{
//                    while(!list.isEmpty()){
//                        int index = list.pollLast();
//                        if(a[index]>a[i]){
//                            list.addLast(i);
//                        }
//                    }
//                    if(list.isEmpty()){
//                        list.addLast(i);
//                    }
//                }
//            }
        }
        return result;
    }
}
