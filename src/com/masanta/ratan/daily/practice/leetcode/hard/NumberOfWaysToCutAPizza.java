package com.masanta.ratan.daily.practice.leetcode.hard;

public class NumberOfWaysToCutAPizza {


    /**
     * 1444. Number of Ways of Cutting a Pizza
     *
     * Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
     *
     * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
     *
     * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
     *
     * Input: pizza = ["A..","AAA","..."], k = 3
     * Output: 3
     * Explanation: The figure above shows the three ways to cut the pizza. Note that pieces must contain at least one apple.
     *
     * @param pizza String array of pizza
     * @param k number of pieces necessary
     * @return number of ways to cut the pizza
     */
    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        Integer[][][] dp = new Integer[k][m][n];
        int[][] preSum = new int[m+1][n+1];

        for (int r = m - 1; r >= 0; r--)
            for (int c = n - 1; c >= 0; c--)
                preSum[r][c] = preSum[r][c+1] + preSum[r+1][c] - preSum[r+1][c+1] + (pizza[r].charAt(c) == 'A' ? 1 : 0);

        return dfs(m, n, k-1, 0, 0, dp, preSum);
    }

    private int dfs(int m, int n, int k, int r, int c, Integer[][][] dp, int[][] preSum) {
        if (preSum[r][c] == 0) return 0;
        if (k == 0) return 1;
        if (dp[k][r][c] != null) return dp[k][r][c];
        int ans = 0;

        for (int nr = r + 1; nr < m; nr++)
            if (preSum[r][c] - preSum[nr][c] > 0)
                ans = (ans + dfs(m, n, k - 1, nr, c, dp, preSum)) % 1_000_000_007;
        for (int nc = c + 1; nc < n; nc++)
            if (preSum[r][c] - preSum[r][nc] > 0)
                ans = (ans + dfs(m, n, k - 1, r, nc, dp, preSum)) % 1_000_000_007;

        return dp[k][r][c] = ans;
    }


    public static void main(String[] args) {
        NumberOfWaysToCutAPizza numberOfWaysToCutAPizza = new NumberOfWaysToCutAPizza();
        String[] pizzaSliceMatrix = {"A..","AAA","..."};
        int numberOfPieces = 3;
        System.out.println(numberOfWaysToCutAPizza.ways(pizzaSliceMatrix, numberOfPieces ));
    }


    /*
        Solution discuss: https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/solutions/3360907/image-explanation-dp-prefix-sum-c-java-python/
        Youtube solution: https://www.youtube.com/watch?v=x05misqS7yI
        Another solution discuss: https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/solutions/3360876/day-365-100-java-c-python-explained-4-3-ways-dry-run-proof/
        LeetCode Editorial: https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/editorial/
     */
}
