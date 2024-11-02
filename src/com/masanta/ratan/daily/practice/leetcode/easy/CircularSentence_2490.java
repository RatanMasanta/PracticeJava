package com.masanta.ratan.daily.practice.leetcode.easy;

public class CircularSentence_2490 {

    /**
     *
     * Problem statement:
     *
     * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
     *
     * For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
     * Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.
     *
     * A sentence is circular if:
     *
     * The last character of a word is equal to the first character of the next word.
     * The last character of the last word is equal to the first character of the first word.
     * For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.
     *
     * Given a string sentence, return true if it is circular. Otherwise, return false.
     *
     *
     *
     * Example 1:
     *
     * Input: sentence = "leetcode exercises sound delightful"
     * Output: true
     * Explanation: The words in sentence are ["leetcode", "exercises", "sound", "delightful"].
     * - leetcode's last character is equal to exercises's first character.
     * - exercises's last character is equal to sound's first character.
     * - sound's last character is equal to delightful's first character.
     * - delightful's last character is equal to leetcode's first character.
     * The sentence is circular.
     * Example 2:
     *
     * Input: sentence = "eetcode"
     * Output: true
     * Explanation: The words in sentence are ["eetcode"].
     * - eetcode's last character is equal to eetcode's first character.
     * The sentence is circular.
     * Example 3:
     *
     * Input: sentence = "Leetcode is cool"
     * Output: false
     * Explanation: The words in sentence are ["Leetcode", "is", "cool"].
     * - Leetcode's last character is not equal to is's first character.
     * The sentence is not circular.
     *
     *
     * Constraints:
     *
     * 1 <= sentence.length <= 500
     * sentence consist of only lowercase and uppercase English letters and spaces.
     * The words in sentence are separated by a single space.
     * There are no leading or trailing spaces.
     *
     */


    void main(){
        String sentence = "leetcode exercises sound delightful";
        System.out.println(STR."\{sentence} is a circular sentence? Method 1 : \{isCircularSentence(sentence)}, and check on 2nd method: \{isCircularSentence1(sentence)}" );
    }

    private static boolean isCircularSentence(String s) {
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false;

        int k = s.indexOf(" ");
        if (k == -1)
            return true;

        while (k != -1) {
            if (s.charAt(k - 1) != s.charAt(k + 1)) {
                return false;
            }

            k = s.indexOf(" ", k+1);
        }
        return true;
    }

    private static boolean isCircularSentence1(String sentence) {
         if(sentence.length() == 1) return true;
         String words[] = sentence.split(" ");
         if(words[words.length - 1].charAt(words[words.length - 1].length() - 1) != words[0].charAt(0)) return false;
         for(int i = 0; i < words.length - 1; i++){
            if ( words[i].charAt(words[i].length() - 1) != words[i+1].charAt(0) ) return false;
         }
         return true;
     }

}
