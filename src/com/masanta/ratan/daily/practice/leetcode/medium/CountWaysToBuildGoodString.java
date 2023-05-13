package com.masanta.ratan.daily.practice.leetcode.medium;

public class CountWaysToBuildGoodString {

    // Memoization
    private int mod = 1_000_000_007;
    private int[] dp;

    /**
     *
     * 2466. Count Ways To Build Good Strings
     *
     * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and
     * then at each step perform either of the following:
     *
     * Append the character '0' zero times.
     * Append the character '1' one times.
     * This can be performed any number of times.
     *
     * A good string is a string constructed by the above process having a length between low and high (inclusive).
     *
     * Return the number of different good strings that can be constructed satisfying these properties. Since the
     * answer can be large, return it modulo 109 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: low = 3, high = 3, zero = 1, one = 1
     * Output: 8
     * Explanation:
     * One possible valid good string is "011".
     * It can be constructed as follows: "" -> "0" -> "01" -> "011".
     * All binary strings from "000" to "111" are good strings in this example.
     *
     * Intuition
     * We will implement the algorithm using a recursive method.
     * Let dfs(end) be the number of good strings of length end.
     *
     * The trick is that each time a recursive function calls itself,
     * it reduces the given problem dfs(end) into subproblems dfs(end - zero) and dfs(end - one).
     * The recursion call continues until it reaches a point where the subproblem can be solved without
     * further recursion, that is dfs(0) = 1.
     *
     * Similarly, we will also build an auxiliary array dp to avoid repeated computation.
     * Initially, we set every value dp[i] (except dp[0]) as -1, which also implies that dp[i] is not visited.
     * During the recursion, if dp[end] != -1, it means we have already calculated dfs(end) previously, so
     * just return dp[end].
     *
     * @param low minimum length of suitable good strings
     * @param high maximum length of suitable good strings
     * @param zero number of times '0' is appended
     * @param one number of times '1' is appended
     * @return
     */
    public int countGoodStrings(int low, int high, int zero, int one) {
        dp = new int[high + 1];
        // we need to find the relationship
        for(int i = 0; i <= high; i++){
            dp[i] = -1;
        }

        dp[0] = 1;

        int answer = 0;
        for(int end = low; end <= high; ++end){
            answer += dfs(end, zero, one);
            answer %= mod;
        }
        return answer;
    }

    private int dfs(int end, int appendZeroLength, int appendOneLength){
        if(dp[end] != -1) return dp[end];
        int count = 0;
        if(end >= appendOneLength){
            count += dfs(end - appendOneLength, appendZeroLength, appendOneLength);
        }
        if(end >= appendZeroLength){
            count += dfs(end - appendZeroLength, appendZeroLength , appendOneLength);
        }
        dp[end] = count % mod;
        return dp[end];
    }

    public static void main(String[] args) {
        CountWaysToBuildGoodString countWaysToBuildGoodString = new CountWaysToBuildGoodString();
        int minSize = 2;
        int maxSize = 3;
        int appendZeroLength = 1;
        int appendOneLength = 2;
        System.out.println(countWaysToBuildGoodString.countGoodStrings(minSize, maxSize, appendZeroLength, appendOneLength));
    }
}
