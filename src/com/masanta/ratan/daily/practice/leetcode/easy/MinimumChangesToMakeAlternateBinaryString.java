package com.masanta.ratan.daily.practice.leetcode.easy;

public class MinimumChangesToMakeAlternateBinaryString {

    /*
        Problem statement:
            You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.

            The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.

            Return the minimum number of operations needed to make s alternating.



            Example 1:

            Input: s = "0100"
            Output: 1
            Explanation: If you change the last character to '1', s will be "0101", which is alternating.
            Example 2:

            Input: s = "10"
            Output: 0
            Explanation: s is already alternating.
            Example 3:

            Input: s = "1111"
            Output: 2
            Explanation: You need two operations to reach "0101" or "1010".


            Constraints:

            1 <= s.length <= 104
            s[i] is either '0' or '1'.
     */

    public static int minOperations(String s) {
        char c_0 = s.charAt(0);
        int count1 = count(s, c_0);
        int count2 = count(s, c_0=='0'?'1':'0')+1;
        return Math.min(count1, count2);
    }

    private static int count(String s, char c_pre){
        int count = 0;
        for(int i=1; i<s.length(); i++){
            char current = s.charAt(i);
            if(current==c_pre){
                count++;
                c_pre = c_pre == '0' ? '1' : '0';
            }else{
                c_pre = current;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        String binaryString = "010101110101011111111111110";
        System.out.println(MinimumChangesToMakeAlternateBinaryString.minOperations(binaryString));

    }
}
