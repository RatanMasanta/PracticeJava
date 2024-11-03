package com.masanta.ratan.dynamicProgramming.adityaVerma;

import java.util.Arrays;

public class SubsetSumProblem {

    static boolean[][] tp;

    void main(){
        int arr[] = {2,3,7,8,10}, sum =  11;
        int n = arr.length;
        tp = new boolean[n+1][sum+1];
        System.out.println(STR."Can \{sum} be generated from the subsets of the array \{Arrays.toString(arr)} ? \{subsetSumProblemThroughTabulation(arr, sum, n)}");

    }

    /**
     *
     * Here, we are checking the values of previous values and check if we want to take those values further or not.
     * Here, we want to check if we can take a value or not.
     * We have to skip if the weight is higher than sum required, so at that point, we will have to have the max sum possible same as the previous value in previous row.
     * We check the boolean OR of the value of previous(i-1) row's jth column and check if we can make a subset sum of the remaining sum (j - arr[i - 1]).
     * tp[i-1][j - arr[i]]
     * If we don't take this value or the value exceeds sum, we set the current possible value to previous row's value
     * tp[i][j] = tp[i-1][j]
     *
     * @param arr
     * @param sum
     * @param n
     * @return
     */
    private static boolean subsetSumProblemThroughTabulation(int[] arr, int sum, int n){
        // Initialization
        for(int i = 0; i <  n + 1; i++){
            for(int j = 0; j < sum + 1; j++){
                if(i == 0) tp[i][j] = false;
                if(j == 0) tp[i][j] = true;
            }
        }

        // code snippet
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < sum + 1; j++){
                if(arr[i - 1] <= j){
                    tp[i][j] = tp[i-1][j-arr[i-1]] || tp[i-1][j];
                } else {
                    tp[i][j] = tp[i-1][j];
                }
            }
        }

        return tp[n][sum];
    }




}
