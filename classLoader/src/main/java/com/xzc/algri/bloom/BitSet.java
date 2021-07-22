package com.xzc.algri.bloom;

import com.xzc.algri.sort.SortUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * java中有三种移位运算符
 * <p>
 * <<      :     左移运算符，num << 1,相当于num乘以2
 * <p>
 * >>      :     右移运算符，num >> 1,相当于num除以2
 * <p>
 * >>>    :     无符号右移，忽略符号位，空位都以0补齐
 * <p>
 * 正数无符号右移
 * 无符号右移运算符和右移运算符的主要区别在于负数的计算，因为无符号右移是高位补0，移多少位补多少个0。
 * <p>
 * 负数无符号右移
 * -6的二进制是6的二进制取反再加1,
 * 6的二进制也就是0000 0000 0000 0000 0000 0000 0000 0110，
 * 取反后加1为1111 1111 1111 1111 1111 1111 1111 1010，
 * 右移三位0001 1111 1111 1111 1111 1111 1111 1111
 *
 * @Author xzc
 * @Date 11:10 2021/4/26
 * @return
 **/
public class BitSet implements Cloneable, java.io.Serializable {

    private static final long serialVersionUID = 1L;
    final static int ADDRESS_BITS_PER_WORD = 6; //2^6=64，程序中出现的 >>6
    private final static int BITS_PER_WORD = 64;
    private final static int BIT_INDEX_MASK = 63;
    private static final long WORD_MASK = 0xffffffffffffffffL;
    private long[] words; //64*21亿
    private transient int wordsInUse = 0; //开了几个long型数组

    //默认是64位
    public BitSet() {
        initWords(64);
    }

    //指定多少位
    public BitSet(int nbits) {
        if (nbits < 0)
            throw new NegativeArraySizeException("nbits < 0: " + nbits);
        initWords(nbits);
    }

    private void initWords(int nbits) {
        //初始化多少个long型数组才能存下？除以64(>>6) 然后+1；
        //long是8个byte, 64bit
        //8, 1 long
        //100, 2 long
        words = new long[((nbits - 1) >> 6) + 1];
    }

    public void set(int bitIndex) {

        int wordIndex = (bitIndex >> 6); //除以64定位到words数组里面某个long元素； 50->0,  100->1
        words[wordIndex] |= (1L << bitIndex); // bitIndex是1，其他位都是0
    }

    public boolean get(int bitIndex) {

        int wordIndex = (bitIndex >> 6);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }


    //
    private static int wordIndex(int bitIndex) {
        return bitIndex >> ADDRESS_BITS_PER_WORD;
    }


    public void clear(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        int wordIndex = wordIndex(bitIndex);
        if (wordIndex >= wordsInUse)
            return;

        words[wordIndex] &= ~(1L << bitIndex);

        recalculateWordsInUse();
        checkInvariants();
    }


    private void checkInvariants() {
        assert (wordsInUse == 0 || words[wordsInUse - 1] != 0);
        assert (wordsInUse >= 0 && wordsInUse <= words.length);
        assert (wordsInUse == words.length || words[wordsInUse] == 0);
    }


    private void recalculateWordsInUse() {
        // Traverse the bitset until a used word is found
        int i;
        for (i = wordsInUse - 1; i >= 0; i--)
            if (words[i] != 0)
                break;

        wordsInUse = i + 1; // The new logical size
    }


    private void ensureCapacity(int wordsRequired) {
        if (words.length < wordsRequired) {
            // Allocate larger of doubled size or required size
            int request = Math.max(2 * words.length, wordsRequired);
            words = Arrays.copyOf(words, request);
        }
    }

    private void expandTo(int wordIndex) {
        int wordsRequired = wordIndex + 1;
        if (wordsInUse < wordsRequired) {
            ensureCapacity(wordsRequired);
            wordsInUse = wordsRequired;
        }
    }

