package com.example.lzl.java.jinjieniuke;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import sun.reflect.generics.tree.Tree;

public class Class1To4AllTheCode {
    public static void main(String[] args){
        //1.KMP
//        System.out.println(KMP("ababaaaa","aaaa"));
        //2.Manachar
//        System.out.print(Manacher("ababaaaa"));
        //3.BFPRT
//        int[] a = {6,5,4,8,9,1};
//        System.out.print(BFPRT(a,4));
//        for(int i = 0;i<a.length;i++){
//            System.out.println(a[i]);
//        }
        //4.滑动窗口-队列在窗口内最小值最大值的更新结构
//        int[] a ={4,3,5,4,3,3,6,7};
//        moveWindow(a);
        //5.单调栈-找到一个数左面第一个比他大的，和右面第一个比他大的数
        //二维数组最大面积问题
        //单调栈题目一
//        int[][] a = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
//        int[] rs = new int[a[0].length];
//        int max = Integer.MIN_VALUE;
//        for(int i = 0;i<a.length;i++){
//            for(int j = 0;j<a[i].length;j++){
//                if(a[i][j] == 1){
//                    rs[j]++;
//                }else{
//                    rs[j] = 0;
//                }
//            }
//            max = Math.max(max,maxRectFromBottom(rs));
//            System.out.println(max);
//        }System.out.println(max);
        //6.morris遍历
//        TreeNode tree6 = new TreeNode(7, null, null);
//        TreeNode tree5 = new TreeNode(6, null, null);
//        TreeNode tree4 = new TreeNode(5, null, null);
//        TreeNode tree3 = new TreeNode(4, null, null);
//        TreeNode tree2 = new TreeNode(3, tree5, tree6);
//        TreeNode tree1 = new TreeNode(2, tree3, tree4);
//        TreeNode tree = new TreeNode(1, tree1, tree2);
//        Morris(tree);
        //
        //连续子数组，最长子数组和为aim，Hashmap的使用。
        int[] a = {7,1,2,3,1,7,-6,-1,7,7};
        System.out.println(maxLength(a,7));
    }
    //============================HashMap的使用========================

