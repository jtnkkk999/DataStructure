package com.example.lzl.java.test;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class leetcode {
    public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(Integer x) {
           if(x!=null)
           val = x;
       }
    }

//    [3,5,1,6,2,0,8,null,null,7,4]
//            5
//            1
    public static void main(String[] args){
        TreeNode a = new TreeNode(3);
        a.left = new TreeNode(5);
        a.right = new TreeNode(1);
        a.left.left = new TreeNode(6);
        a.left.right = new TreeNode(2);
        a.right.left = new TreeNode(0);
        a.right.right = new TreeNode(8);
        a.left.right.left = new TreeNode(7);
        a.left.right.right = new TreeNode(4);
//        System.out.println(maxDepth(a));
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
//        List<List<Integer>> list = levelOrderBottom(a);
//        System.out.println(Arrays.toString(new List[]{list}));
//        System.out.println(tree2str(a));
//        System.out.println(isSymmetric(a));
        System.out.println(result("12-11+(4*2-4*2)+1"));
    }
    public static int result(String string){
        char[] arrays = string.toCharArray();
        return getSum(arrays,0)[0];
    }

    private static int[] getSum(char[] arrays, int j) {
        int i =j;
        int number= 0;
        LinkedList<Integer> stNumber = new LinkedList<>();
        LinkedList<Character> stOP = new LinkedList<>();
        while(i<arrays.length&&arrays[i]!=')'){
            if(arrays[i]=='('){
                int[] re = getSum(arrays,i+1);
                number = re[0];
                i = re[1]+1;
                continue;
            }
            if(arrays[i]>='0'&&arrays[i]<='9'){
                number = number*10+arrays[i]-'0';
                i++;
            }else {
                //加入数值
                stNumber.addLast(number);
                //加入符号
                addOp(stNumber,stOP,arrays[i]);
                number = 0;
                i++;
            }
        }
        if(number!=0){
            char op = stOP.pollLast();
            if(op=='*'||op=='/'){
                int num = stNumber.pollLast();
                if(op =='*'){
                    stNumber.addLast(num*number);
                }else{
                    stNumber.addLast(num/number);
                }
            }else{
                stOP.addLast(op);
                stNumber.addLast(number);
            }
            addOp(stNumber,stOP,'=');
        }
        int[] res = new int[2];
        res[0] = addSum(stNumber,stOP);
        res[1] = i;
        return res;
    }

    private static int addSum(LinkedList<Integer> stNumber, LinkedList<Character> stOP) {
        while(!stOP.isEmpty()){
            char op = stOP.pollFirst();
            if(op =='+'){
                int  num1 = stNumber.pollFirst();
                int num2 = stNumber.pollFirst();
                stNumber.addFirst(num2+num1);
            }else{
                int  num1 = stNumber.pollFirst();
                int num2 = stNumber.pollFirst();
                stNumber.addFirst(num1-num2);
            }
        }
        return stNumber.pollFirst();
    }

    private static void addOp(LinkedList<Integer> stNumber, LinkedList<Character> stOP, char array) {
        if(stOP.isEmpty()){
            stOP.addLast(array);
            return;
        }
        char op = stOP.pollLast();
        switch(op){
            case '+':
                stOP.addLast('+');
                break;
            case '-':
                stOP.addLast('-');
                break;
            case '*':
                stNumber.addLast(stNumber.pop()*stNumber.pop());
                break;
            case '/':
                int num1=stNumber.pollLast();
                int num2 = stNumber.pollLast();
                stNumber.addLast(num2/num1);
                break;
        }
        if(array!='='){
            stOP.addLast(array);
        }

    }

    public static boolean isSymmetric(TreeNode root) {
        if(root == null){
            return false;
        }
        return isResult(root.left,root.right);
//        if(root==null){
//            return true;
//        }
//        LinkedList<Integer> list = new LinkedList<>();
//        LinkedList<Integer> list2 = new LinkedList<>();
//        if(root.left!=null){
//            BFS(root.left,list);
//        }
//        if(root.right!=null){
//            BFS2(root.right,list2);
//        }
//
//        if(list.size()!=list2.size()){
//            return false;
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i)!=list2.get(i)){
//                return false;
//            }
//        }
//        return true;
    }

    private static boolean isResult(TreeNode left, TreeNode right) {
        if(left==null&&right==null){
            return true;
        }
        if(left!=null&&right!=null){
            if(left.val == right.val){
                return isResult(left.left,right.right) && isResult(left.right,right.left);
            }else{
                return false;
            }
        }
        return false;
    }

    private static void BFS(TreeNode left, LinkedList<Integer> list) {
        list.addLast(left.val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(left);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null){
                queue.add(node.left);
                list.add(node.left.val);
            }else{
                list.add(-1);
            }
            if(node.right!=null){
                queue.add(node.right);
                list.add(node.right.val);
            }else{
                list.add(-1);
            }
        }
    }
    private static void BFS2(TreeNode left, LinkedList<Integer> list) {
        list.addLast(left.val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(left);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.right!=null){
                queue.add(node.right);
                list.add(node.right.val);
            }else{
                list.add(-1);
            }
            if(node.left!=null){
                queue.add(node.left);
                list.add(node.left.val);
            }else{
                list.add(-1);
            }
        }
    }


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }

        TreeNode[] result = new TreeNode[1];
        getResult(root,p,q,result);
        return result[0];
    }

    private static int getResult(TreeNode root, TreeNode p, TreeNode q,TreeNode[] result) {
        if(root == null){
            return 0;
        }
        int l = getResult(root.left,p,q,result);
        int r = getResult(root.right,p,q,result);
        int mid = 0;
        if(root ==p||root ==q){
           mid++;
        }
        if(result == null && l+3+mid == 2){
            result[0] = root;
        }
        return l+r+mid;
    }

    public static class res{
        public boolean one;
        public boolean two;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left>right?left+1:right+1;
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length()==0||s ==""||s==null||s.isEmpty()){
            return 0;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        char[] a = s.toCharArray();
        int length = 0;
        int res = -1;
        int pre = 0;
        for (int i = 0; i < a.length; i++) {
            if(map.containsKey(a[i])){
                int j = map.get(a[i]);
                length = i-j;
                for( int w = pre;w<=j;w++){
                    map.remove(a[w]);
                }
                pre = j+1;
                map.put(a[i],i);
                if(length>res){
                    res = length;
                }
            }else{
                map.put(a[i],i);
                length++;
                if(length>res){
                    res = length;
                }
            }
        }
        return res;
    }
    public static String tree2str(TreeNode t) {
        if(t == null){
            return "";
        }
        String l = tree2str(t.left);
        String r = tree2str(t.right);
        if(l == "" && r== ""){
            return t.val+"";
        }
        if(l =="" && r!=""){
            return t.val  + "()" + "("+ r + ")";
        }
        if(l !="" && r==""){
            return t.val  + "("+ l + ")";
        }
        return t.val  + "("+ l + ")" + "("+ r + ")";
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        getAllList(root,list,0);
        return list;
    }

    private static void getAllList(TreeNode root, List<List<Integer>> list, int i) {
        if(root == null){
            return;
        }
        List<Integer> oneList;
        if(list!=null&&list.size()>i){
            oneList = list.get(i);
            oneList.add(root.val);
        }else{
            oneList = new ArrayList<>();
            oneList.add(root.val);
            list.add(oneList);
        }

        if(root.left!=null){
            getAllList(root.left,list,i+1);
        }
        if(root.right!=null){
            getAllList(root.right,list,i+1);
        }
    }
}
