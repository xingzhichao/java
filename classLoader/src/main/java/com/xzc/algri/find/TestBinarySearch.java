package com.xzc.algri.find;

/**
 * @ClassName TestBinarySearch
 * @Description 折半查找
 * @Author zhichao.xing
 * @Date 2021/7/10 16:50
 * @Version 1.0
 **/
public class TestBinarySearch {
    /**
     * @return
     * @Description 二分查找 Java 实现
     * 时间复杂度
     * <p>
     * 比如：总共有n个元素，每次查找的区间大小就是n，n/2，n/4，…，n/2^k（接下来操作元素的剩余个数），其中k就是循环的次数。
     * 由于n/2^k取整后>=1，即令n/2^k=1，
     * 可得k=log2n,（是以2为底，n的对数），所以时间复杂度可以表示O(log n)
     * @Author xzc
     * @Date 16:51 2021/7/10
     **/
    public static int binarySearch(Integer[] srcArray, int des) {
        int low = 0;
        int high = srcArray.length - 1;

        while ((low <= high)
                && (low <= srcArray.length - 1)
                && (high <= srcArray.length - 1)) {
            int middle = (high + low) >> 1;
            if (des == srcArray[middle]) {
                return middle;
            } else if (des < srcArray[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
