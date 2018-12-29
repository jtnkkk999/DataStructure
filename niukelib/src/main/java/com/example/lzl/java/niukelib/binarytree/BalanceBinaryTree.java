package com.example.lzl.java.niukelib.binarytree;

import javafx.scene.transform.Rotate;

/**
 * 平衡二叉树：搜索树的基础上，加了平衡的操作。
 */
public class BalanceBinaryTree {
    TreeNode root;
    int size = 0;
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;
    public void add(int data){
        TreeNode temp = root;
        TreeNode node = new TreeNode(data);
        if(root ==null){
            root = node;
            return;
        }
        //第一步通过搜索二叉树的方式将数据存入
        TreeNode parent = null;
        while(temp!=null){
            parent = node;
            if(temp.data<data){
                temp = temp.rightChild;
            }else{
                temp = temp.leftChild;
            }
        }
        if(parent.data<data){
            parent.rightChild = node;
            node.parent = parent;
        }
        //2.查看是否平衡，采取左右旋转的策略

    }

    /**
     * 左旋操作，需要根据变换图进行写代码
     * @param node
     */
    private void left_rotate(TreeNode node){
        if(node!=null){
            //1.将Y的左孩子交给X的右孩子。
            TreeNode X = node;
            TreeNode Y = node.rightChild;
            X.rightChild = Y.leftChild;
            if(Y.leftChild!=null){
               Y.leftChild.parent = X;
            }
            //2.将Y放到X的位置上。
            Y.parent = X.parent;
            if(X.parent!=null){
                if(X.parent.leftChild == X){
                    X.parent.leftChild = Y;
                }else {
                    X.parent.rightChild = Y;
                }
            }else{
                Y = root;
            }
            //3.将X挂在Y的左孩子上。
            Y.leftChild = X;
            X.parent =Y;

        }
    }

    /**
     * 右旋操作，根据右旋变换图进行代码的填写
     * @param node
     */
    private void right_rotate(TreeNode node){
        if(node!=null){
            TreeNode X = node;
            TreeNode Y = X.leftChild;
            //1.将Y的右孩子接到X的左孩子上。
            X.leftChild= Y.rightChild;
            if(Y.rightChild!=null){
                Y.rightChild.parent = X;
            }
            //2.将Y放在X上
            Y.parent = X.parent;
            if(X.parent.leftChild == X){
                X.parent.leftChild = Y;
            }else{
                X.parent.rightChild = Y;
            }
            //3.将X放在Y的右面
            Y.rightChild = X;
            X.parent = Y;
        }
    }

    /**
     * 二叉搜索树所使用的节点
     */
    public static class TreeNode{
        int data;
        int balance=0;//平衡因子
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;
        public TreeNode(int data){
            this.data = data;
        }
    }
}
