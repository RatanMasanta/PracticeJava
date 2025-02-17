package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.ArrayList;

public class SearchPattern {

    /**
     * Problem Statement:
     * Given two strings, one is a text string txt and the other is a pattern string pat. The task is to print the indexes of all the occurrences of the pattern string in the text string. Use 0-based indexing while returning the indices.
     * Note: Return an empty list in case of no occurrences of pattern.
     *
     * Examples :
     *
     * Input: txt = "abcab", pat = "ab"
     * Output: [0, 3]
     * Explanation: The string "ab" occurs twice in txt, one starts at index 0 and the other at index 3.
     * Input: txt = "abesdu", pat = "edu"
     * Output: []
     * Explanation: There's no substring "edu" present in txt.
     * Input: txt = "aabaacaadaabaaba", pat = "aaba"
     * Output: [0, 9, 12]
     * Explanation:
     *
     * Constraints:
     * 1 ≤ txt.size() ≤ 106
     * 1 ≤ pat.size() < txt.size()
     * Both the strings consist of lowercase English alphabets.
     *
     * Company: Microsoft
     *
     */


    void main(){
        String pat = "aaba";
        String txt = "aabaacaadaabaaba";
        System.out.println(STR."Pattern indices present in text are: \{SearchPattern.search(pat, txt)}");
    }

    public static ArrayList<Integer> search(String pat, String txt) {
        ArrayList<Integer> indices = new ArrayList<>();

        int M = pat.length();
        int N = txt.length();

        // Create the longest prefix suffix (LPS) array
        int[] lps = new int[M];
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt
        int j = 0; // index for pat
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                indices.add(i - j); // Add the starting index of the pattern in the text
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return indices;
    }

    private static void computeLPSArray(String pat, int M, int[] lps) {
        int length = 0; // length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < M) {
            if (pat.charAt(i) == pat.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
