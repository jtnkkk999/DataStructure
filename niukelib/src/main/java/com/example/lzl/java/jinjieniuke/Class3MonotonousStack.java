package com.example.lzl.java.jinjieniuke;



import com.example.lzl.java.english.graduate.WordTest;
import com.example.lzl.java.niukelib.binarytree.SearchBinaryTree;
import com.example.lzl.java.test.test;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.LinkedList;
import java.util.Stack;

import sun.reflect.generics.tree.Tree;

/**
 * 单调栈：遍历一遍可以获得左面第一个比他大的，右面第一个比他大的两个值。
 *
 * 题目一：计算二维数组中1围成的矩形的最大面积。
 * 题目二：数组表示环形山，当所有数都不想等的时候 1 = 0；2 = 1； n = 2*n-3;
 *        当有相等的数的时候
 *
 * Morris遍历：当前节点current 1) 如果current没有左孩子，current就一直向右移动。
 *                            2）如果有左孩子：找到左孩子的最右的节点。记录当前节点。mostRight
 *                                         1.如果mostRight的右孩子是null，mostRight.right = current. current向左移动。此时回退到上一个节点
 *                                         存在了mostRight.right中，不需要用栈来回退了。
 *                                         2.如果mostRight的右孩子是current。mostRight.right =null,current = current.right
 *            特点：1）一个节点有左子树，就会回到这个节点两次
 */
public class Class3MonotonousStack {
    public static void main(String[] args){
        //单调栈题目一
        int[][] a = {{1,0,1,1},{1,1,1,1},{1,1,1,0}};
        int[] b = {4,3,2,5,6};
//        System.out.println(maxRectFromBottom(b));
        int[] height = new int[a[0].length];
        int max = Integer.MIN_VALUE;

        for(int i =0;i<a.length;i++){
            for(int j = 0;j<a[i].length;j++){
                if(a[i][j] == 0){
                    height[j] = 0;
                }else{
                    height[j] = height[j]+1;
                }
            }
//            max = Math.max(max,maxRectFromBottom(height));
        }
//        System.out.println(max);
        //单调栈题目二：网易special offer
        int[] c ={3,2,1,3,4,4};
//        System.out.println(circleMountain(c));
        //Morris遍历
        TreeNode tree6 = new TreeNode(7, null, null);
        TreeNode tree5 = new TreeNode(6, null, null);
        TreeNode tree4 = new TreeNode(5, null, null);
        TreeNode tree3 = new TreeNode(4, null, null);
        TreeNode tree2 = new TreeNode(3, tree5, tree6);
        TreeNode tree1 = new TreeNode(2, tree3, tree4);
        TreeNode tree = new TreeNode(1, tree1, tree2);
        Morris(tree);
    }
//========================================Morris遍历============================
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
                    rightNode.rightChild = null;
                    //后序遍历
                    printEdge(current.leftChild);
                }
            }else{
                //先序遍历
//                System.out.println(current.data);
            }
            //中序遍历
//            System.out.println(current.data);
            current = current.rightChild;
        }
        //后序遍历
        printEdge(node);
    }

    private static void printEdge(TreeNode leftChild) {
        TreeNode node = reverse(leftChild);
        while (node.rightChild != null){
            System.out.println(node.data);
            node = node.rightChild;
        }
        System.out.println(node.data);
        reverse(node);
    }

    private static TreeNode reverse(TreeNode leftChild) {
        TreeNode now = leftChild;
        TreeNode pre = null;
        TreeNode next;
        while(now!=null){
            next = now.rightChild;
            now.rightChild = pre;
            pre = now;
            now = next;
        }
        return pre;
    }

    //    public static void Morris(TreeNode node){
//        TreeNode current = node;
//        TreeNode rightNode = null;
//        while(current!=null){
//            rightNode = node.leftChild;
//            //1.判断当前的节点的左孩子是否存在
//            if(rightNode!=null){
//                while(rightNode.rightChild!=null&&rightNode.rightChild!=current){
//                    rightNode = rightNode.rightChild;
//                }
//                if(rightNode.rightChild == null){
//                    rightNode.rightChild = current;
//                    current = current.leftChild;
//                    continue;
//                }else{
//                    rightNode.rightChild = null;
//                }
//            }
//            //2.左孩子不存在，current节点右移
//            current = current.rightChild;
//        }
//
//    }
//========================================题目二================================
    public static int circleMountain(int[] array){
        if(array.length == 0 || array.length == 1){
            return 0;
        }
        if(array.length == 2){
            return 1;
        }
        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;
        for(int i = 0;i<array.length;i++){
            if(array[i]>maxValue){
                maxValue = array[i];
                maxIndex = i;
            }
        }
        Stack<bean> stack = new Stack<>();//单调栈，从大到小，
        bean a  = new bean();
        a.list.addLast(maxIndex);
        a.count++;
        stack.push(a);
        int i = (maxIndex+1)%array.length;
        int result = 0;
        while(i!=maxIndex){
            int ind = stack.peek().list.peek();
            if(array[i]<array[ind]){
                bean b = new bean();
                b.list.addLast(i);
                b.count++;
                stack.push(b);
                i = (i+1)%array.length;
            }else if(array[i]==array[ind]){
                stack.peek().list.addLast(i);
                stack.peek().count++;
                i = (i+1)%array.length;
            }else{
                //计算山的数量
                bean c = stack.pop();
                int length = c.count;
                result = result + length*2 + length*(length-1)/2;
            }
        }
        while(!stack.isEmpty()){
            bean b = stack.pop();
            LinkedList<Integer> list = b.list;
            int length = list.size();
            if(stack.empty()){
                result = result+ length*(length-1)/2;
            }else{
                bean c = stack.pop();
                if(stack.isEmpty()){
                    if(c.count>1){
                        result = result+ length*(length-1)/2 + length*2;
                    }else{
                        result = result+ length*(length-1)/2 + length;
                    }
                }else{
                    result = result+ length*(length-1)/2;
                }
                stack.push(c);
            }
        }
        return result;
    }
    static class bean{
        public LinkedList<Integer> list = new LinkedList<>();//该值的下标链表
        public int count;//当前大小值的个数
    }

//========================================题目一================================
    /**
     * 单调栈计算组最大面积，从小到大
     * @param height
     */
    private static int maxRectFromBottom(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int max = Integer.MIN_VALUE;
        while(i<height.length){
            while(!stack.isEmpty()&& height[i]<height[stack.peek()]){
                int h = height[stack.pop()];
                int left = 0;
                if(stack.isEmpty()){
                    left = -1;
                }else{
                    left = stack.peek();
                }
                max = Math.max(max,(i-left-1)*h);
            }
            stack.push(i);
            i++;
        }
        //此时栈内只有从小到大的数。右面确定，左面变换
        while (!stack.isEmpty()){
            int h = height[stack.pop()];
            int left = 0;
            if(stack.isEmpty()){
                left = -1;
            }else{
                left = stack.peek();
            }
            max = Math.max(max,h*(height.length-left-1));
        }
        return max;
    }
}
