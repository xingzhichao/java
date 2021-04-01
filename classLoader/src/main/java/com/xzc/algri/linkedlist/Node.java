package com.xzc.algri.linkedlist;

import lombok.Data;

/**
 * 链表节点
 */
@Data
public class Node {
    private int data; // 数据域
    private Node next;// 指针域

    public Node() {
    }

    public Node(int data) {
        // super();
        this.data = data;
    }
    
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
