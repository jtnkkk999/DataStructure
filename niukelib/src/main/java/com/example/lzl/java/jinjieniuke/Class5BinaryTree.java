package com.example.lzl.java.jinjieniuke;


/**
 * 二叉树问题的套路：整棵树的问题可以转换成所有节点作为头节点的结果。(树形DP = 在树上做动态规划)
 *
 * 搜索二叉树：
 */
public class Class5BinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) {
            if(x!=null)
                val = x;
        }
    }
    public static void main(String[] args){

    }
    public static boolean isBalanceTree(TreeNode node){
        returnData data = getHeight(node);
        return data.isBalance;
    }
    public static returnData getHeight(TreeNode node){
        if(node == null){
            return new returnData(true,0);
        }
        returnData leftData = getHeight(node.left);
        returnData rightData = getHeight(node.right);
        if(leftData.isBalance == true&&rightData.isBalance==true){
            if(Math.abs(leftData.height-rightData.height)<=1){
                return new returnData(true,Math.max(leftData.height+1,rightData.height+1));
            }else{
                return new returnData(false,-1);
            }
        }else{
            return new returnData(false,-1);
        }

    }
    public static class returnData{
        public boolean isBalance;
        public int height;
        public returnData(boolean isBalance,int height){
            this.isBalance = isBalance;
            this.height = height;
        }
    }
}
