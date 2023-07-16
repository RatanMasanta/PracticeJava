package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.Arrays;

public class MinimumInsertionStepsToMakeAStringPalindrome {

    /**
     * 1312. Minimum Insertion Steps to Make a String Palindrome
     *
     * Given a string s. In one step you can insert any character at any index of the string.
     *
     * Return the minimum number of steps to make s palindrome.
     *
     * A Palindrome String is one that reads the same backward as well as forward.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "zzazz"
     * Output: 0
     * Explanation: The string "zzazz" is already palindrome we do not need any insertions.
     *
     *
     * Approach 1: Recursive Dynamic Programming
     * Intuition
     * If you are new to Dynamic Programming, please see our Leetcode Explore Card for more information on it!
     *
     * As our task is to insert minimum number of additional characters to s to make it a palindrome, we would want to figure out the longest palindromic subsequence that we can make from the characters in s. Characters that cannot be included in the longest palindromic subsequence must be adjusted by adding additional characters at required indices to form the entire string palindrome.
     *
     * The answer of the problem would be the length of s minus the length of the longest palindromic subsequence in s.
     *
     * There are several methods for determining the length of the longest palindromic subsequence in a string. The length of the longest common subsequence (LCS) in the given string and its reverse string is one of the most commonly used techniques. Here, we'll go over some of the approaches that make use of LCS.
     *
     * You can see some approaches that do not use LCS in this editoral of the longest palindromic subsequence problem.
     *
     * We will use recursion to find the length of the longest common subsequence in this approach.
     *
     * Let's take two strings, s1 which is equal to s and s2 which is the reverse of s. We want to find the longest common subsequence between these two strings.
     *
     * If the last characters of the substrings under consideration are the same, the last character will be considered in the final common subsequence. As a result, we add 1 and recursively calculate the length of the longest common subsequence in substrings formed by removing the last character from both strings.
     *
     * If the last characters aren't the same, we search for the LCS recursively by removing the last character from the first substring while keeping the second substring as is. We also recurse by leaving the first substring as is and removing the last character from the second. We choose the maximum of these because we want the longest common subsequence.
     *
     * To perform this recursion, we use two variables, m and n, where m denotes the first m characters from s1 and n denotes the first n characters from s2 that are being considered in the current recursion call. As a result, the recursive relation can be written as follows:
     *
     * If s1[m - 1] == s2[n - 1], i.e., the last characters match, perform answer = 1 + LCS(s1, s2, m - 1, n - 1).
     * Else, perform answer = max(LCS(s1, s2, m, n - 1), LCS(s1, s2, m - 1, n).
     * where LCS(string s1, string s2, int i, int j) is a recursive method that returns the longest common subsequence of the substrings taking the first i characters of s1 and the first j characters of s2 into account. The LCS of s1 and s2 is LCS(s1, s2, m, n), where m is the length of s1 and n is the length of s2.
     *
     * The recursion tree of the above relation for s1 and s2 would look something like this:
     *
     * img
     *
     * Several subproblems, such as LCS(s1, s2, m - 2, n - 1), LCS(s1, s2, m - 1, n - 1), etc., are solved twice in the partial recursion tree shown above. If we draw the entire recursion tree, we can see that there are many subproblems that are solved repeatedly.
     *
     * To avoid this issue, we store the solution of the subproblem in a 2D array when it is solved. When we encounter the same subproblem again, we simply refer to the array. This is called memoization.
     *
     * The answer of the problem would be n - LCS(s, sReverse, n, n) where n is length of s and sReverse is the reverse string of s.
     *
     * Algorithm
     * Create an integer variable n and initialize it to the size of s.
     * Create a string variable sReverse and set it to the reverse of s.
     * Create a 2D-array called memo having n + 1 rows and n + 1 columns where memo[i][j] contains the length of the longest common subsequence considering the first i characters of s and the first j characters of sReverse. We initialize the array to -1.
     * Return n - lcs(s, sReverse, n, n, memo) where lcs is a recursive method with four parameters: the first string s1, the second string s2, the length of the substring from the start of s1 under consideration, the length of the substring from the start of s2 under consideration and memo. It returns the length of the longest common subsequence in the substrings of s1 and s2 under consideration. We perform the following in this method:
     * If m == 0 || n == 0, it indicates one of the two substrings under consideration is empty, so we return 0.
     * If memo[m][n] != -1, it indicates that we have already solved this subproblem, so we return memo[m][n].
     * If the last characters of the substrings under consideration are the same, the last character has to be included. As a result, we add 1 and look for the length of the longest common subsequence by ignoring the last character of both the substrings under consideration. We return memo[i][j] = 1 + lcs(s1, s2, m - 1, n - 1, memo).
     * Otherwise, if the last characters do not match, we recursively search for the longest common subsequence in both the substrings formed after ignoring their last characters one by one. We pick the maximum of these two. We return memo[i][j] = max(lcs(s1, s2, m - 1, n, memo), lcs(s1, s2, m, n - 1, memo)).
     *
     * @param s String provided to convert into a palindrome
     * @return number of insertions required to make the string palindrome
     */
    public int minInsertions(String s) {
        int n = s.length();
        String sReverse = new StringBuilder(s).reverse().toString();
        int[][] memo = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = -1;
            }
        }

        return n - lcs(s, sReverse, n, n, memo);
    }

    private int lcs(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
        }
        return memo[m][n] = Math.max(lcs(s1, s2, m - 1, n, memo), lcs(s1, s2, m, n - 1, memo));
    }

    public static void main(String[] args) {
        MinimumInsertionStepsToMakeAStringPalindrome minimumInsertionStepsToMakeAStringPalindrome =
                new MinimumInsertionStepsToMakeAStringPalindrome();
        String s1 = "leetcode";
        System.out.println(minimumInsertionStepsToMakeAStringPalindrome.minInsertions(s1));
    }

    public static class MaximumNumberOfEventsThatCanBeAttendedTwo {

        /*
            1751. Maximum Number of Events That Can Be Attended II
            Hard
            1.8K
            33
            Companies
            You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.

            You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.

            Return the maximum sum of values that you can receive by attending events.



            Example 1:



            Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
            Output: 7
            Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
         */

        /**
         *
         * Overview
         * We can only attend an event if the start day of it is greater than the end day of the previously attended event. This implies that we should sort events by their start time. As shown in the following figure, we sort events = [[1,2,4],[3,4,3],[2,3,1],[4,6,5],[2,4,8]] according to the start time of each event.
         *
         * img
         *
         * All subsequent solutions are based on the sorted events.
         *
         * Approach 1: Top-down Dynamic Programming + Binary Search
         * Intuition
         * If you are not familiar with dynamic programming, please refer to our explore cards Dynamic Programming Explore Card. We will focus on the usage in this article and not the underlying principles or implementation details.
         *
         * Let dfs(cur_index) represent the maximum value obtained by attending events optimally in the range events[cur_index ~ n - 1]
         *
         * For event cur_index, we have two options:
         *
         * attend the current event and gain a value of events[cur_index][2]. Then we need to find the nearest event that we can attend after event cur_index. Recall that we have sorted events by start time. We can apply binary search to find the index where we should insert the end time of the current event cur_index in the sorted list of start times. Let's say the nearest one is event next_index. Thus dfs(cur_index) is the larger value between the two options:
         *
         * attend the current event and obtain a value of events[cur_index][2] + dfs(next_index).
         *
         * skip the current event, move on to the next event, and gain a value of dfs(cur_index + 1).
         *
         * which is denoted as dfs(cur_index) = max(dfs(cur_index + 1), dfs(next_index) + events[cur_index][2]).
         *
         *
         * As shown in the picture below, we find the insertion index is 3, which indicates that the nearest available event after event 0 is event 3.
         *
         * img
         *
         * Therefore, we can update dfs(0) as the larger value obtained by attending or skipping event 0.
         *
         * attend event 0 and get a value of events[0][2] + dfs(3).
         * skip event 0 and get a value of dfs(1).
         * img
         *
         * Given the restriction that we can attend a maximum of k events, we also need to keep track of count, the number of events we have attended so far. Therefore, we will redefine this function as dfs(cur_index, count).
         *
         * Additionally, we use memoization to store the maximum value obtained by each state (cur_index, count). This helps us avoid re-solving the same subproblems multiple times and significantly reduces the time complexity of the algorithm.
         *
         *
         * Algorithm
         * Sort events by start time.
         *
         * Build a 2D array dp of size (k+1)×n(k + 1) \times n(k+1)×n as memory.
         *
         * Define dfs(cur_index, count) as the maximum value obtained by attending a maximum of count events in the range events[cur_index ~ n - 1].
         *
         * If (count, cur_index) is already stored in dp, return dp[count][cur_index].
         *
         * Return 0 if count = 0 or cur_index = n.
         *
         * Skip this event and get the value of dfs(cur_index + 1, count).
         *
         * Find the index of the nearest available event next_index after the current event cur_index with binary search.
         *
         * Attend this event and get the value of dfs(next_index, count - 1) plus the value of this event events[cur_index][2].
         *
         * Store the larger one of the two values above in dp[count][cur_index] and return dp[count][cur_index].
         *
         * Return dfs(0, k).
         *
         * @param events int array with event details start ady, end day and value of the event
         * @param k maximum number of events you can attend
         * @return maximum sum of values that you can receive by attending events
         */
        public int maxValuePartOne(int[][] events, int k) {
            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            int n = events.length;

            dp = new int[k + 1][n];
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }

            return dfs(0, k, events);
        }

        private int[][] dp;
        private int dfs(int curIndex, int count, int[][] events) {
            if (count == 0 || curIndex == events.length) {
                return 0;
            }
            if (dp[count][curIndex] != -1) {
                return dp[count][curIndex];
            }
            int nextIndex = bisectRight(events, events[curIndex][1]);
            dp[count][curIndex] = Math.max(dfs(curIndex + 1, count, events), events[curIndex][2] + dfs(nextIndex, count - 1, events));
            return dp[count][curIndex];
        }

        private int bisectRight(int[][] events, int target) {
            int left = 0, right = events.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (events[mid][0] <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }


        /**
         * Approach 2: Bottom-up Dynamic Programming + Binary Search
         * Intuition
         * In the previous approach, we start with the original problem dfs(0, k) and recursively break it down into smaller subproblems. We can also use bottom-up DP that starts with the smallest subproblems and works its way up to the original problem.
         *
         * We can build a 2D array dp and let dp[count][cur_index] represent the maximum value we obtain by attending at most count events in the range events[cur_index ~ n - 1] (equivalent to dfs(cur_index, count) in the previous approach). We first solve the smallest subproblems, then use their solutions to solve slightly larger subproblems, and so on until we solve the original problem dp[0][k].
         *
         * For the current state dp[count][cur_index], we have two options:
         *
         * attend event cur_index and gain a value of events[cur_index][2]. Then we need to find the nearest events that we can attend after this event. Recall that we have sorted events according to the start times, so we can apply a binary search to find next_index, the inserting index of events[cur_index][1], the end time of this event, on the sorted start times. Thus the value we obtain is events[cur_index][2] + dp[count - 1][next_index].
         *
         * skip the event cur_index and move on to the next event, thus the value is equal to dp[count][cur_index + 1].
         *
         * Therefore, we have the recurrence relation as dp[count][cur_index] = max(dp[count][cur_index + 1], dp[count - 1][next_index] + events[cur_index][2]).
         *
         *
         * Algorithm
         * Sort events by start time.
         *
         * Define a dynamic programming table dp of size (k+1)⋅(n+1)(k + 1) \cdot (n + 1)(k+1)⋅(n+1).
         *
         * Iterate starting from the base cases. Iterate over events backward from n - 1 to 0. For each event, iterate over the number of events that can be attended from 1 to k.
         *
         * Locate nextIndex, the index of the first event whose starting time is greater than the end time of the current event curIndex using binary search.
         *
         * Update dp[count][curIndex] as max(dp[count][curIndex + 1], dp[count + 1][nextIndex] + events[curIndex][2]).
         *
         * Return dp[k][0] when the iteration is complete.
         *
         * @param events int array with event details start ady, end day and value of the event
         * @param k maximum number of events you can attend
         * @return maximum sum of values that you can receive by attending events
         */
        public int maxValuePartTwo(int[][] events, int k) {
            int n = events.length;
            int[][] dp = new int[k + 1][n + 1];
            Arrays.sort(events, (a, b) -> a[0] - b[0]);

            for (int curIndex = n - 1; curIndex >= 0; --curIndex) {
                for (int count = 1; count <= k; count++) {
                    int nextIndex = bisectRightDFS(events, events[curIndex][1]);
                    dp[count][curIndex] = Math.max(dp[count][curIndex + 1], events[curIndex][2] + dp[count - 1][nextIndex]);
                }
            }
            return dp[k][0];
        }

        private int bisectRightDFS(int[][] events, int target) {
            int left = 0, right = events.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (events[mid][0] <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        public static void main(String[] args) {

        }


    }
}

/**
 * Leetcode editotial: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/editorial/
 * Leetcode discuss:
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/solutions/3444915/java-solution-memorisation/?languageTags=java&topicTags=memoization
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/solutions/3443028/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/
 * Youtube link: https://www.youtube.com/watch?v=xPBLEj41rFU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=30
 */
