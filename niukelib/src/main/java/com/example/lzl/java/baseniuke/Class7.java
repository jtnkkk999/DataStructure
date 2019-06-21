package com.example.lzl.java.baseniuke;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *  并查集：解决的问题：1）查两个元素是否属于一个集合（快）isSameSet(A,B)
 *                     2）两个集合合并在一起(两个元素所在的集合合并在一起)
 *        局限性：1）初始化给所有的元素，不能动态添加
 *        *这是一种数据结构
 *  岛问题：感染函数
 *
 *  前缀数：将数加到路程线段上 附加功能
 *      1）一个字符串是否加入过。
 *      2）有多少个以字符串前缀开头的数。
 *
 *  贪心：多个字符串拼接，字典序排序
 *      1）贪心策略1，多个字符串排序，按从小到大拼接。   b 和 ba  -> b<ba    但bba>bab 所以不成立
 *      2）贪心策略2，st1+st2<st2+st1  st1<st2
 *      *排序策略的问题
 *
 */
public class Class7 {

    public static void main(String[] args){
        //岛问题
//        int[][] array= {
//                {0,0,1,0,1,0},
//                {1,1,1,0,1,0},
//                {1,0,0,1,0,0},
//                {0,0,0,0,0,0}};
//        System.out.print(countIsLand(array));
        //贪心问题

    }
    //    贪心算法 comparator比较接口，用来写比较器用的。comper  comparater 排序接口，实现该接口的类，可以直接通过sort排序 comperTo
    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String s, String t1) {
            return (s+t1).compareTo(t1+s);
        }
    }

    public static String lowersString(String[] strs){
        if(strs == null||strs.length==0){
            return "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res+=strs[i];
        }
        return res;
    }

    //========================================前缀数，包含上述两个功能
    public static class TrieNode{
        public int path;//有多少个以这个节点开头的数
        public int end;//有多少个以这个节点结尾的数
        public TrieNode[] nexts;//可通过实际需要改变结构
        public TrieNode(){
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String world){
            if(world == null){
                return;
            }
            TrieNode node = root;
            char[] charArrays = world.toCharArray();
            for(int i = 0;i<charArrays.length;i++){
                int index = charArrays[i]-'a';
                if(node.nexts[index]==null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[i];
                node.path++;
            }
            node.end++;
        }

        /**
         * 查找出现过几次某个单词
         * @param world
         * @return
         */
        public int search(String world){
            if(world == null){
                return 0;
            }
            TrieNode node = root;
            char[] charArrays = world.toCharArray();
            for (int i = 0; i < charArrays.length; i++) {
                int index = charArrays[i]-'a';
                if(node.nexts[index] == null){
                    //没有这个单词
                   return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        /**
         * 删除一个单词
         * @param world
         */
        public void delete(String world){
            if(search(world)!=0){
                TrieNode node = root;
                char[] charArrays = world.toCharArray();
                for (int i = 0; i < charArrays.length; i++) {
                    int index = charArrays[i] - 'a';
                    node.path--;
                    node.end--;
                    if(--node.nexts[index].path == 0){
                        node.nexts[index] =null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }
        public int searchPreFixNumber(String pre){
            if(pre == null){
                return 0;
            }
            TrieNode node = root;
            char[] charArray = pre.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                int index = charArray[i]- 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }


    }


    //========================================岛问题
    //1.常规解法，通过感染函数
    public static int countIsLand(int[][] m){
        if(m==null&&m[0]==null){
            return 0;
        }
        //*******************宽高的设置位置很重要*******************
        int w = m[0].length;
        int h = m.length;
        int count = 0;
        for(int i = 0;i<h;i++){
            for(int j =0;j<w;j++){
                if(m[i][j] == 1){
                    //感染
                    infect(m,i,j,h,w);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 感染函数
     * @param m
     * @param i
     * @param j
     * @param w
     * @param h
     */
    private static void infect(int[][] m, int i, int j, int w, int h) {
        if(i<0||i>=w||j<0||j>=h||m[i][j]!=1){
            return;
        }
        m[i][j] = 2;
        infect(m,i,j-1,w,h);//上
        infect(m,i+1,j,w,h);//右
        infect(m,i,j+1,w,h);//下
        infect(m,i-1,j,w,h);//左
    }


    //并查集结构原型
    public static class Node{

    }
    public static class UnionFindSet{
        public HashMap<Node,Node> fatherMap;//key是孩子，value是父亲
        public HashMap<Node,Integer> sizeMap;//某一个节点他所在的集合有多少节点
        public UnionFindSet(List<Node> nodes){
            //初始化应该把所有的样本都给你 nodes
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            makeSets(nodes);
        }

        /**
         * 初始化刚传入的所有数据
         * @param nodes
         */
        private void makeSets(List<Node> nodes){
            for(Node node : nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        /**
         * 找到一个节点的父亲节点，并将过程中的节点连接到最顶端的节点上
         * @param node
         * @return
         */
        private Node findHead(Node node){
            Node father = fatherMap.get(node);
            if(father!=node){
                father = findHead(father);//递归方式调用。会将node节点以上的所有节点都连接在最顶头的节点上面
            }
            fatherMap.put(node,father);
            return father;
        }

        public boolean isSameSet(Node a,Node b){
            return findHead(a)==findHead(b);
        }
        public void union(Node a,Node b){
            if(a == null||b==null){
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if(aHead!=bHead){
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                if(aSize<=bSize){
                    fatherMap.put(aHead,bHead);
                    sizeMap.put(bHead,aSize+bSize);
                }else{
                    fatherMap.put(bHead,aHead);
                    sizeMap.put(aHead,aSize+bSize);
                }
            }
        }


    }
}
