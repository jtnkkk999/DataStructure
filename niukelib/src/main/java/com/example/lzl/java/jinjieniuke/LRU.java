package com.example.lzl.java.jinjieniuke;

import java.util.HashMap;

/**
 * 双向链表和HashMap结构
 */
public class LRU {
    private MyCache<Integer,Integer> cache;
    public LRU(int capacity) {
        cache = new MyCache<>(capacity);
    }

    public int get(int key) {
        //这块返回的是integer会有null的情况所以要判断
        Integer res = cache.get(key);
        if(res == null){
            return -1;
        }
        return res;
    }

    public void put(int key, int value) {
        cache.put(key,value);
    }


    //1.双向链表数据节点结构
    public static class Node<K,T>{
        public K key;
        public T value;
        public Node<K,T> next;
        public Node<K,T> last;
        public Node(K key,T value){
            this.key = key;
            this.value = value;
        }
    }
    //2.设计双端链表数据结构，带有添加等操作
    public static class NodeDoubleLinkedList<K,T>{
        public Node<K,T> head;//第一个节点
        public Node<K,T> tail;//最后一个节点
        public NodeDoubleLinkedList(){
            this.head =null;
            this.tail = null;
        }
        public void addNode(Node<K,T> node){
            if(node == null){
                return;
            }
            if(this.head == null){
                head = node;
                tail = node;
            }else{
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }
        public void moveNodeToTail(Node<K,T> node){
            //1.当node为尾节点
            if(tail == node){
                return;
            }
            //2.当node为头节点。
            if(node == head){
                head = head.next;
                head.last = null;
            }else{
                //3.当node在中间的时候
                node.last.next = node.next;
                node.next.last = node.last;
            }
            //将node节点接到最后
            tail.next = node;
            node.last = tail;
            tail = node;
            node.next = null;
        }
        public Node<K,T> removeHead(){
            if(head ==null){
                return null;
            }
            Node<K,T> pre = head;
            if(head == tail){
                head =null;
                tail = null;
            }else{
                head = head.next;
                head.last = null;
                pre.next = null;
            }
            return pre;
        }
    }
    public static class MyCache<K,V>{
        private HashMap<K,Node<K,V>> map;
        private NodeDoubleLinkedList<K,V> nodeList;
        private int capacity;
        public MyCache(int capacity){
            map = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
            this.capacity = capacity;
        }
        public V get(K key){
            if(map.containsKey(key)){
                Node<K,V> res = map.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }
        public void put(K key,V value){
            if(map.containsKey(key)){
                Node<K,V> node = map.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            }else{
                Node<K,V> node = new Node<>(key,value);
                map.put(key,node);
                nodeList.addNode(node);
                if(map.size() == capacity){
                    Node<K,V> removeNode = nodeList.removeHead();
                    map.remove(removeNode.key);
                }
            }

        }
    }

}
