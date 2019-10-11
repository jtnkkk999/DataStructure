package com.example.lzl.java.english.graduate;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 英语考研基础课的单词短语：
 * 1.legal penalty 合法的处罚
 * 2.security legislation 安全法立
 * 3.is essential to data security
 */
public class WordTest {
    public static void main(String args[]){
        Tree tree6 = new Tree(7, null, null);
        Tree tree5 = new Tree(6, null, null);
        Tree tree4 = new Tree(5, null, null);
        Tree tree3 = new Tree(4, null, null);
        Tree tree2 = new Tree(3, tree5, tree6);
        Tree tree1 = new Tree(2, tree3, tree4);
        Tree tree = new Tree(1, tree1, tree2);
        midTraversal(tree);
    }

    /**
     * 第二次来到这个节点的时候打印
     * @param node
     */
    public static void midTraversal(Tree node){
        Stack<Tree> stack = new Stack();
        stack.push(node);
        while(node!=null||!stack.empty()){
            if(node!=null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                System.out.println(node.value);
                node = node.right;
            }
        }
    }
    static class Tree{
        public Tree left;
        public Tree right;
        public int value;
        public Tree(int value,Tree left,Tree right){
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

}
