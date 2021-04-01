package com.xzc.algri.bloom;

import com.xzc.algri.MurmurHash;
import com.xzc.algri.sort.SortUtils;

import java.util.BitSet;
import java.util.Random;


public class BloomFilter {

	private final BitSet bs;

	final int[] hashSeeds;

	final int capacity;

	public BloomFilter(int slots, int hashFunctions) {
		bs = new BitSet(slots);
		Random r = new Random(System.currentTimeMillis());
		hashSeeds = new int[hashFunctions];
		for (int i = 0; i < hashFunctions; ++i) {
			hashSeeds[i] = r.nextInt();
		}
		capacity = slots;
	}

	/**
	 * 公式 计算hash函数个数
	 * @param n
	 * @param m
	 * @return
	 */
	static int optimalNumOfHashFunctions(long n, long m) {
		// (m / n) * log(2), but avoid truncation due to division!
		return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
	}

	/**
	 * 公式  计算bit数组大小
	 * @param n
	 * @param p
	 * @return
	 */
	static int optimalNumOfBits(long n, double p) {
		if (p == 0) {
			p = Double.MIN_VALUE;
		}
		return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
	}

    //3 hash, 这3个hash值对应位置都应该设置为1
	public void add(int value) {
		byte[] b = new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
		for (int i = 0; i < hashSeeds.length; ++i) {
			int h = MurmurHash.hash32(b, 4, hashSeeds[i]);
			bs.set(Math.abs(h) % capacity, true);
		}
	}

	public void clear() {
		bs.clear();
	}

	public boolean mightContain(int value) {
		byte[] b = new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
		for (int i = 0; i < hashSeeds.length; ++i) {
			int h = MurmurHash.hash32(b, 4, hashSeeds[i]);

			if (!bs.get(Math.abs(h) % capacity)) {
				return false;
			}
		}

		return true;
	}

 
	static <T> BloomFilter create(long expectedInsertions, double fpp) {
 

		if (expectedInsertions == 0) {
			expectedInsertions = 1;
		}

		int numBits = optimalNumOfBits(expectedInsertions, fpp);
		int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
		try {
			return new BloomFilter(numBits, numHashFunctions);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Could not create FastBloomFilter of " + numBits + " bits", e);
		}
	}

	public static void main(String[] args) {
		BloomFilter bf =  BloomFilter.create(100000000, 0.01);
 
		int[] arrayToSort = SortUtils.buildRandomIntArray(10000000);
		
		BitSet bitSet = new BitSet(100000000);
		
		for(int elem: arrayToSort) {
			bitSet.set(elem);
			bf.add(elem);
		}
		
		Random generator = new Random();
		for(int i=0; i<10000000; i++) {
			int rand = generator.nextInt(100000000);
		    if(bitSet.get(rand)) {
		    	//System.out.println("Number: " +rand + " is in array ...");
		    }else {
		    	if(bf.mightContain(rand)) {
			    	System.out.println("Number: " +rand + " is not in array ..., bloom filter error");
		    	}
		    }
		}
	}
}
