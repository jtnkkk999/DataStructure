package com.example.lzl.java.niukelib.binarytree;

public class TreeTest {
    public static void main(String[] args) {
        SearchBinaryTree tree = new SearchBinaryTree();
        tree.put(5);
        tree.put(9);
        tree.put(3);
        tree.put(1);
        tree.put(7);
        tree.put(4);
        tree.put(2);
        tree.put(6);
        tree.delect(5);
        tree.midOrderTraverse();
    }
}
