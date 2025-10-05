package com.masanta.ratan.daily.practice.dsa.sixteenweekplan.day2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    /*
        3. Longest Substring Without Repeating Characters


            Given a string s, find the length of the longest substring without duplicate characters.

            Example 1:

            Input: s = "abcabcbb"
            Output: 3
            Explanation: The answer is "abc", with the length of 3.
            Example 2:

            Input: s = "bbbbb"
            Output: 1
            Explanation: The answer is "b", with the length of 1.
            Example 3:

            Input: s = "pwwkew"
            Output: 3
            Explanation: The answer is "wke", with the length of 3.
            Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


            Constraints:

            0 <= s.length <= 5 * 104
            s consists of English letters, digits, symbols and spaces.

     */


    public static void main(String[] args) {
        String s = "pwwkew";
        String t = "abcsbcanbbvsdvldotipadfn";
        System.out.println(lengthOfLongestSubstring_1(s));
        System.out.println(lengthOfLongestSubstring_2(s));
        System.out.println(lengthOfLongestSubstring_1(t));
        System.out.println(lengthOfLongestSubstring_2(t));
    }


    /**
     *
     * This method uses a sliding window and set approach to solve the question.
     * Here, we take the set to check if we have visted the element before or not.
     * If we get a duplicate, we remove the elements of set till the current character is removed.
     * Then we move the left side pointer in front and check the maxLength by formula: right - left +1
     *
     * @param s String given
     * @return Length of longest substring from string s
     */
    public static int lengthOfLongestSubstring_1(String s) {
        Set<Character> charSet = new HashSet<>();
        char[] charArr = s.toCharArray();
        int n = charArr.length, left  = 0, right = 0, maxLength = 0;
        for(int i = 0; i < n; i++ ){
            char c = charArr[i];
            while(charSet.contains(c)){
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(c);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }


    /**
     *
     * This method uses a HashMap (array can also be used) to store the last seen index of the current character.
     * If we revisit a key, we update the value in the map and move the left pointer upfront.
     * We check the maxLength by the formula: right - left + 1
     * We return the max length we get.
     *
     * @param s String given
     * @return Length of longest substring from string sn
     */
    public static int lengthOfLongestSubstring_2(String s) {
        int left = 0, maxLength = 0, n = s.length();
        HashMap<Character, Integer> lastSeenMap = new HashMap<>();
        for(int right = 0; right < n; right++){// start counting
            char c = s.charAt(right);
            if(lastSeenMap.containsKey(c) &&
                    lastSeenMap.get(c) >= left){ // we get another occurence of the character at a later point of time
                left = lastSeenMap.get(c) + 1;
            }
            lastSeenMap.put(c, right); // Add or update the character and the lastSeen value
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

}
