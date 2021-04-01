package com.xzc.algri.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 队列的特点是什么？
 * - 是先进先出（FIFO）
 * 优先队列
 * <p>
 * - 不再遵循先入先出的原则，而是分为两种情况：
 * <p>
 * 分类
 * i)最大优先队列，无论入队顺序，当前最大的元素优先出队。
 * ii)最小优先队列，无论入队顺序，当前最小的元素优先出队。
 *
 * 入队，出队 时间复杂度O(Log N)
 */
@Slf4j
public class PriorityQueue {

    private int[] array;
    private int size;

    public PriorityQueue() {
        //队列初始长度32
        array = new int[32];
    }

    /**
     * 入队
     * 采用数组来存储二叉堆的元素，
     * 因此当元素超过数组范围的时候，需要进行resize来扩大数组长度。
     *
     * @param key 入队元素
     */
    private void enQueue(int key) {
        //队列长度超出范围，扩容
        if (size >= array.length) {
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     */
    private int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("the queue is empty !");
        }
        //获取堆顶元素
        int head = array[0];
        //最后一个元素移动到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    /**
     * 上浮调整
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = childIndex / 2;
        // temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            //无需真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void downAdjust() {
        // temp保存父节点值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，直接跳出
            if (temp >= array[childIndex])
                break;
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void resize() {
        //队列容量翻倍
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    /**
     * 入口
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        log.info("出队元素：" + priorityQueue.deQueue());
        log.info("出队元素：" + priorityQueue.deQueue());
    }
}
