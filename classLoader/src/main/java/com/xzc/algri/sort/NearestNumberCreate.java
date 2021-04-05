package com.xzc.algri.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 原文：https://mp.weixin.qq.com/s/_mIeGKdvTOH-1jleJ4aADg
 */
public class NearestNumberCreate {

    @Test
    public void test() {
//        int[] nums = {1, 5, 3, 2, 4};
        int[] nums = {1, 2,3,5,4};
        int[] nearestNumber = findNearestNumber(nums);
        System.out.println(Arrays.toString(nearestNumber));
    }

    static int[] findNearestNumber(int[] numbers) {
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        //找到逆序区域前一位
        int index = findTransferPoint(numbersCopy);
        //如果数字置换边界为0，说明整个数组已经逆序，无法得到更大的相同数
        if (index == 0) {
            return null;
        }
        //把逆序区域的前一位和刚大于它的数字进行交换
        exchangeHead(numbersCopy, index);
        //把原来的逆序改成顺序
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    private static int[] reverse(int[] numbers, int index) {
        for (int i = index + 1; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                int temp = numbers[i + 1];
                numbers[i + 1] = numbers[i];
                numbers[i] = temp;
            }
        }
        return numbers;
    }

    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

}
