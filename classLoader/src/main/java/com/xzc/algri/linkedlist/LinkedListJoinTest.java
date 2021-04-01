package com.xzc.algri.linkedlist;

/**
 * 一个比较经典的问题
 *
 * <p>判断两个链表是否相交，如果相交找出他们的交点。
 *
 * <p>（注意两个单链表相交不会出现X型交叉——单链表，每个节点只有一个指针域）。 是Y型
 *
 * <p>首先判断两个链表本身是否有环：
 *
 * <p>https://blog.csdn.net/u011635492/article/details/80784894
 */
public class LinkedListJoinTest {

  /**
   * 判断是否存在环
   *
   * <p>步骤：设置两个指针同时指向head，其中一个一次前进一个节点（P1），另外一个一次前进两个节点(P2)。
   * p1和p2同时走，如果其中一个遇到null，则说明没有环，如果走了N步之后，二者指向地址相同，那么说明链表存在环。
   *
   * <p>return false : 无环 true ： 有环
   */
  public static boolean isLoop(DataNode h) {
    DataNode p1 = h;
    DataNode p2 = h;
    while (p2.getNext() != null && p2.getNext().getNext() != null) {
      p1 = p1.getNext();
      p2 = p2.getNext().getNext();
      if (p1 == p2) break;
    }
    return !(p1 == null || p2 == null);
  }

  /**
   * 判断是否相交
   *
   * <p>采用最简单直接的方法，遍历两个链表，判断第一个链表的每个结点是否在第二个链表中，时间复杂度为O(len1*len2)，耗时很大；
   *    顺序查询到第一个在第二个链表种的节点即是两个链表的交点。
   *
   * <p>最环情况下，判断两个链表是否相交，只需要遍历链表，判断尾节点是否相等即可。
   */
  public static boolean isJoinNoLoop(DataNode h1, DataNode h2) {
    DataNode p1 = h1;
    DataNode p2 = h2;
    while (null != p1.getNext()) p1 = p1.getNext();
    while (null != p2.getNext()) p2 = p2.getNext();
    return p1 == p2;
  }


    /**
     * 最环情况下找到第一个相交点
     * 方法： 算出两个链表的长度差为x,长链表先移动x步，之后两链表同时移动，直到相遇的第一个交点。
     */
    public static DataNode getFirstJoinNode(DataNode h1,DataNode h2) {
        int length1 = 0;
        int length2 = 0;
        while(null != h1.getNext()) {
            length1 ++;
            h1 = h1.getNext();
        }
        while(null != h2.getNext()) {
            length1 ++;
            h2 = h2.getNext();
        }
        return length1>=length2?getNode(h1,length1,h2,length2):getNode(h2,length2,h1,length1);
    }


    private static  DataNode getNode(DataNode h1,int length1,DataNode h2,int length2){
        return new DataNode();
    }
}
