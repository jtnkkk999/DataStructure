package com.example.lzl.java.niukelib.binarytree;

import javax.swing.tree.TreeNode;

/**
 * 搜索二叉树即搜索树，查询树，排序树。
 * 特征：左孩子小于根节点，右孩子大于根节点。
 */
public class SearchBinaryTree {
    TreeNode root;
    /**
     * 传入一个数值返回一个节点
     * @param data
     * @return
     */
    public TreeNode put(int data){
        /**
         * 判空的处理
         */
        if(root == null){
            root = new TreeNode(data);
            return root;
        }

        TreeNode parent = null;//用来存储上一个节点。
        TreeNode temp = root;

        //查找到需要放入的位置
        while(temp!=null){
            parent = temp;
            if(temp.data>data){
                temp = temp.leftChild;
            }else if(temp.data<data){
                temp = temp.rightChild;
            }else{
                //相等则存在，直接返回不插入
                return temp;
            }
        }

        //生成节点并进行插入
        TreeNode newNode = new TreeNode(data);
        if(parent.data>data){
            parent.leftChild = newNode;
        }else{
            parent.rightChild = newNode;
        }
        newNode.parent = parent;
        return newNode;
    }

    /**
     * 查询操作
     * @param data
     * @return
     */
    public TreeNode search(int data){
        if(root == null){
            return null;
        }
        TreeNode temp = root;
        while(temp!=null){
            if(temp.data>data){
                temp = temp.leftChild;
            }else if(temp.data<data){
                temp = temp.rightChild;
            }else{
                return temp;
            }
        }
        return null;
    }

    /**
     * 删除操作：1)当删除的节点存在右子树，则用其右子树的最左处的节点替换（即后继节点）。
     *          2）否则，用删除节点的左子树的最右节点替换（即前驱节点）。
     *          3）否则，删除的是根节点
     *
     *          注意：找到替换节点（前驱或者后继）后，只需要将值传给删除节点，然后再删除掉替换的这个节点即可。
     *              删除替换节点时。要注意：1）替换结点是否存在左孩子或者右孩子。
     *                                     2）替换节点是在其父节点的左子树上还是右子树上。
     *                                     3）将1）中的孩子放在2）上。（每一步都需要判断）
     * @param data
     * @return
     */
    public void delect(int data){
        if(root == null){
            throw new NullPointerException("root is null");
        }
        //先找到要删除的节点
        TreeNode node = search(data);
        //不存在删除的节点
        if(node==null){
            return;
        }
        TreeNode right =node.rightChild;
        TreeNode left = node.leftChild;
        if(right!=null){
            //右孩子不等于空，找后继节点代替

            //找到右孩子最左面的孩子。（没有就是自己）
            while(right.leftChild!=null){
                right = right.leftChild;
            }
            //将后继节点的值赋值给需要删除的节点
            node.data = right.data;
            //此时划分的情况为两大种，四小种。画图自己分析
            if(right.rightChild!=null){
                //
                if( right.parent.leftChild == right){
                    right.parent.leftChild = right.rightChild;
                }else{
                    right.parent.rightChild = right.rightChild;
                }
                right.rightChild.parent = right.parent;
            }else{
                if( right.parent.leftChild == right){
                    right.parent.leftChild =null;
                }else {
                    right.parent.rightChild =null;
                }
                right.parent =null;

            }
        }else if(left!=null){
            //左孩子不等于空，找前驱节点代替
            while(left.rightChild!=null){
                left = left.rightChild;
            }
            node.data = left.data;
            if(left.leftChild!=null){
                if(left.parent.rightChild == left){
                    left.parent.rightChild = left.leftChild;
                }else{
                    left.parent.leftChild = left.leftChild;
                }

                left.leftChild.parent = left.parent;
            }else{
                if(left.parent.rightChild == left){
                    left.parent.rightChild = null;
                }else{
                    left.parent.leftChild = null;
                }
                left.parent = null;
            }
        }else{
            if(node == root){
                root =null;
                return;
            }
            if(node.parent.leftChild == node){
                node.parent.leftChild = null;
            }else{
                node.parent.rightChild = null;
            }
            node.parent = null;
        }
    }


    /**
     * 通过中序遍历检查插入是否正确，搜索二叉树的中序遍历是排好序的。
     *
     * 中序遍历的三种方式：1）递归 2）非递归 3）morris
     * 暂时实现递归版
     * @param root
     */
    private void midOrderTraverse(TreeNode root){
        if(root == null){
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.println(root.data);
        midOrderTraverse(root.rightChild);
    }

    /**
     * 暴漏给外部的查询接口
     */
    public void midOrderTraverse(){
        midOrderTraverse(root);
    }
    /**
     * 二叉搜索树所使用的节点
     */
    public static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;
        public TreeNode(int data){
            this.data = data;
        }
    }
}
