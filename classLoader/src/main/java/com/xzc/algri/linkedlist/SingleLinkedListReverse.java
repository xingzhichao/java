package com.xzc.algri.linkedlist;

import java.util.Stack;

/**
 * Java单链表反转
 */
public class SingleLinkedListReverse {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getData() + " ");
            h = h.getNext();
        }
        // 调用反转方法
        head = reverse(head);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
        System.out.println("");
        System.out.println("使用 stack 来实现反转 ");
        newf();
    }

    /**
     * 递归，在反转当前节点之前先反转后续节点
     */
    public static Node reverse(Node head) {
        // head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
        Node next = head.getNext();
        if (head == null || next == null) {
            return head;// 若为空链或者当前结点在尾结点，则直接还回
        }
        Node reHead = reverse(next);// 先反转后续节点head.getNext()
        next.setNext(head);// 将当前结点的指针域指向前一结点
        head.setNext(null);// 前一结点的指针域令为null;
        return reHead;// 反转后新链表的头结点
    }

    /**
     * 借助外部反转
     */
    public static void newf() {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        Stack<Node> stack = new Stack();
        stack.push(head);
        stack.push(node1);
        stack.push(node2);
        stack.push(node3);
        while (!stack.empty()) {
            Node pop = stack.pop();
            System.out.println(pop.getData());
        }
    }
}
