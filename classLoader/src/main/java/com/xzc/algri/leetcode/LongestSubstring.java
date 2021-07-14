package com.xzc.algri.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 */
public class LongestSubstring {

    public static void main(String[] args) {
        LongestSubstring l = new LongestSubstring();
        int length = l.lengthOfLongestSubstring2("abcabcbb");
        System.out.println(length);
    }

    /**
     * 方法2 :
     * 优化后的
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.equals("")) {
            return 0;
        }
        int maxLength = 0;
        int left = 0;
        Map map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            boolean b = map.containsKey(s.charAt(i));
            if (b) {
                left = Math.max(left, (Integer) map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    /**
     * 方法1
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.equals("")) {
            return 0;
        }
        int size = 0;
        Set set = new HashSet();
        while (s.length() > 1) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int oldsize = set.size();
                set.add(c);
                if (oldsize == set.size()) {
                    if (oldsize > size) {
                        size = oldsize;
                    }
                    s = s.substring(1, s.length());
                    set = new HashSet();
                    break;
                }
            }
        }
        return size;
    }
}
