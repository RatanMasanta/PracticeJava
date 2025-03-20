package com.masanta.ratan.freecodecamp.dynamicprogramming;

import java.util.*;

/**
 *
 *
 * In this class, you have to find out the best way to gather details of the minimum size array of elements which equate to the
 * target sum.
 *
 */
public class FindTheBestWayToGetTargetSum {



    void main(String[] args){
        int[] arr1 = {5,3,4,7};
        int[] arr2 = {2,3,5};
        int[] arr3 = {1,4,5};
        int[] arr4 = {1,2,5,25};

        System.out.println(Arrays.toString(findTheBestWayToGetTargetSumMemoized(7, arr1, new HashMap<Integer, int[]>())));
        System.out.println(Arrays.toString(findTheBestWayToGetTargetSumMemoized(8, arr2, new HashMap<Integer, int[]>())));
        System.out.println(Arrays.toString(findTheBestWayToGetTargetSumMemoized(8, arr3, new HashMap<Integer, int[]>())));

        System.out.println(Arrays.toString(findTheBestWayToGetTargetSumBruteForce(7, arr1)));
        System.out.println(Arrays.toString(findTheBestWayToGetTargetSumBruteForce(8, arr2)));
        System.out.println(Arrays.toString(findTheBestWayToGetTargetSumBruteForce(8, arr3)));
        //System.out.println(Arrays.toString(findTheBestWayToGetTargetSumBruteForce(100, arr4))); // Code gets stuck here
    }


    public static int[] findTheBestWayToGetTargetSumBruteForce(int targetSum, int[] arr){
        if(targetSum == 0) return new int[]{};
        if(targetSum < 0) return null;

        int[] shortestCombination = null;


        for(int num: arr){
            int remainder = targetSum - num;
            int[] remainderCombination = findTheBestWayToGetTargetSumBruteForce(remainder, arr);
            if(remainderCombination != null){
                int oldLen = remainderCombination.length;
                int[] combination = new int[oldLen + 1];
                System.arraycopy(remainderCombination, 0, combination, 0, oldLen);
                combination[combination.length - 1] = num;
                if(shortestCombination == null || combination.length < shortestCombination.length ){
                    shortestCombination = combination;
                }
            }

        }
        return shortestCombination;
    }


    public static int[] findTheBestWayToGetTargetSumMemoized(int targetSum, int[] arr, Map<Integer, int[]> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum == 0) return new int[]{};
        if (targetSum < 0) return null;

        int[] shortestCombination = null;

        for (int num : arr) {
            int remainder = targetSum - num;
            int[] remainderCombination = findTheBestWayToGetTargetSumMemoized(remainder, arr, memo);
            if (remainderCombination != null) {
                int[] combination = new int[remainderCombination.length + 1];
                System.arraycopy(remainderCombination, 0, combination, 0, remainderCombination.length);
                combination[combination.length - 1] = num;

                if (shortestCombination == null || combination.length < shortestCombination.length) {
                    shortestCombination = combination;
                }
            }
        }

        memo.put(targetSum, shortestCombination);
        return shortestCombination;
    }


}
