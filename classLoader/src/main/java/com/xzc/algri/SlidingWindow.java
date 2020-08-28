package com.xzc.algri;

/**
 * @ClassName SlidingWindow
 * @Description 经典滑动窗口题目
 * @Author zhichao.xing
 * @Date 2020/8/28 12:42
 * @Version 1.0
 **/
public class SlidingWindow {
    /**
     * 滑动窗口
     * 就像描述的那样，可以理解成是一个会滑动的窗口，每次记录下窗口的状态，再找出符合条件的适合的窗口
     * 可以使用滑动窗口来减少时间复杂度
     * 经典滑动窗口题目
     * 给一组大小为n的整数数组，计算长度为k的子数组的最大值
     * 比如：数组{1,2,3,4,5,7,6,1,8},k=2，那么最终结果应该是7+6=13最大。
     * 最简单的是使用两层遍历，通过所有情况找出最大的一个子数组，时间复杂度O(N^2)
     * 使用滑动窗口，从[0,k-1]的一个窗口，记录其总和，然后窗口向右移动到[1,k],再到[2,k+1]，直到数组的最尾端，找出里面总和最大的一个窗口，这样的解法就是滑动窗口算法。
     *
     * @return int
     * @Description
     * @Author xzc
     * @Date 12:43 2020/8/28
     **/
    public static int maxnum(int[] array, int k) {
        //如果k比数组长度还大，返回-1
        if (array.length < k) {
            return -1;
        }
        int left = 0;
        int sum = 0;
        //第一次，默认值 没有k
        for (int i = 0; i < k; i++) {
            sum += array[i];
        }
        //tempsum记录每个窗口的总和
        int tempsum = sum;
        while (left + k < array.length) {
            tempsum = tempsum - array[left] + array[left + k];
            left++;
            //sum取原sum和tempsum的较大值
            sum = Math.max(sum, tempsum);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 3;
        System.out.println(maxnum(arr, k));
    }
}
