package com.masanta.ratan.daily.practice.leetcode.medium;

public class DecodedStringAtIndex {

    /*
    https://leetcode.com/problems/decoded-string-at-index/description/?envType=daily-question&envId=2023-09-27

    880. Decoded String at Index

    You are given an encoded string s. To decode the string to a tape, the encoded string is read one character at a time and the following steps are taken:

    If the character read is a letter, that letter is written onto the tape.
    If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
    Given an integer k, return the kth letter (1-indexed) in the decoded string.



    Example 1:

    Input: s = "leet2code3", k = 10
    Output: "o"
    Explanation: The decoded string is "leetleetcodeleetleetcodeleetleetcode".
    The 10th letter in the string is "o".

     */


    public static void main(String[] args) {
        String encodedString = "leet2code3";
        int k = 10;
        System.out.println(decodeAtIndex(encodedString,k));
    }

    /**
     *
     * @param inputString encoded string to decode
     * @param k index to find in the decoded string
     * @return string at kth index
     */
    public static String decodeAtIndex(String inputString, int k) {
        long decodedStringLength = 0; // Total length of the decoded string
        // Find the length of the decoded string
        for(char c: inputString.toCharArray()) {
            if (Character.isDigit(c)) {
                // If the character is a digit, update the decoded length accordingly
                decodedStringLength *= (c - '0');
            } else {
                // If the character is a letter, increment the decoded length
                decodedStringLength++;
            }
        }
        // Traverse the input string in reverse to decode and find the kth character
        for(int i = inputString.length() - 1; i > -1; i--){
            char currentChar = inputString.charAt(i);
            if(Character.isDigit(currentChar)){
                // If the character is a digit, adjust the length and k accordingly
                decodedStringLength /= (currentChar-'0');
                k %= decodedStringLength;
            } else {
                // If the character is a letter, check if it's the kth character
                if (k == 0 || decodedStringLength == k){
                    // Return the kth character as a string
                    return String.valueOf(currentChar);
                }
                decodedStringLength --;
            }
        }
        return ""; // Return an empty string if no character is found
    }

}
