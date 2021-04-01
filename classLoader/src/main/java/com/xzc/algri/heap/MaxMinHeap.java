package com.xzc.algri.heap;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.PriorityQueue;

/** */
@Slf4j
public class MaxMinHeap {

  /**
   * 用PriorityQueue实现最大最小堆
   *
   * <p>实际上是一个堆（不指定Comparator时默认为最小堆）， 通过传入自定义的Comparator函数可以实现大顶堆。
   *
   * @param args
   */
  public static void main(String[] args) {
    // 小顶堆，默认容量为11
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    // 大顶堆，容量11
    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<Integer>(11, Comparator.comparing(Integer::longValue).reversed());

    maxHeap.add(1);
    maxHeap.add(2);
    maxHeap.add(3);
    log.info(maxHeap.toString());
  }
}
