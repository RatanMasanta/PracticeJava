package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class NthTribonacci {

    private Map<Integer, Integer> dp = new HashMap<>(){{
        put(0, 0);
        put(1, 1);
        put(2, 1);
    }};

    private int dfs(int i) {
        if (dp.containsKey(i)) {
            return dp.get(i);
        }

        int answer = dfs(i - 1) + dfs(i - 2) + dfs(i - 3);
        dp.put(i, answer);
        return answer;
    }

    /**
     *
     * 1137. N-th Tribonacci Number
     *
     *  The Tribonacci sequence Tn is defined as follows:
     *
     * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
     *
     * Given n, return the value of Tn.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 4
     * Output: 4
     * Explanation:
     * T_3 = 0 + 1 + 1 = 2
     * T_4 = 1 + 1 + 2 = 4
     *
     * TOP DOWN (MEMOIZATION)
     *
     * @param n the index of the number in the tribonacci series
     * @return the value of value at the nth position in the tribonacci series
     */
    public int tribonaccMemoizationi(int n) {
        return dfs(n);
    }


    /**
     * BOTTOM UP (TABULATION)
     * @param n the index of the number in the tribonacci series
     * @return the value of value at the nth position in the tribonacci series
     */
    public int tribonacciTabulation(int n) {
        if (n < 3) {
            return n > 0 ? 1 : 0;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    /**
     * Space Optimized
     * @param n the index of the number in the tribonacci series
     * @return the value of value at the nth position in the tribonacci series
     */
    public int tribonacciSpaceOptimized(int n) {
        if (n < 3) {
            return n > 0 ? 1 : 0;
        }

        int a = 0, b = 1, c = 1;
        for (int i = 0; i < n - 2; ++i) {
            int tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
        }

        return c;
    }

    public static void main(String[] args) {
        NthTribonacci tribonacci = new NthTribonacci();
        System.out.println(tribonacci.tribonacciTabulation(4));
        System.out.println(tribonacci.tribonacciSpaceOptimized(4));
        System.out.println(tribonacci.tribonaccMemoizationi(4));
    }
}
