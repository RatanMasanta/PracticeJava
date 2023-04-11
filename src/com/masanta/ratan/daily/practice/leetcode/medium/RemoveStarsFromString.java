package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Stack;

public class RemoveStarsFromString {

    /**
     *
     * 2390. Removing Stars From a String
     *
     * You are given a string s, which contains stars *.
     *
     * In one operation, you can:
     *
     * Choose a star in s.
     * Remove the closest non-star character to its left, as well as remove the star itself.
     * Return the string after all stars have been removed.
     *
     * Note:
     *
     * The input will be generated such that the operation is always possible.
     * It can be shown that the resulting string will always be unique.
     *
     * Input: s = "leet**cod*e"
     * Output: "lecoe"
     * Explanation: Performing the removals from left to right:
     * - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
     * - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
     * - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
     * There are no more stars, so we return "lecoe".
     *
     * @param s String passed
     * @return string after removal after removal of stars
     */
    public String removeStars(String s) {
        Stack<Character> charStack = new Stack<Character>();
        char[] charArray = s.toCharArray();
        for(char charLiteral : charArray){
            if(charLiteral != '*'){
                charStack.push(charLiteral);
            } else {
                if(!charStack.isEmpty()){
                    charStack.pop();
                }
            }
        }
        StringBuilder s1 = new StringBuilder();
        while(!charStack.isEmpty()){
            s1.append(charStack.pop());
        }
        return s1.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveStarsFromString removeStarsFromString = new RemoveStarsFromString();
        String stringToTest = "leet**cod*e";
        System.out.println(removeStarsFromString.removeStars(stringToTest));
    }


}
