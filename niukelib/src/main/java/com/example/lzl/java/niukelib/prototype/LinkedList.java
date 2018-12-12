package com.example.lzl.java.niukelib.prototype;

public class LinkedList {
    /**
     * 链表相关的问题
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
        Node now = node;
        Node next;
        while(now.next!=null){
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        now.next = pre;
        return now;
    }
}
