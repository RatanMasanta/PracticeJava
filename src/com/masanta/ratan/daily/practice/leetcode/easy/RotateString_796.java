package com.masanta.ratan.daily.practice.leetcode.easy;

public class RotateString_796 {

    /**
     *
     * Problem statement
     *
     * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
     *
     * A shift on s consists of moving the leftmost character of s to the rightmost position.
     *
     * For example, if s = "abcde", then it will be "bcdea" after one shift.
     *
     *
     * Example 1:
     *
     * Input: s = "abcde", goal = "cdeab"
     * Output: true
     * Example 2:
     *
     * Input: s = "abcde", goal = "abced"
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= s.length, goal.length <= 100
     * s and goal consist of lowercase English letters.
     *
     */

    void main(){
        String s1 = "abcde";
        String s2 = "cdeab";

        System.out.println("Rotate String results for " + s1 + " and " +  s2 + " upon roatate string is: " + rotateString(s1, s2));
    }

    public static boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) return false;
        if(s.equals(goal)) return true;
        boolean isSubstring = true;
        int counter = 0;
        while(isSubstring){
            counter++;
            isSubstring = s.contains(goal.substring(0,counter + 1));
        }
        int j = counter;
        for(int i = counter; i < goal.length(); i++){
            if(s.charAt(i - j) != goal.charAt(i)) return false;
        }
        return true;
    }

}
