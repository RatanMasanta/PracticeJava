package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;
import java.util.Stack;

public class RemoveDuplicatedCharactersFromStringAndReturnLexicographicalShortestString {

    /*
    https://leetcode.com/problems/remove-duplicate-letters/description/?envType=daily-question&envId=2023-09-26
    316. Remove Duplicate Letters
        Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is
        the smallest in lexicographical order
         among all possible results.



        Example 1:

        Input: s = "bcabc"
        Output: "abc"
        Example 2:

        Input: s = "cbacdcbc"
        Output: "acdb"
     */
    public static void main(String[] args) {
        String duplicatedString = "bcabc";
        System.out.println(removeDuplicateChar(duplicatedString));
        System.out.println(removeDuplicateLetters(duplicatedString));
    }

    /**
     *
     * @param dulplicatedString given string with duplicated data
     * @return lexicographicallly smalles string without duplicates
     */
    public static String removeDuplicateChar(String dulplicatedString){
        int[] charlastIndexMap = new int[26];
        Arrays.fill(charlastIndexMap, -1);
        // a- z index positions and their last freq
        boolean[] hasVisited = new boolean[26];
        Arrays.fill(hasVisited, false);
        for(int i = 0; i < dulplicatedString.length(); i++){
            char c = dulplicatedString.charAt(i);
            if(charlastIndexMap[c - 'a'] < i){
                charlastIndexMap[c - 'a'] = i;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < dulplicatedString.length(); i++){
            char currentChar = dulplicatedString.charAt(i);
            int index = currentChar - 'a';
            if( hasVisited[index] ){
                continue;
            }
            while(result.length() > 0
                    &&  currentChar < result.charAt(result.length() - 1) // current value is less than the last added character
                    && charlastIndexMap[result.charAt(result.length() - 1) - 'a'] > i){ // last value comes later on in the string
                hasVisited[result.charAt(result.length() -1) - 'a'] = false; // reset the hasVisited to show that we are not using it
                result.deleteCharAt(result.length() - 1); // remove the last character since we have an extra one later
            }
            result.append(currentChar);
            hasVisited[index] = true;
        }
        return result.toString();
    }

    /**
     *
     * @param s given string with duplicated data
     * @return lexicographicallly smalles string without duplicates
     */
    public static String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i; // track the lastIndex of character presence
        }

        boolean[] seen = new boolean[26]; // keep track seen
        Stack<Integer> st = new Stack();

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (seen[curr]) continue; // if seen continue as we need to pick one char only
            while (!st.isEmpty() && st.peek() > curr && i < lastIndex[st.peek()]){
                seen[st.pop()] = false; // pop out and mark unseen
            }
            st.push(curr); // add into stack
            seen[curr] = true; // mark seen
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.append((char) (st.pop() + 'a'));
        return sb.reverse().toString();
    }

}
