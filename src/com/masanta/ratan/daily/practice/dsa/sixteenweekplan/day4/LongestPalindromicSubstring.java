package com.masanta.ratan.daily.practice.dsa.sixteenweekplan.day4;

public class LongestPalindromicSubstring {

    /*



            Given a string s, return the longest palindromic substring in s.



            Example 1:

            Input: s = "babad"
            Output: "bab"
            Explanation: "aba" is also a valid answer.
            Example 2:

            Input: s = "cbbd"
            Output: "bb"


            Constraints:

            1 <= s.length <= 1000
            s consist of only digits and English letters.

     */

    public static void main(String[] args) {

        String input  = "babadabad";
        System.out.println(longestPalindrome_Manacher(input));
        System.out.println(longestPalindrome_DP(input));

    }


    /*
            Manacher's Algorithm is a powerful technique that allows us to find the longest palindromic substring in a given string in linear time. Here's a detailed breakdown of the algorithm's approach:
            1. String Transformation
    We first transform the original string to simplify the algorithm. This transformation achieves two things:

    It ensures that every potential center of a palindrome is surrounded by identical characters (#), which simplifies the process of expanding around a center.
    It adds special characters ^ at the beginning and $ at the end of the string to avoid any boundary checks during the palindrome expansion. For instance, the string "babad" is transformed into "^#b#a#b#a#d#$".
    2. Initialization
    We maintain an array P with the same length as the transformed string. Each entry P[i] denotes the radius (half-length) of the palindrome centered at position i.

    We also introduce two critical pointers:

    C: The center of the palindrome that has the rightmost boundary.
    R: The right boundary of this palindrome.
    Both C and R start at the beginning of the string.

    3. Iterating Through the String
    For every character in the transformed string, we consider it as a potential center for a palindrome.

    a. Using Previously Computed Information:
    If the current position is to the left of R, its mirror position about the center C might have information about a palindrome centered at the current position. We can leverage this to avoid unnecessary calculations.

    b. Expanding Around the Center:
    Starting from the current radius at position i (which might be derived from its mirror or initialized to 0), we attempt to expand around i and check if the characters are the same.

    c. Updating C and R:
    If the palindrome centered at i extends beyond R, we update C to i and R to the new boundary.

    4. Extracting the Result
    Once we've computed the palindromic radii for all positions in the transformed string, we find the position with the largest radius in P. This position represents the center of the longest palindromic substring. We then extract and return this palindrome from the original string.

    Complexity
    Time complexity: O(n)O(n)O(n)
    Manacher's algorithm processes each character in the transformed string once, making the time complexity linear.

    Space complexity: O(n)O(n)O(n)
    We use an array P to store the palindrome radii, making the space complexity linear as well.
        */
        public static String longestPalindrome_Manacher(String s) {
            StringBuilder sb = new StringBuilder("^#");
            for (char c : s.toCharArray()) {
                sb.append(c).append("#");
            }
            sb.append("$");
            String T = sb.toString();

            int n = T.length();
            int[] P = new int[n];
            int C = 0, R = 0;

            for (int i = 1; i < n-1; i++) {
                P[i] = (R > i) ? Math.min(R - i, P[2*C - i]) : 0;
                while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i]))
                    P[i]++;

                if (i + P[i] > R) {
                    C = i;
                    R = i + P[i];
                }
            }

            int max_len = 0, center_index = 0;
            for (int i = 0; i < n; i++) {
                if (P[i] > max_len) {
                    max_len = P[i];
                    center_index = i;
                }
            }
            return s.substring((center_index - max_len) / 2, (center_index + max_len) / 2);
        }


        public static String longestPalindrome_DP(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            int[] ans = new int[] { 0, 0 };

            for (int i = 0; i < n; i++) { // self characters would always be palindromes
                dp[i][i] = true;
            }

            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    ans[0] = i;
                    ans[1] = i + 1;
                }
            }

            for (int diff = 2; diff < n; diff++) {
                for (int i = 0; i < n - diff; i++) {
                    int j = i + diff;
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        ans[0] = i;
                        ans[1] = j;
                    }
                }
            }

            int i = ans[0];
            int j = ans[1];
            return s.substring(i, j + 1);
        }


}