    /**
     * 解决的问题是：从最早出现某个数之和，算上当前位置，所以求得的是从该数之后
     * @param a
     * @param aim
     * @return
     */
    public static int maxLength(int[] a,int aim){
        int sum = 0;
        HashMap<Integer,Integer> map = new HashMap<>();//和----序号
        map.put(0,-1);
        int i = 0;
        int max = 0;
        while(i<a.length){
            sum += a[i];
            if(map.containsKey(sum-aim)){
                max = Math.max(max,i-map.get(sum-aim));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
            i++;
        }
        return max;
    }
    //============================Morris========================
    public static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        public TreeNode(int value, TreeNode left, TreeNode right){
            this.leftChild = left;
            this.rightChild = right;
            this.data = value;
        }
    }
    public static void Morris(TreeNode node){
        TreeNode current = node;
        TreeNode rightNode;
        while(current!=null){
            rightNode = current.leftChild;
            if(rightNode!=null){
                while(rightNode.rightChild!=null&&rightNode.rightChild!=current){
                    rightNode = rightNode.rightChild;
                }
                if(rightNode.rightChild == null){
                    rightNode.rightChild = current;
                    //先序遍历
//                    System.out.println(current.data);
                    current = current.leftChild;
                    continue;
                }else{
                    rightNode.rightChild =null;
                    //后序遍历
                    printResult(current.leftChild);
                }
            }else{
                //先序遍历
//                System.out.println(current.data);
            }
            //中序遍历
//            System.out.println(current.data);
            current = current.rightChild;
        }
        printResult(node);
    }
    private static void printResult(TreeNode current) {
        TreeNode pre = reverse(current);
        TreeNode copy = pre;
        while(pre!=null){
            System.out.println(pre.data);
            pre = pre.rightChild;
        }
        reverse(copy);
    }
    public static TreeNode reverse(TreeNode current){
        TreeNode pre = null;
        TreeNode cur = current;
        TreeNode next;
        while(cur!=null){
            next = cur.rightChild;
            cur.rightChild = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //============================单调栈=========================//小的数，从小到大的单调栈
    public static int maxRectFromBottom(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int max = Integer.MIN_VALUE;
        while(i<arr.length){
            while(!stack.isEmpty()&&arr[i]<arr[stack.peek()]){
                int height = arr[stack.pop()];
                int left = 0;
                if(stack.isEmpty()){
                    left = -1;
                }else{
                    left = stack.peek();
                }
                max = Math.max(max,(i-left-1)*height);
            }
            stack.push(i++);
        }
        while(!stack.isEmpty()){
            int left;
            int height = arr[stack.pop()];
            int right = arr.length;
            if(stack.isEmpty()){
                left = -1;
            }else{
                left = stack.peek();
            }
            max = Math.max(max,(right-left-1)*height);
        }
        return max;
    }
    //============================滑动窗口===============================
    private static void moveWindow(int[] a) {
        LinkedList<Integer> maxList = new LinkedList();//从大到小
        for(int i = 0;i<a.length;i++){
            while(!maxList.isEmpty()&&a[i]>a[maxList.peekLast()]){
                maxList.pollLast();
            }
            maxList.addLast(i);
            if(i-maxList.peekFirst()==3){
                maxList.pollFirst();
            }
            if(i>=2){
                System.out.println(a[maxList.peekFirst()]);
            }
        }
    }
    //=====================================KMP============================
    /**
     * KMP算法，是求一个字符串中包含另一个字符串所在的位置。
     */
    public static int KMP(String st1,String st2){
        //1.将两个String转换成字符数组
        char[] arry1 = st1.toCharArray();
        char[] arry2 = st2.toCharArray();
        //2.求的next数组
        int[] next = getNext(arry2);
        //3.求解结果
        int i = 0;
        int j = 0;
        while(i<arry1.length&&j<arry2.length){
            if(arry1[i] == arry2[j]){
                i++;
                j++;
            }else{
                if(next[j] ==-1){
                    i++;
                }else{
                    j = next[j];
                }
            }
        }
        if(j == arry2.length){
            return i-j;
        }
        return -1;
    }

    /**
     * next数组
     * @param arry2
     * @return
     */
    private static int[] getNext(char[] arry2) {
        int[] next = new int[arry2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int index = next[i-1];//指向0下标。也表示0个相等的数
        while(i<arry2.length){
            if(arry2[i-1]==arry2[index]){
                next[i] = ++index;
                i++;
            }else{
                if(next[index]==-1){
                    next[i] = 0;
                    i++;
                }else{
                    index = next[index];
                }
            }
        }
        return next;
    }
    //================================manachar====================
    /**
     * 最长回文字串的长度
     * @param string
     * @return
     */
    public static int Manacher(String string){
        //1.扩充数组。解决奇偶的分析。
        char[] array = string.toCharArray();
        char[] chars = new char[array.length*2+1];
        int index = 0;
        for(int i = 0;i<chars.length;i++){
            if(i%2 == 0){
                chars[i] = '#';
            }else{
                chars[i] = array[index++];
            }
        }
        //2.开始manachar部分
        int R = -1;//回文半径
        int C = 0;
        int[] ring =new int[chars.length];
        int max = Integer.MIN_VALUE;
        int i = 0;
        while(i<chars.length){
            if(R>i){
                //i在R内部的两种情况，1）在R内，在R外和上面。2）然后再往外扩
                ring[i] = Math.min(ring[2*C-i],R-i);
            }else{
                //i>=R，没法加速，把自己算上，然后暴力扩
                ring[i] = 1;
            }
            while(i-ring[i]>=0&&i+ring[i]<chars.length){
                if(chars[i-ring[i]]==chars[i+ring[i]]){
                    ring[i]++;
                }else{
                    break;
                }
            }
            if(i+ring[i]-1>R){
                C = i;
                R = i+ring[i]-1;
            }
            max = Math.max(max,ring[i]);
            i++;
        }
        return max-1;
    }
    //===========================BFPRT==============================

    /**
     * 时间复杂度O(N)
     * @param array
     * @param k
     * @return
     */
    public static int BFPRT(int[] array,int k){
        return bfprt(array,0,array.length-1,k-1);
    }

    private static int bfprt(int[] array, int start, int end, int k) {
        //获取中间值的条件判断
        if(start >=end){
            return  array[start];
        }
        //1.获取一个中间的数
        int center = getCenter(array,start,end);
        //2.partition操作
        int[] res = partition(array,start,end,center);
        if(k<res[0]){
            return bfprt(array,start,res[0]-1,k);
        }else if(k>res[1]){
            return bfprt(array,res[1]+1,end,k);
        }else{
            return array[k];
        }
    }
    private static int[] partition(int[] array, int start, int end, int center) {
        int l = start-1;
        int r = end+1;
        int i = start;
        while(i<r){
            if(array[i]<center){
                int temp = array[++l];
                array[l] = array[i];
                array[i] = temp;
                i++;
            }else if(array[i]>center){
                int temp = array[--r];
                array[r] = array[i];
                array[i] = temp;
            }else{
                i++;
            }
        }
        int[] res = new int[2];
        res[0] = l+1;
        res[1] = end-1;
        return res;
    }
    private static int getCenter(int[] array, int start, int end) {
        int length = end-start+1;
        length = length/5+length%5==0?0:1;
        int[] res = new int[length];
        for(int i = 0;i<res.length;i++){
            int l = start+i*5;
            int r = l+4;
            res[i] = getFiveCenter(array,l,Math.min(end,r));
        }
        return bfprt(res,0,res.length-1,res.length/2);
    }

    private static int getFiveCenter(int[] array, int l, int r) {
        for(int i = l+1;i<=r;i++){
            int j;
            int value = array[i];
            for(j=i-1;j>=l;j--){
                if(array[j]>value){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1] = value;
        }
        return array[(l+r)/2+(l+r)%2];
    }

}
