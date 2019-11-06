package com.example.lzl.java.jinjieniuke;

import java.util.HashMap;

/**
 * 1.次数的双向链表
 */
public class LFU {
    public class Node{
        public Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;
        public Node(Integer key,Integer value,Integer times){
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }
    public class LFUCache{
        /**
         * 传入的node都是确定存在的
         */
        public class NodeList{
            public Node head;
            public Node tail;
            public NodeList last;
            public NodeList next;
            public NodeList(Node node){
                this.head = node;
                this.tail = node;
            }
            public void addNodeFromHead(Node node){
                node.down = head;
                head.up = node;
                head = node;
            }
            public boolean isEmpty(){
                return head ==null;
            }
            public void deleteNode(Node node){
                if(head == tail){
                    head =null;
                    tail = null;
                }else{
                    if(head == node){
                        head = head.down;
                        head.up.down = null;
                        head.up = null;
                    }else if(tail == node){
                        tail = tail.up;
                        tail.down.up = null;
                        tail.down = null;
                    }else{
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
                node.up = null;
                node.down = null;
            }
        }
        private int capacity;
        private int size;
        private HashMap<Integer,Node> records;//通过key也就是数值找到对应的node
        private HashMap<Node,NodeList> heads;//通过node找到nodelist
        private NodeList headList;//用于方便删除，记录的链表头节点。
        public LFUCache(int capacity){
            this.capacity = capacity;
            records = new HashMap<>();
            heads = new HashMap<>();
            headList = null;
            size = 0;
        }
        public void set(int key,int value){
            if(records.containsKey(key)){
                Node node = records.get(key);
                //修改node里的数据和值
                node.times++;
                node.value = value;
                NodeList curNodeList = heads.get(node);
                move(node,curNodeList);
            }else{
                //添加
                //当已经满了，先删除第一个
                if(capacity == size){
                    Node deleteNode =headList.tail;
                    headList.deleteNode(deleteNode);
                    records.remove(deleteNode.key);
                    heads.remove(deleteNode);
                    modifyNodeList(headList);
                    size--;
                }
                //添加数据
                Node node = new Node(key,value,1);
                if(headList == null){
                    headList = new NodeList(node);
                }else{
                    if(headList.head.times.equals(node.times)){
                        headList.addNodeFromHead(node);
                    }else{
                        NodeList nodeList = new NodeList(node);
                        nodeList.next = headList;
                        headList.last = nodeList;
                        headList = nodeList;
                    }
                }
                records.put(node.key,node);
                //新添加的数据肯定是在headlist上
                heads.put(node,headList);
                size++;
            }
        }

        /**
         * 对nodelist进行删除，整理操作，返回值该nodelist是否还存在
         * @param headList
         */
        private boolean modifyNodeList(NodeList headList) {
            if(headList.isEmpty()){
                if(headList == this.headList){
                    headList = headList.next;
                    if(headList != null){
                        headList.last = null;
                    }
                }else{
                    headList.last.next = headList.next;
                    if(headList.next!=null){
                        headList.next.last = headList.last;
                    }
                }
                return true;
            }
            return false;
        }

        /**
         * 将node移除当前list,移入下一个list。
         * @param node
         * @param curNodeList
         */
        private void move(Node node, NodeList curNodeList) {
            //1.从当前nodelist中删除改该node
            curNodeList.deleteNode(node);
            //2.因为删除了该节点当前nodelist可能要删除。所以
            NodeList preNodeList = modifyNodeList(curNodeList)?curNodeList.last:curNodeList;
            NodeList nextList = curNodeList.next;
            if(nextList == null){
                nextList = new NodeList(node);
                if(preNodeList!=null){
                    preNodeList.next = nextList;
                }
                nextList.last = preNodeList;
                if(headList==null){
                    headList = nextList;
                }
                heads.put(node,nextList);
            }else{
                if(nextList.head.times.equals(node.times)){
                    nextList.addNodeFromHead(node);
                    heads.put(node,nextList);
                }else{
                    NodeList newNodeList = new NodeList(node);
                    if(preNodeList!=null){
                        preNodeList.next = newNodeList;
                    }
                    newNodeList.last = preNodeList;
                    newNodeList.next = nextList;
                    nextList.last = newNodeList;
                    if(headList == null){
                        headList = newNodeList;
                    }
                    heads.put(node,newNodeList);
                }
            }
        }

        public void move(){

        }
        public int get(int key){
            if(!records.containsKey(key)){
                return -1;
            }
            Node node = records.get(key);
            node.times++;
            NodeList nodeList = heads.get(node);
            move(node,nodeList);
            return node.value;
        }

    }
}
