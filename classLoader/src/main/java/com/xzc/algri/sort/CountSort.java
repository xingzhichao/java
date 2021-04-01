package com.xzc.algri.sort;

/**
 * 计数排序
 *
 * - 优化后  稳定排序
 */
public class CountSort {

    /**
     * @param array - 待排序数组
     * @param k     - 数组中最大值
     * @return
     */
    private static int[] countSort(int[] array, int k) {
        //统计数组C
        int[] C = new int[k + 1];
        //获取A数组大小用于构造B数组
        int length = array.length, sum = 0;
        //返回的目标数组
        int[] B = new int[length];//构造B数组

        for (int i = 0; i < length; i++) {
            // 统计A中各元素个数，存入C数组
            C[array[i]] += 1;
        }//c构成之后 ，其实可以直接遍历c 输出，但是不是稳定的

        //数组c 做变种，值存储的是 相应正数的最终排序位置（最大排序位置）
        for (int i = 0; i < k + 1; i++) {
            sum += C[i];
            C[i] = sum;
        }
        //遍历A数组，构造B数组
        for (int i = length - 1; i >= 0; i--) {
            //将A中该元素放到排序后数组B中指定的位置
            B[C[array[i]] - 1] = array[i];
            //将C中该元素-1，方便存放下一个同样大小的元素
            C[array[i]]--;
        }
        return B;//将排序好的数组返回，完成排序
    }

    public static void main(String[] args) {
        int[] A = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        int[] B = countSort(A, 5);
        for (int i = 0; i < A.length; i++) {
            System.out.println((i + 1) + "th:" + B[i]);
        }
    }
}
