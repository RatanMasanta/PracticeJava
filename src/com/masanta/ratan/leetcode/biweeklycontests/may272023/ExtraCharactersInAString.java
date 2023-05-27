package com.masanta.ratan.leetcode.biweeklycontests.may272023;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExtraCharactersInAString {

    /**
     * 2707. Extra Characters in a String
     *
     * You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
     *
     * Return the minimum number of extra characters left over if you break up s optimally.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
     * Output: 1
     * Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.
     *
     *
     * @param s Given String
     * @param dictionary Given arry of Strings to match from
     * @return extra characters left in the string
     */
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        // Create set for dictionary for O(1) search in dictionary.
        for (String word : dictionary) set.add(word);

        // Memoization array, with Integer.MAX_VALUE as default value.
        int[] dp = new int[s.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // start with index 0.
        return split(s, 0, dp, set);
    }

    private int split(String s, int index, int[] dp, Set<String> dictionary) {
        // if index reached at the end of the string there no extra character remaining.
        if (index >= s.length()) return 0;

        // if dictionary contains word for subtring from index to length.
        // There is no extra character.
        if (dictionary.contains(s.substring(index, s.length()))) return 0;

        // if dp[index] is not default value,
        // we have already calculated result for this index.
        if (dp[index] != Integer.MAX_VALUE) return dp[index];

        // Default value can be all the characters of subtring.
        int min = s.length() - index;
        // check for all possible substring from current index
        for (int i = index + 1; i <= s.length(); i++) {
            // if this substring is in dictionary there are zero characters.
            // else all characters of this substring are remaining.
            int count = dictionary.contains(s.substring(index, i)) ? 0 : i - index;
            // check for second half after split.
            count += split(s, i, dp, dictionary);
            // updated the minimum value.
            min = Math.min(min, count);
        }

        // update dp[index] with current min.
        dp[index] = min;
        return min;
    }

    /**
     *
     * Dynamic Programming Approach:
     * The main idea is to use an array dp of size n+1, where n is the length of the input string s. The value of dp[i] represents the minimum number of extra characters left over if we break up the substring s[i..n-1] optimally.
     *
     * First, we initialize the dp array with all values set to n+1, except for dp[n], which is set to 0. This is because if there are no characters left in the string, there are no extra characters left over.
     *
     * Then, we iterate backwards from n-1 to 0. For each index i, we first set dp[i] to dp[i+1]+1. This represents the case where we do not break up the substring at index i, and instead leave it as an extra character.
     *
     * Next, we iterate from i+1 to n, checking if the substring s[i..j-1] is present in the dictionary. If it is, we update dp[i] to be the minimum of its current value and dp[j]. This represents the case where we break up the substring at index j, and there are no extra characters between indices i and j-1.
     *
     * Finally, after all iterations are complete, the value of dp[0] represents the minimum number of extra characters left over if we break up the entire string s optimally.
     *
     * @param s
     * @param dictionary
     * @return
     */
    public int minExtraChar2(String s, String[] dictionary) {
            Set<String> dict = new HashSet<>();
            for (String word : dictionary) {
                dict.add(word);
            }

            int n = s.length();
            int[] dp = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                dp[i] = dp[i - 1] + 1;

                for (int j = i - 1; j >= 0; j--) {
                    String substring = s.substring(j, i);
                    if (dict.contains(substring)) {
                        dp[i] = Math.min(dp[i], dp[j]);
                    }
                }
            }

            return dp[n];
        }

    public static void main(String[] args) {
        String s = "sayhelloworld";
        String[] dictionary = {"hello","world"};
        ExtraCharactersInAString extraCharactersInAString =
                new ExtraCharactersInAString();
        System.out.println(extraCharactersInAString.minExtraChar(s, dictionary));
        System.out.println(extraCharactersInAString.minExtraChar2(s, dictionary));
    }

}
