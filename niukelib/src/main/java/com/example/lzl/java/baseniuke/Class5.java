package com.example.lzl.java.baseniuke;

import java.util.Stack;

/**
 * 1.二叉树的遍历。递归和非递归方式。递归系统帮助压栈，可以理解成三次来到对应节点的流程。理解清楚后，便可以通过自己进行压栈操作，改成非递归的方式。
 *         前中后续遍历就是压栈方式规律的寻找
 *         *看后序遍历可以深刻理解递归遍历三次来到当前栈位的打印位置（以及遍历方式）1）刚来到该方法及当前节点
 *                                                                              2）左孩子做完各种操作返回到当前节点的
 *                                                                              3）右孩子操作完回到当前节点
 * 2.带有parent的指针的节点的后继节点。中序遍历的某个节点的下一个节点，前驱就是中序遍历的上一个节点   #####################（比较难！需要再次理解）##################
 *          1）找到顶部节点，中序遍历后找到相应的下一个节点。
 *          2）后个节点的后继节点结论：1.只要该节点有右子树，则后继节点就是该右子树的最左子树的而节点。
 *                                   2.该节点没有右子树，该节点为X,其父节点为p。判断X是否为p的左孩子：1）是则p为后继节点2）不是，则X = p,p = x.parent继续上找。
 * 3.二叉树的序列化和反序列化：用特殊字符代表null:例子（1_2_3_#_#_）,怎么序列化就怎么反序列化
 * 4.判断一个树是否是平衡二叉树  *二叉树的问题，用递归很好解决   词条：树形DP，在二叉树上做动态规划
 * 5.搜索二叉树：任何一个节点，左子树比他小，右子树比他大。1）中序遍历是一次升序的，就是搜索二叉树
 * 6.完全二叉树：判断条件（按层遍历）：1）一个节点有右孩子，没有左孩子，则不是完全二叉树，返回fale
 *                                   2)当出现一个节点有左孩子，没右孩子或者左右都没有孩子之后。以后的节点必须都是叶子节点。
 *                                   *通过队列，放入头节点，判断队列是否为空进行按层遍历，在循环中，当左孩子不为空，进入队列，右孩子不为空进入队列。
 * 7.计算完全二叉树节点的个数：*深度为l的满二叉树的节点个数为2^l-1
 *                             通过左满还是右满。通过结论递归计算节点个数。
 */
public class Class5 {
    public static void main(String[] args){
//        Tree tree6 = new Tree(7,null,null);
//        Tree tree5 = new Tree(6,null,null);
        Tree tree4 = new Tree(5,null,null);
        Tree tree3 = new Tree(4,null,null);
        Tree tree2 = new Tree(3,null,null);
        Tree tree1 = new Tree(2,tree3,tree4);
        Tree tree = new Tree(1,tree1,tree2);
        //前序遍历
//        preTraverse(tree);
        //中序遍历
//        inorderTraversal(tree);
        //后序遍历
//        lastTraversal(tree);
        //判断一个数是否是平衡树
        System.out.print(judgeBalanceTree(tree));

    }

    /**
     * 判断一个树是否是平衡树
     * @param tree
     * @return
     */
    public static boolean judgeBalanceTree(Tree tree){
        int leftLength = treeLength(tree.left)+1;
        int rightLenght = treeLength(tree.right)+1;
        System.out.print(leftLength+":"+rightLenght);
        if(Math.abs(leftLength-rightLenght)>1){
            return false;
        }
        return true;
    }
    public static int treeLength(Tree tree){
        if(tree == null){
            return 0;
        }
        int leftLength = treeLength(tree.left)+1;
        int rightLenght = treeLength(tree.right)+1;
        return  leftLength>rightLenght?leftLength:rightLenght;
    }

    /**
     *前序遍历，右面先入栈，左面后入栈，出栈的时候打印  第一次进入的时候打印
     * @param tree
     */
    public static void preTraverse(Tree tree){
        Stack<Tree> stack = new Stack<>();
        stack.push(tree);
        while(!stack.isEmpty()){
            Tree now = stack.pop();
            System.out.println(now.value);
            if(now.right!=null){
                stack.push(now.right);
            }
            if(now.left!=null){
                stack.push(now.left);
            }
        }
    }

    /**
     * 中序遍历一直入栈直到找到最左面的数，当左面为空，出栈打印，并将出栈数右节点赋值给当前节点。  从左孩子回到当前节点的时候打印
     * @param tree
     */
    public static void inorderTraversal(Tree tree){
        Stack<Tree> stack = new Stack<>();
        while(!stack.isEmpty()||tree!=null){
            if(tree!=null){
                stack.push(tree);
                tree = tree.left;
            }else{
                Tree pop = stack.pop();
                System.out.println(pop.value);
                tree = pop.right;
            }
        }
    }

    /**
     * 后序遍历，先进stack1的栈最后打印，而后序遍历最先进stack1的最后打印。所以先将中间的数入栈。然后将左孩子压入stack栈，再将stack2压入stack栈
     *
     *  从右孩子回到当前节点的时候打印
     *
     * 先左再右再中
     * @param tree
     */
    public static void lastTraversal(Tree tree){
        Stack<Tree> stack = new Stack<>();//缓存栈
        Stack<Tree> stack1 = new Stack<>();//进行后续遍历打印的栈
        stack.push(tree);
        while(!stack.isEmpty()){
            Tree pre = stack.pop();
            stack1.push(pre);
            if(pre.left!=null){
                stack.push(pre.left);
            }
            if(pre.right!=null){
                stack.push(pre.right);
            }
        }
        while(!stack1.isEmpty()){
            System.out.println(stack1.pop().value);
        }

    }
    static class Tree{
        int value;
        Tree left;
        Tree right;
        public Tree(int value,Tree left,Tree right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
