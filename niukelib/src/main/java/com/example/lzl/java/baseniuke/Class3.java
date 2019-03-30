package com.example.lzl.java.baseniuke;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 1.排序的稳定性，当出现相同的值的时候相同值位置不变，即稳定排序。
 * 可做到稳定的排序：冒泡排序，相等的时候不交换位置。
 * 插入排序，当出现相等，不移动，将自己插入前面的位置。
 * 归并排序，相等先拷贝左面的。（可做到额外空间复杂度为1，“归并排序内部缓存法”，原地归并，使时间复杂度变为O(n^2)，不要看）
 * 桶排序，计数排序(桶排序的一个体现)，基数排序（个位，十位，百位。。只需要10个桶）。（O(n)）
 * 不稳定的排序：选择排序，因为要选择最小的值放在第一个位置，会导致后面的顺序都会被打乱。
 * 快速排序做不到稳定性。（基本快排，01 stable sort使快排稳定）
 * 堆排
 * 2.工程排序中的排序方式：
 * 数据长度很长：基础类型：使用快排。（基础类型相同值无差异）
 * 非基础类型：归并。
 * 数据长度很短：插入排序，常数项特别少。长度小于60的时候。小样本飞快。
 * 3.面试一坑：基，偶数左面基数，右面偶数，还维持相对顺序（即稳定），不能使用非比较的排序
 * 4.comparator 和comparable  见Class2
 * comparable(内比较器)
 * 5.无序数组，排完序后求相邻两数的最大插值。O（n）的时间复杂度，所以需要使用桶排序思想。
 * 6.数组实现队列和栈
 * 栈对应的方法，push,peek,pop
 * 队列对应的方法，add,peek,poll
 * 7.实现栈中获取最小值的操作，getmin()时间复杂度需要时O(1)
 */
public class Class3 {
    public static void main(String[] args) {
        //5.
//        int[] a = {1,4,9,6,3,7,13,19,16,40,36,25};
//        System.out.println(searchMaxSpace(a));
        //数组实现栈
//        StackForArray qa = new StackForArray(10);
//        qa.push(1);
//        qa.push(2);
//        qa.push(3);
//        qa.push(4);
//        qa.push(5);
//        qa.push(6);
//        System.out.println(qa.peek());
//        System.out.println(qa.pop());
//        System.out.println(qa.pop());
            //数组实现队列
//        QueueForArray qa = new QueueForArray(4);
//        qa.add(1);
//        qa.add(2);
//        qa.add(3);
//        qa.add(4);
//        qa.add(5);
//        System.out.println(qa.peek());
//        System.out.println(qa.poll());
//        System.out.println(qa.poll());

        //时间复杂度为O（1）的获取栈中的最小值
//        StackForGetMin min = new StackForGetMin();
//        min.push(5);
//        System.out.println(min.getMin());
//        min.push(4);
//        System.out.println(min.getMin());
//        min.push(6);
//        System.out.println(min.getMin());
//        min.push(2);
//        System.out.println(min.getMin());
//        min.push(7);
//        System.out.println(min.getMin());
//        min.pop();
//        System.out.println(min.getMin());
//        min.pop();
//        System.out.println(min.getMin());

          //队列实现栈
//        QueueForStack queueForStack = new QueueForStack();
//        queueForStack.push(1);
//        queueForStack.push(2);
//        queueForStack.push(3);
//        queueForStack.push(4);
//        queueForStack.push(5);
//        System.out.println(queueForStack.pop());
//        System.out.println(queueForStack.pop());
//        System.out.println(queueForStack.pop());
//        queueForStack.push(7);
//        queueForStack.push(8);
//        System.out.println(queueForStack.pop());
//        System.out.println(queueForStack.pop());
//        System.out.println(queueForStack.pop());
        //栈实现队列
        StackForQueue stackForQueue = new StackForQueue();
        stackForQueue.add(1);
        stackForQueue.add(2);
        stackForQueue.add(3);
        stackForQueue.add(4);
        System.out.println(stackForQueue.poll());
        System.out.println(stackForQueue.poll());
        System.out.println(stackForQueue.poll());
        stackForQueue.add(5);
        stackForQueue.add(6);
        System.out.println(stackForQueue.poll());
        System.out.println(stackForQueue.poll());
        System.out.println(stackForQueue.poll());
    }

