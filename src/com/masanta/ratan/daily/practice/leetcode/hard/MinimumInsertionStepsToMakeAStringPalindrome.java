package com.masanta.ratan.daily.practice.leetcode.hard;

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

}

/**
 * Leetcode editotial: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/editorial/
 * Leetcode discuss:
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/solutions/3444915/java-solution-memorisation/?languageTags=java&topicTags=memoization
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/solutions/3443028/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/
 * Youtube link: https://www.youtube.com/watch?v=xPBLEj41rFU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=30
 */
