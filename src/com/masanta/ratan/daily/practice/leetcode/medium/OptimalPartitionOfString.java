package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class OptimalPartitionOfString {

    /**
     *
     * 2405. Optimal Partition of String
     *
     * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
     *
     * Return the minimum number of substrings in such a partition.
     *
     * Note that each character should belong to exactly one substring in a partition.
     *
     * Input: s = "abacaba"
     * Output: 4
     * Explanation:
     * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
     * It can be shown that 4 is the minimum number of substrings needed.
     *
     * @param s String
     * @return number of unique partitions that can be made
     */
    public int partitionString(String s) {
        int i = 0, ans = 1, flag = 0;
        while(i < s.length()) {
            int val = s.charAt(i) - 'a';
            if ((flag & (1 << val)) != 0) {
                flag = 0;
                ans++;
            }
            flag = flag | (1 << val);
            i++;
        }
        return ans;
    }

    public int partitionStringWithoutBitManipulation(String s) {
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int count = 1, substringStart = 0;

        for (int i = 0; i < s.length(); i++) {
            if (lastSeen[s.charAt(i) - 'a'] >= substringStart) {
                count++;
                substringStart = i;
            }
            lastSeen[s.charAt(i) - 'a'] = i;
        }

        return count;
    }

    public static void main(String[] args) {
        OptimalPartitionOfString optimalPartitionOfString = new OptimalPartitionOfString();
        String s = "abacabcdefdfhiklfg";
        String s1 = "abacaba";
        System.out.println(optimalPartitionOfString.partitionString(s));
        System.out.println(optimalPartitionOfString.partitionString(s1));
        System.out.println(optimalPartitionOfString.partitionStringWithoutBitManipulation(s));
        System.out.println(optimalPartitionOfString.partitionStringWithoutBitManipulation(s1));
    }

    /*
        Leetcode Editorial: https://leetcode.com/problems/optimal-partition-of-string/editorial/
        Leetcode discuss: https://leetcode.com/problems/optimal-partition-of-string/solutions/3376693/image-explanation-3-approaches-o-1-space-c-java-python/
        Solution 2: https://leetcode.com/problems/optimal-partition-of-string/solutions/3376662/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/

     */

}
