package com.example.lzl.java.niukelib.prototype;

public class LinkedList {
    /**
     * 链表相关的问题:
     *
     * 1.链表的倒叙
     * 2。链式基数排序，也就是桶排序：麻将先排序数字再排序花色，稳定的排序。
     *    使用场景，数据量几十个，插入操作多的情况（数量少，但插入操作多，譬如麻将，扑克牌的整理操作）
     */
    public static class Node{
        int value;
        Node next;
        public Node(int value,Node next){
            this.value = value;
            this.next = next;
        }
    }
    public static void main(String[] args){
        Node node = new Node(1,new Node(2,new Node(3,new Node(4,new Node(5,null)))));
        node = reverseNode(node);
        while (node!=null){
            System.out.println(node.value);
            node = node.next;
        }
    }

    /**
     * 链表的倒叙
     * @param node
     * @return
     */
    public static Node reverseNode(Node node){
        Node pre = null;
        Node next;
        while(node!=null){
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