    private static void checkRange(int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex < 0: " + fromIndex);
        if (toIndex < 0)
            throw new IndexOutOfBoundsException("toIndex < 0: " + toIndex);
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex +
                    " > toIndex: " + toIndex);
    }

    public void flip(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        int wordIndex = wordIndex(bitIndex);
        expandTo(wordIndex);

        words[wordIndex] ^= (1L << bitIndex);

        recalculateWordsInUse();
        checkInvariants();
    }

    public void flip(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);

        if (fromIndex == toIndex)
            return;

        int startWordIndex = wordIndex(fromIndex);
        int endWordIndex = wordIndex(toIndex - 1);
        expandTo(endWordIndex);

        long firstWordMask = WORD_MASK << fromIndex;
        long lastWordMask = WORD_MASK >>> -toIndex;
        if (startWordIndex == endWordIndex) {
            // Case 1: One word
            words[startWordIndex] ^= (firstWordMask & lastWordMask);
        } else {
            // Case 2: Multiple words
            // Handle first word
            words[startWordIndex] ^= firstWordMask;

            // Handle intermediate words, if any
            for (int i = startWordIndex + 1; i < endWordIndex; i++)
                words[i] ^= WORD_MASK;

            // Handle last word
            words[endWordIndex] ^= lastWordMask;
        }

        recalculateWordsInUse();
        checkInvariants();
    }

    public void clear() {
        while (wordsInUse > 0)
            words[--wordsInUse] = 0;
    }

    public boolean isEmpty() {
        return wordsInUse == 0;
    }

    //多少位在用
    public int cardinality() {
        int sum = 0;
        for (int i = 0; i < wordsInUse; i++)
            sum += Long.bitCount(words[i]);
        return sum;
    }

    public void and(BitSet set) {
        if (this == set)
            return;

        while (wordsInUse > set.wordsInUse)
            words[--wordsInUse] = 0;

        // Perform logical AND on words in common
        for (int i = 0; i < wordsInUse; i++)
            words[i] &= set.words[i];

        recalculateWordsInUse();
        checkInvariants();
    }

    public void or(BitSet set) {
        if (this == set)
            return;

        int wordsInCommon = Math.min(wordsInUse, set.wordsInUse);

        if (wordsInUse < set.wordsInUse) {
            ensureCapacity(set.wordsInUse);
            wordsInUse = set.wordsInUse;
        }

        // Perform logical OR on words in common
        for (int i = 0; i < wordsInCommon; i++)
            words[i] |= set.words[i];

        // Copy any remaining words
        if (wordsInCommon < set.wordsInUse)
            System.arraycopy(set.words, wordsInCommon,
                    words, wordsInCommon,
                    wordsInUse - wordsInCommon);

        // recalculateWordsInUse() is unnecessary
        checkInvariants();
    }

    public int size() {
        return words.length * BITS_PER_WORD;
    }

    public void xor(BitSet set) {
        int wordsInCommon = Math.min(wordsInUse, set.wordsInUse);

        if (wordsInUse < set.wordsInUse) {
            ensureCapacity(set.wordsInUse);
            wordsInUse = set.wordsInUse;
        }

        // Perform logical XOR on words in common
        for (int i = 0; i < wordsInCommon; i++)
            words[i] ^= set.words[i];

        // Copy any remaining words
        if (wordsInCommon < set.wordsInUse)
            System.arraycopy(set.words, wordsInCommon,
                    words, wordsInCommon,
                    set.wordsInUse - wordsInCommon);

        recalculateWordsInUse();
        checkInvariants();
    }

    public static void main(String[] args) {
        /**
         * @Description 8+4+2+1
         **/
        System.out.println(15 >> 1);
        System.out.println(-15 >> 1);

//        System.out.println(15 >>> 1);
//        System.out.println("========");
//        System.out.println(16 >> 1);
//        System.out.println(16 >>> 1);
        System.out.println("---------------");
//        System.out.println(-15 >>> 1);
        System.out.println(-1 >>> 1);
//        int[] arrayToSort = SortUtils.buildRandomIntArray(10000000);
//
//        BitSet bitSet = new BitSet(100000000);
//
//        for (int elem : arrayToSort) {
//            bitSet.set(elem);
//        }
//
//        Random generator = new Random();
//        for (int i = 0; i < 100; i++) {
//            int rand = generator.nextInt(100000000);
//            if (bitSet.get(rand)) {
//                System.out.println("Number: " + rand + " is in array ...");
//            } else {
//                System.out.println("Number: " + rand + " is not in array ...");
//            }
//        }


    }


}