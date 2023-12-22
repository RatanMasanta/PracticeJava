package com.masanta.ratan.daily.practice.leetcode.easy;

public class MaximumScoreAfterSplittingAString {


    /*
        Problem statement:

        1422. Maximum Score After Splitting a String

        Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).

        The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.



        Example 1:

        Input: s = "011101"
        Output: 5
        Explanation:
        All possible ways of splitting s into two non-empty substrings are:
        left = "0" and right = "11101", score = 1 + 4 = 5
        left = "01" and right = "1101", score = 1 + 3 = 4
        left = "011" and right = "101", score = 1 + 2 = 3
        left = "0111" and right = "01", score = 1 + 1 = 2
        left = "01110" and right = "1", score = 2 + 1 = 3
        Example 2:

        Input: s = "00111"
        Output: 5
        Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
        Example 3:

        Input: s = "1111"
        Output: 3


        Constraints:

        2 <= s.length <= 500
        The string s consists of characters '0' and '1' only.

     */

    // O(n^2) solution
    public static int maxScoreOne(String s) {
        int max = -1;
        for(int i = 0; i < s.length() - 1; i++){
            max = Math.max((countZero(s,i) + countOne(s,i)) , max);
        }
        return max;
    }
    private static int countZero(String s, int index){
        int count = 0;
        for(int  i = 0; i<= index; i++){
            if(s.charAt(i) == '0'){
                count++;
            }
        }

        return count;
    }
    private static int countOne(String s, int index){
        int count = 0;
        for(int  i = index + 1; i< s.length(); i++){
            if(s.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }


    /**
     *
     * Approach
     * Here's the approach in 5 points:
     *
     * Initialize Variables:
     *
     * onesCount to 0 to track the number of '1's encountered.
     * zerosCount to 0 to track the number of '0's encountered.
     * bestScore to INT_MIN to hold the best score found so far.
     * Iterate Through String (Except Last Character):
     *
     * For each character in the string (except the last one):
     * If the character is '1', increment onesCount.
     * If the character is '0', increment zerosCount.
     * Calculate the current score as zerosCount - onesCount.
     * Update bestScore with the maximum of bestScore and the current score.
     * Count Last Character:
     *
     * If the last character is '1', increment onesCount.
     * Calculate Final Score:
     *
     * The final score is calculated as bestScore + onesCount.
     * This effectively adds the fixed contribution of '1's in the second substring to the best score found in the first substring.
     * Return Final Score:
     *
     * Return the bestScore + onesCount value, representing the maximum score achievable.
     * Complexity
     * Time complexity:O(n)
     * Space complexity:O(1)
     *
     *
     * @param inputStr inputt string
     * @return
     */
    public static int maxScoreTwo(String inputStr) {
        int onesCount = 0;
        int zerosCount = 0;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < inputStr.length() - 1; i++) {
            if (inputStr.charAt(i) == '1') {
                onesCount++;
            } else {
                zerosCount++;
            }

            bestScore = Math.max(bestScore, zerosCount - onesCount);
        }

        if (inputStr.charAt(inputStr.length() - 1) == '1') {
            onesCount++;
        }

        return bestScore + onesCount;
    }


    /**
     *
     * Approach
     * Count '1's: Counts the total number of '1's in the string using count.
     * Initialize Variables:
     * Sets result to 0 to store the maximum score.
     * Sets zerosCount to 0 to track the number of '0's encountered.
     * Iterate Through String: Loops through each character of the string (except the last one):
     * If the current character is '1', decrements onesCount.
     * If the current character is '0', increments zerosCount.
     * Updates result with the maximum of result and zerosCount + onesCount.
     * Return Maximum Score: Returns the result variable, which holds the best possible score.
     * Key Idea: The approach cleverly avoids explicit string splitting by maintaining a count of '1's and adjusting it as the loop progresses, effectively simulating different splits implicitly.
     * Complexity
     * Time complexity:O(n)
     * Space complexity:O(1)
     *
     *
     * @param inputStr input string
     * @return
     */
    public static int maxScoreThree(String inputStr) {
        int onesCount = (int) inputStr.chars().filter(ch -> ch == '1').count();

        int result = 0;
        int zerosCount = 0;
        for (int i = 0; i < inputStr.length() - 1; i++) {
            if (inputStr.charAt(i) == '1') {
                onesCount--;
            } else {
                zerosCount++;
            }

            result = Math.max(result, zerosCount + onesCount);
        }

        return result;
    }

    public static void main(String[] args) {
        String inputString = "01101100000111111111111111110101010010101010101201010101010";
        System.out.println(MaximumScoreAfterSplittingAString.maxScoreOne(inputString));
        System.out.println(MaximumScoreAfterSplittingAString.maxScoreTwo(inputString));
        System.out.println(MaximumScoreAfterSplittingAString.maxScoreThree(inputString));
    }



}
