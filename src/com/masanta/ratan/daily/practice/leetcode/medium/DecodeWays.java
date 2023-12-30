package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class DecodeWays {

    /*
        Problem statement:
        A message containing letters from A-Z can be encoded into numbers using the following mapping:

        'A' -> "1"
        'B' -> "2"
        ...
        'Z' -> "26"
        To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

        "AAJF" with the grouping (1 1 10 6)
        "KJF" with the grouping (11 10 6)
        Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

        Given a string s containing only digits, return the number of ways to decode it.

        The test cases are generated so that the answer fits in a 32-bit integer.



        Example 1:

        Input: s = "12"
        Output: 2
        Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
        Example 2:

        Input: s = "226"
        Output: 3
        Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
        Example 3:

        Input: s = "06"
        Output: 0
        Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").


        Constraints:

        1 <= s.length <= 100
        s contains only digits and may contain leading zero(s).
     */

    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        if(s == null || s.length() == 0) return 0;
        dp[0] = 1; // empty string
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // If we have the first char value as 0, we won't be able to make any new decoded messages
        for(int i = 2; i <= n; i++){
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if (first >= 1 && first <= 9){
                dp[i] += dp[i-1]; // valid first integer
            }
            if(second >= 10 && second <= 26){
                dp[i] += dp[i-2]; // valid first second integer
            }
        }
        return dp[n];
    }

    public static int topDownApproachNumDecoding(String s){
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return topDownDecode(s, 0, memo);
    }

    private static int topDownDecode(String s, int index, int[] memo) {
        // Base case: if the index reaches the end of the string
        if (index == s.length()) {
            return 1; // This is a valid decoding
        }

        // Check memoization table
        if (memo[index] != -1) {
            return memo[index];
        }

        // Check for leading zero
        if (s.charAt(index) == '0') {
            return 0; // This decoding is invalid
        }

        // Decode single digit
        int ways = topDownDecode(s, index + 1, memo);

        // Decode two digits if possible
        if (index + 1 < s.length() && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            ways += topDownDecode(s, index + 2, memo);
        }

        // Memoize the result
        memo[index] = ways;

        return ways;
    }

    public static int numDecodingsBottomUp(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        // Initialize the DP array
        int[] dp = new int[n + 1];
        dp[n] = 1; // There is one way to decode an empty string

        // Fill in the DP array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Check for leading zero
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                // Decode single digit
                dp[i] += dp[i + 1];

                // Decode two digits if possible
                if (i + 1 < n && Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        String s1= "541462015";
        String s2 = "25122023";
        String s3 = "226";
        System.out.println(DecodeWays.numDecodings(s1));
        System.out.println(DecodeWays.numDecodings(s2));
        System.out.println(DecodeWays.numDecodings(s3));
        System.out.println(DecodeWays.numDecodingsBottomUp(s1));
        System.out.println(DecodeWays.numDecodingsBottomUp(s2));
        System.out.println(DecodeWays.numDecodingsBottomUp(s3));
        System.out.println(DecodeWays.numDecodings(s1));
        System.out.println(DecodeWays.numDecodings(s2));
        System.out.println(DecodeWays.numDecodings(s3));
        System.out.println(DecodeWays.topDownApproachNumDecoding(s1));
        System.out.println(DecodeWays.topDownApproachNumDecoding(s2));
        System.out.println(DecodeWays.topDownApproachNumDecoding(s3));


    }

}
