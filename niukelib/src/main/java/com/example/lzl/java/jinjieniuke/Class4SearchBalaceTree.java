package com.example.lzl.java.jinjieniuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.text.html.parser.Entity;

/**
 * 搜索二叉树：左孩子比他要小，右孩子比他要大。
 * 平衡二叉树：左子树右子树高度差不超过。
 * HashMao:查找O(1)
 * TreeMap:以搜索二叉树组织起来的KEY,即红黑树,查找O(高度)
 *
 * 题目：1.楼层范围
 *       2.连续子数组和为aim，最长的子数组。
 *       3.奇数偶数个数相等的最长子数组。算法同2，基数-1.偶数1
 *       4.子数组异或为0，切成最多个数的异或子数组为0    0和任何数异或 = 任何数
 *
 */
public class Class4SearchBalaceTree {
    static class Node{
        public int situation;
        public int height;
        public boolean isUP;
        public Node(int situation,int height,boolean isUP){
            this.situation = situation;
            this.height = height;
            this.isUP = isUP;
        }
    }
    static class comapre implements Comparator<Node>{

        @Override
        public int compare(Node n1, Node n2) {
            if(n1.situation!=n2.situation){
                return n1.situation - n2.situation;
            }
            if(n1.isUP!=n2.isUP){
                //先下后上
                return n1.isUP? -1:1;
            }
            return 0;
        }
    }
    public static void codeOne(int[][] array){
        Node[] nodes = new Node[2*array.length];
        //1.将二维数组拆分成我们需要的结构，位置-高度-上下
        for(int i = 0;i<array.length;i++){
            int[] a = array[i];
            nodes[i*2] = new Node(a[0],a[2],true);
            nodes[i*2+1] = new Node(a[1],a[2],false);
        }
        Arrays.sort(nodes,new comapre());
        //2.创建一个平衡搜索二叉树，treeMap，一直获取最大的值,key = 加入节点的高度，value = 加入节点的次数。通过treeMap可以一直获得最大的节点高度。
        TreeMap<Integer,Integer> rsMap = new TreeMap<>();
        TreeMap<Integer,Integer> pmMap = new TreeMap<>();
        for(int i = 0;i<nodes.length;i++){
            if(nodes[i].isUP){
                if(!rsMap.containsKey(nodes[i].height)){
                    rsMap.put(nodes[i].height,1);
                }else{
                    rsMap.put(nodes[i].height,rsMap.get(nodes[i].height)+1);
                }
            }else{
                if(rsMap.containsKey(nodes[i].height)){
                    if(rsMap.get(nodes[i].height)==1){
                        rsMap.remove(nodes[i].height);
                    }else{
                        rsMap.put(nodes[i].height,rsMap.get(nodes[i].height)-1);
                    }
                }
            }
            //3.收集每个位置最大高度的信息
            if(rsMap.isEmpty()){
                pmMap.put(nodes[i].situation,0);
            }else{
                pmMap.put(nodes[i].situation,rsMap.lastKey());
            }
        }
        int start = 0;
        int height = 0;
        ArrayList<ArrayList<Integer>> resList = new ArrayList<>();
        while(!pmMap.isEmpty()){
            Map.Entry<Integer, Integer> a = pmMap.pollFirstEntry();
            int currentPosition = a.getKey();
            int CurrentHeight = a.getValue();
            if(height != CurrentHeight){
                if(height!=0){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(currentPosition);
                    list.add(height);
                    resList.add(list);
                }
                start = currentPosition;
                height = CurrentHeight;
            }
        }
        for(int i = 0;i<resList.size();i++){
            for(int j = 0;j<resList.get(i).size();j++){
                System.out.print(resList.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int[][] array = {{1,3,3},{2,4,4},{5,6,1}};
        //题目一
        codeOne(array);
        //题目二
        int[] a = {7,1,2,3,1,7,-6,-1,7,7};
        System.out.println(maxLength(a,7));
        //题目三
    }
    //==================================题目二==========================
    public static int maxLength(int[] arr,int aim){
        HashMap<Integer,Integer> map = new HashMap<>();//sum,算上结尾的位置。
        map.put(0,-1);
        int sum = 0;
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum-aim)){
                length = Math.max(length,i-map.get(sum-aim));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return length;
    }
    //=================================题目三异或切割=============================
    public static int mostEOR(int[] arr){
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int result = 0;
        int xor = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if(map.containsKey(xor)){
                int pre = map.get(xor);
                dp[i] = pre == -1?1:(dp[pre]+1);
            }
            if(i>0){
                dp[i] = Math.max(dp[i-1],dp[i]);
            }
            map.put(xor,i);
            result = Math.max(result,dp[i]);
        }
        return result;
    }

}
