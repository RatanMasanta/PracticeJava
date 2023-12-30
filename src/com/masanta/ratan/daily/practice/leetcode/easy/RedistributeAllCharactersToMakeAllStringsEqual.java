package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class RedistributeAllCharactersToMakeAllStringsEqual {

    /*
        You are given an array of strings words (0-indexed).

        In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].

        Return true if you can make every string in words equal using any number of operations, and false otherwise.



        Example 1:

        Input: words = ["abc","aabc","bc"]
        Output: true
        Explanation: Move the first 'a' in words[1] to the front of words[2],
        to make words[1] = "abc" and words[2] = "abc".
        All the strings are now equal to "abc", so return true.
        Example 2:

        Input: words = ["ab","a"]
        Output: false
        Explanation: It is impossible to make all the strings equal using the operation.


        Constraints:

        1 <= words.length <= 100
        1 <= words[i].length <= 100
        words[i] consists of lowercase English letters.

     */

    public static boolean makeEqual(String[] words){
        int length = words.length;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < length; i++){
            for(char c: words[i].toCharArray()){
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }
        for(Map.Entry mapEntry : freqMap.entrySet()){
            if((Integer) mapEntry.getValue() % length != 0) return false;
        }
        return true;
    }

    public static boolean makeEqualTwo(String[] words) {
        if (words.length == 1) {
            return true;
        }
        int totalCharCount = 0;
        for (String s : words) {
            totalCharCount += s.length();
        }
        if (totalCharCount % words.length != 0) {
            return false;
        }

        int[] myMap = new int[26];
        for (String s : words) {
            for (char c : s.toCharArray()) {
                myMap[c - 'a']++; // dem tan suat xuat hien, 'b' - 'a' = 1
            }
        }
        for (int i : myMap) {
            if (i % words.length != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] wordOne = {"a","b"};
        String[] wordTwo = {"abc","aabc","bc"};
        System.out.println(RedistributeAllCharactersToMakeAllStringsEqual.makeEqual(wordTwo));
        System.out.println(RedistributeAllCharactersToMakeAllStringsEqual.makeEqual(wordOne));
        System.out.println(RedistributeAllCharactersToMakeAllStringsEqual.makeEqualTwo(wordTwo));
        System.out.println(RedistributeAllCharactersToMakeAllStringsEqual.makeEqualTwo(wordOne));
    }

}
