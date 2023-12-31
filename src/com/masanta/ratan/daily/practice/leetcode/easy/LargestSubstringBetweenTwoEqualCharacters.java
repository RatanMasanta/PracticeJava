package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class LargestSubstringBetweenTwoEqualCharacters {

    /*
        Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.

        A substring is a contiguous sequence of characters within a string.



        Example 1:

        Input: s = "aa"
        Output: 0
        Explanation: The optimal substring here is an empty substring between the two 'a's.
        Example 2:

        Input: s = "abca"
        Output: 2
        Explanation: The optimal substring here is "bc".
        Example 3:

        Input: s = "cbzxy"
        Output: -1
        Explanation: There are no characters that appear twice in s.


        Constraints:

        1 <= s.length <= 300
        s contains only lowercase English letters.
     */

    public static int maxLengthBetweenEqualCharacters(String s) {
        // Sliding Window
        char[] chars = s.toCharArray();
        int len = s.length();
        for (int i = len - 1; i > 0; i--) { // starts from farther end
            for (int j = 0; j + i < len; j++) { // the other end from the start
                if (chars[j] == chars[j + i]) return i - 1;
            }
        }
        return -1;
    }

    public static int maxLengthBetweenEqualCharactersTwo(String s) {
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        int maxLength = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (lastOccurrence.containsKey(c)) {
                maxLength = Math.max(maxLength, i - lastOccurrence.get(c) - 1);
            } else {
                lastOccurrence.put(c, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s1 ="aa";
        String s2 = "abca";
        String s3 = "mgntdygtxrvxjnwksqhxuxtrv";
        String s4 = "cbxyz";
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters(s1));
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters(s2));
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters(s3));
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharacters(s4));

        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharactersTwo(s1));
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharactersTwo(s2));
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharactersTwo(s3));
        System.out.println(LargestSubstringBetweenTwoEqualCharacters.maxLengthBetweenEqualCharactersTwo(s4));
    }

}
