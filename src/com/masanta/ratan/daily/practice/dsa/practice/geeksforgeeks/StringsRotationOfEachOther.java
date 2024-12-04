package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

public class StringsRotationOfEachOther {

    /**
     *
     * Strings Rotations of Each Other
     * Difficulty: EasyAccuracy: 43.83%Submissions: 191K+Points: 2
     * You are given two strings of equal lengths, s1 and s2. The task is to check if s2 is a rotated version of the string s1.
     *
     * Note: The characters in the strings are in lowercase.
     *
     * Examples :
     *
     * Input: s1 = "abcd", s2 = "cdab"
     * Output: true
     * Explanation: After 2 right rotations, s1 will become equal to s2.
     * Input: s1 = "aab", s2 = "aba"
     * Output: true
     * Explanation: After 1 left rotation, s1 will become equal to s2.
     * Input: s1 = "abcd", s2 = "acbd"
     * Output: false
     * Explanation: Strings are not rotations of each other.
     * Constraints:
     * 1 <= s1.size(), s2.size() <= 10^5
     *
     * Company tag: Oracle, Adobe
     *
     */


    void main(){
        String s1 = "abcd";
        String s2 = "acbd";
        String s3 = "dabc";
        System.out.println(areRotations(s1, s2));
        System.out.println(areRotations1(s1, s3));
    }


    public static boolean areRotations(String s1, String s2) {
        // Your code here
        StringBuilder obj = new StringBuilder(s2);
        int m = s2.length();
        obj.append('$');
        obj.append(s1);
        obj.append(s1);
        s1 = new String(obj);
        int n = s1.length();
        int lps[] = new int[n];
        int len = 0;
        int i =1;
        while(i<n){
            if(s1.charAt(i) ==s1.charAt(len)){
                len++;
                if(len==m){
                    return true;
                }
                lps[i] =len;
                i++;
            }
            else{
                if(len>0){
                    len =lps[len-1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return false;
    }

    static boolean areRotations1(String s1, String s2) {
        int n = s1.length();

        // Generate and check all possible rotations of s1
        for (int i = 0; i < n; ++i) {

            // If current rotation is equal to s2, return true
            if (s1.equals(s2)) {
                return true;
            }

            // Right rotate s1
            char last = s1.charAt(s1.length() - 1);
            s1 = last + s1.substring(0, s1.length() - 1);
        }
        return false;
    }
}
