package com.example.lzl.java.baseniuke;

/**
 * 贪心算法：
 *          1）切割金条的问题，贪心策略哈夫曼编码，切割短的和就是所需要的钱数：举例，70切割成两块，不管怎么切一刀都需要70块钱。
 *              哈夫曼编码的解决方案，通过小根堆来实现。
 *          2）两个数组，一个是这个做这个项目要花多少钱，另一个是这个项目能得到多少的利润。给你启动资金（W），一次只能做一个，最多能做（K）个。
 *              先以花费多少进行小根堆排序，然将所有花费少于w的项目弹出来，然后将这些项目丢入收益进行排序的大根堆中，做第一个项目，然后将受益加到W上
 *              然后循环K次。
 *          3）一个会场进行开会，给你每个项目的开始时间和结束时间，让你进行项目的安排，能开最多的会。
 *              通过哪个项目早结束进行贪心
 *          贪心就是经验的积累
 * 暴力递归和动态规划：
 *              *我不知道则么算，但是我知道怎么试出来。动态规划就是用来优化暴力尝试的
 *              1）n!
 *              2)汉诺塔问题
 *              3）打印一个字符串的全部子序列
 *              4）母牛生小牛，N年后总共多少牛的问题，3年后长大：
 *                  f(n) = f(n-1)+f(n-3)
 *                  今年的牛 = 去年的牛+（今年新生的牛）
 *                  今年新生的牛 = 三年前的牛的数量
 *               5）二维数组，每个数都是整数，从左上角到右下角，只能往右和下，沿途数字累加起来，返回最小路径和（暴力递归转成动态规划）
 *                     暴力递归会有很多重复的计算部分（并且与路径无关无-后效性问题）。将重复的计算部分保存。（记忆化搜索）
 *                     （动态规划就是个傻白甜的技术，空间换时间）
 *               6）给一个数组和一个整数。任意选择数组中的数，看能不能加成这个整数。
 * 暴力递归：1.把问题转换为规模缩小了的同类问题的子问题。
 *
 */
public class Class8 {
    public static class  Node{
        public Node(int value,Node next){
            this.value = value;
            this.next = next;
        }
        Node next;
        int value;
    }
    public static void main(String[] args){
        Node head = new Node(1,new Node(2,new Node(3,new Node(4,null))));
        Node pre= null;
        Node next;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        while(pre!=null){
            System.out.println(pre.value);
            pre = pre.next;
        }
    }
}

