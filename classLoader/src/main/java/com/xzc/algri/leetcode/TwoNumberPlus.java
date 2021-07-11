package com.xzc.algri.leetcode;

public class TwoNumberPlus {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode returnNode = new ListNode();
        createNext(returnNode, l1, l2, 0);
        return returnNode;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9, null);
        ListNode listNode2 = new ListNode(9, listNode1);
        ListNode listNode3 = new ListNode(9, listNode2);

        ListNode listNode1111 = new ListNode(9, null);
        ListNode listNode111 = new ListNode(9, listNode1111);
        ListNode listNode11 = new ListNode(9, listNode111);
        ListNode listNode21 = new ListNode(9, listNode11);
        ListNode listNode31 = new ListNode(9, listNode21);
        ListNode listNode = addTwoNumbers(listNode3, listNode31);
        System.out.println(listNode.toString());
    }

    public static ListNode createNext(ListNode returnNode, ListNode l1, ListNode l2, int flag) {
        returnNode.val = (l1.val + l2.val + flag) % 10;
        if ((l1.val + l2.val + flag) / 10 >= 1) {
            flag = 1;
        } else {
            flag = 0;
        }
        l1 = l1.next;
        l2 = l2.next;
        if (l1 == null || l2 == null) {
            if (l1 == null && l2 == null && flag == 0) {
                return returnNode;
            } else {
                l1 = l1 == null ? new ListNode(0) : l1;
                l2 = l2 == null ? new ListNode(0) : l2;
            }
        }
        ListNode nextNode = new ListNode();
        ListNode next = createNext(nextNode, l1, l2, flag);
        returnNode.next = next;
        //返回的是链上的对象
        return returnNode;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
