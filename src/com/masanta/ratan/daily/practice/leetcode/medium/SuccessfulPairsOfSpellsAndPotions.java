package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class SuccessfulPairsOfSpellsAndPotions {


    /**
     * 2300. Successful Pairs of Spells and Potions
     *
     * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
     *
     * You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
     *
     * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
     *
     * Example 1:
     *
     * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
     * Output: [4,0,3]
     * Explanation:
     * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
     * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
     * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
     * Thus, [4,0,3] is returned.
     *
     * @param spells spell strength array
     * @param potions potion strength array
     * @param success minimum number for the product of ith spell and jth potion to calculate that it was a success
     * @return integer array with number of potions ith spell wil be successful
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            int left = 0;
            int right = m - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = (long) spell * potions[mid];
                if (product >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            pairs[i] = m - left;
        }
        return pairs;
    }

    /**
     *
     * Find succesful pairs with Sorting and Two Pointers Approach
     *
     * @param spells spell strength array
     * @param potions potion strength array
     * @param success minimum number for the product of ith spell and jth potion to calculate that it was a success
     * @return integer array with number of potions ith spell wil be successful
     */
    public int[] successfulPairs2(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;

        // Create an array of pairs containing spell and its original index
        int[][] sortedSpells = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedSpells[i][0] = spells[i];
            sortedSpells[i][1] = i;
        }

        // Sort the 'spells with index' and 'potions' array in increasing order
        Arrays.sort(sortedSpells, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(potions);

        // For each 'spell' find the respective 'minPotion' index
        int[] answer = new int[n];
        int potionIndex = m - 1;

        for (int[] sortedSpell : sortedSpells) {
            int spell = sortedSpell[0];
            int index = sortedSpell[1];

            while (potionIndex >= 0 && (long) spell * potions[potionIndex] >= success) {
                potionIndex -= 1;
            }
            answer[index] = m - (potionIndex + 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        SuccessfulPairsOfSpellsAndPotions successfulPairsOfSpellsAndPotions =
                new SuccessfulPairsOfSpellsAndPotions();
        int[] spells = {5,1,3};
        int[] potions = {1,2,3,4,5,6,7,8,-1,-2,-3};
        long successValue = 12;
        System.out.println(Arrays.toString(successfulPairsOfSpellsAndPotions
                .successfulPairs(spells, potions, successValue)));
        System.out.println(Arrays.toString(
                successfulPairsOfSpellsAndPotions.successfulPairs2(spells, potions, successValue)
        ));
    }

    /*
        Leetcode Editorial: https://leetcode.com/problems/successful-pairs-of-spells-and-potions/editorial/
        LeetCode Solution: https://leetcode.com/problems/successful-pairs-of-spells-and-potions/solutions/3367914/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/

     */
}
