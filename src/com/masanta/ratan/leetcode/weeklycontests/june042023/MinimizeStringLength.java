package com.masanta.ratan.leetcode.weeklycontests.june042023;

import java.util.HashSet;
import java.util.Set;

public class MinimizeStringLength {

    /*
    2716. Minimize String Length

    Difficulty:Medium
    Given a 0-indexed string s, repeatedly perform the following operation any number of times:

    Choose an index i in the string, and let c be the character in position i. Delete the closest occurrence of c to the left of i (if any) and the closest occurrence of c to the right of i (if any).
    Your task is to minimize the length of s by performing the above operation any number of times.

    Return an integer denoting the length of the minimized string.


     */

    public int minimizedStringLength(String s) {
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < s.length(); i++){
            if(!set.contains(s.substring(i,i+1))){
                set.add(s.substring(i,i+1));
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        MinimizeStringLength minimizeStringLength = new MinimizeStringLength();
        String str = "abcbsabfersresa";
        System.out.println(minimizeStringLength.minimizedStringLength(str));
    }

}
