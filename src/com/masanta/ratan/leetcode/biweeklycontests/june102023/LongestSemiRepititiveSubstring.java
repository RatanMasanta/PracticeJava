package com.masanta.ratan.leetcode.biweeklycontests.june102023;

public class LongestSemiRepititiveSubstring {

    /*

        6425. Find the Longest Semi-Repetitive Substring
        You are given a 0-indexed string s that consists of digits from 0 to 9.

        A string t is called a semi-repetitive if there is at most one consecutive pair of the same digits inside t.

        Return the length of the longest semi-repetitive substring inside s.

        A substring is a contiguous non-empty sequence of characters within a string.



        Example 1:

        Input: s = "52233"
        Output: 4
        Explanation: The longest semi-repetitive substring is "5223", which starts at i = 0 and ends at j = 3.
        Example 2:

        Input: s = "5494"
        Output: 4
        Explanation: s is a semi-reptitive string, so the answer is 4.
        Example 3:

        Input: s = "1111111"
        Output: 2
        Explanation: The longest semi-repetitive substring is "11", which starts at i = 0 and ends at j = 1.
     */

    /**
     *
     * We scan through the input string s, keeping track of the count of consecutive repetitions (count) and
     * the left pointer (left). We eliminate extra repetitions using the inside while loop and update the
     * maximum length of the semi-repetitive substring (maxLen) whenever a longer semi-repetitive substring
     * is found.
     * Finally, we return the final value of maxLength, which represents the length of the longest
     * semi-repetitive substring in the input string s.
     *
     * @param s Provided String of numbers
     * @return length of longest semi repetitive substring
     */
    public int longestSemiRepetitiveSubstring(String s) {
        int maxLen = 1;  // Stores the length of the longest semi-repetitive substring
        int count = 0;  // Counts the number of consecutive repeated digits
        int left = 0;  // Points to the left end of the current substring

        for (int right = 0; right < s.length(); right++) {
            // Check if the current digit is the same as the previous one
            if (right > 0 && s.charAt(right - 1) == s.charAt(right)) {
                count++;
            }

            while (count > 1) {
                // Check if the digit at the left pointer is the same as the next one
                if (right > left && s.charAt(left) == s.charAt(left + 1)) {
                    count--;
                }
                left++;
            }

            // Update the maximum length of the semi-repetitive substring
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public int longestSemiRepetitiveSubstringSlidingWindow(String s)
    {

        int n = s.length();
        int i = 0,j=0;
        int len =0;
        while( j < n )
        {
            if(check(s.substring(i,j+1)) == true)          // window is valid
            {
                len = Math.max(len,j-i+1);
                j++;
            }
            else         // if invalid
            {
                i++;
            }
        }
        return len;
    }
    // Function to check weather the string is semi-repetitive or not
    public boolean check ( String str)
    {
        int cnt =0;
        for(int i = 0; i < str.length()-1;i++)
        {
            if(str.charAt(i) == str.charAt(i+1)) cnt++;
        }
        if(cnt <= 1) return true;
        else return false;

    }

    public static void main(String[] args) {
        LongestSemiRepititiveSubstring longestSemiRepititiveSubstring = new LongestSemiRepititiveSubstring();
        String numberString = "522633467";
        System.out.println(longestSemiRepititiveSubstring.longestSemiRepetitiveSubstring(numberString));
        System.out.println(longestSemiRepititiveSubstring.longestSemiRepetitiveSubstringSlidingWindow(numberString));
    }
}
