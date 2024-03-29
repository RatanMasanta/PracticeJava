package com.masanta.ratan.neetcode.roadmmap.ArraysAndHashing;

import java.util.Arrays;

public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        // sort and check
        if(s.length() != t.length()) return false;
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for(int i = 0; i < c1.length; i++){
            if(c1[i] != c2[i]){
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        String s1 = "anagram";
        String s2 = "gramaan";
        System.out.println(isAnagram(s1, s2));
    }


}