    /**
     * 无需数组，查找有序后相邻数之间的最大值
     *
     * @param a
     * @return
     */
    private static int searchMaxSpace(int[] a) {
        //1.先遍历一遍数组获取最大值和最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
            min = Math.min(min, a[i]);
        }
        if (min == max) {
            return 0;
        }
        //2.创建桶数组，
        boolean[] hasNumber = new boolean[a.length + 1];
        int[] maxArray = new int[a.length + 1];
        int[] minArray = new int[a.length + 1];
        int bid = 0;//进入哪个桶
        //3.将所有的数放入对应的桶里:注意上面三个数组事桶数组，哪些是数值。
        for (int i = 0; i < a.length; i++) {
            bid = bucket(a[i], a.length, max, min);
            maxArray[bid] = hasNumber[bid] ? Math.max(maxArray[bid], a[i]) : a[i];
            minArray[bid] = hasNumber[bid] ? Math.min(minArray[bid], a[i]) : a[i];
            hasNumber[bid] = true;
        }
        //4.遍历桶，寻找最大插值
        int pre = min;//保存上一个桶里的最大值。
        int maxResult = Integer.MIN_VALUE;
        for (int i = 0; i < a.length + 1; i++) {
            if (hasNumber[i]) {
                maxResult = Math.max(minArray[i] - pre, maxResult);
                pre = maxArray[i];
            }
        }
        return maxResult;
    }

    /**
     * value 值应该在哪个桶里，通过0~100中的10个数来确认结果。但实际上是有11个桶.
     * 最小值一定在0号桶，最大值一定在最后一号桶。
     *
     * @param value  需要放入桶中的值
     * @param length 总共多少个数需要放进桶中，*多一个桶是为了排除掉最大差值在一个桶里的情况。
     * @param max    最大值
     * @param min    最小值
     * @return 返回在哪个桶里
     */
    private static int bucket(int value, int length, int max, int min) {
        return (value - min) * length / (max - min);
    }

    /**
     * 该栈fast指针对应的当前的数，add方法有问题。因为开始的时候0号位置会没有数据。
     */
    static class StackForArray {
        int[] array;
        int count = 0;
        int low = 0;
        int fast = 0;

        public StackForArray(int length) {
            array = new int[length];
        }

        public void push(int value) {
            if (count < array.length) {
                count++;
                array[++fast % (array.length)] = value;
            } else {
                System.out.println("栈已经满了");
            }
        }

        public Integer peek() {
            if (count > 0) {
                return array[fast];
            } else {
                System.out.println("栈为null");
                return null;
            }
        }

        public Integer pop() {
            if (count > 0) {
                int result = 0;
                result = array[fast];
                if (fast == 0) {
                    fast = array.length - 1;
                } else {
                    fast--;
                }
                count--;
                return result;
            } else {
                System.out.println("栈为null");
                return null;
            }
        }


    }

    static class QueueForArray {
        int[] a;
        int count = 0;
        int first = 0;
        int last = 0;

        public QueueForArray(int length) {
            a = new int[length];
        }

        public void add(int value) {
            if (count < a.length) {
                a[first++ % a.length] = value;
                count++;
            } else {
                System.out.println("队列满了");
            }
        }

        public Integer peek() {
            if (count > 0) {
                return a[last];
            } else {
                System.out.println("队列为null");
                return null;
            }
        }

        public Integer poll() {
            if (count > 0) {
                count--;
                return a[last++ % a.length];
            } else {
                System.out.println("队列为null");
                return null;
            }
        }
    }

    /**
     * 用两个栈来实现，一个正常的栈，一个存当前最小值的栈
     */
    static class StackForGetMin {
        Stack<Integer> mStack = new Stack<>();
        Stack<Integer> mMin = new Stack<>();

        public void push(int value) {
            mStack.add(value);
            if(mMin.size() == 0){
                mMin.add(value);
                return;
            }
            int result = 0;
            if (value < mMin.peek()) {
                result = value;
            } else {
                result = mMin.peek();
            }
            mMin.push(result);
        }

        public Integer pop() {
            mMin.pop();
            return mStack.pop();
        }

        public Integer getMin() {
            return mMin.peek();
        }
    }

    /**
     * 队列-》双向链表
     * 用队列实现栈，需要留下栈中最后的值进行返回
     */
    static class QueueForStack{
        Queue<Integer> mQueue1 = new LinkedList<>();//用于存储数据
        Queue<Integer> mQueue2 = new LinkedList<>();//用于取出数据。
        public void push(int value){
            mQueue1.add(value);
        }
        public Integer peek(){
            while(mQueue1.size()>1){
                mQueue2.add(mQueue1.poll());
            }
            swap();
            return mQueue2.peek();
        }

        private void swap() {
            Queue<Integer> temp = new LinkedList<>();
            temp = mQueue1;
            mQueue1 = mQueue2;
            mQueue2 = temp;
        }

        public Integer pop(){
            while(mQueue1.size()>1){
                mQueue2.add(mQueue1.poll());
            }
            swap();
            return mQueue2.poll();
        }
    }

    static class StackForQueue{
        Stack<Integer> mStack1 = new Stack<>();
        Stack<Integer> mStack2 = new Stack<>();
        public void add(int value){
            mStack1.push(value);
        }
        public Integer peek(){
            if(!mStack2.isEmpty()){
                return mStack2.peek();
            }
            while(!mStack1.isEmpty()){
                mStack2.push(mStack1.pop());
            }
            return mStack2.peek();
        }
        public Integer poll(){
            if(!mStack2.isEmpty()){
                return mStack2.pop();
            }
            while(!mStack1.isEmpty()){
                mStack2.push(mStack1.pop());
            }
            return mStack2.pop();
        }
    }

}
