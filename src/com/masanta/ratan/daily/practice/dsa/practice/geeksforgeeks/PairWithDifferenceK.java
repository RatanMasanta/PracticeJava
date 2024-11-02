package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;

public class PairWithDifferenceK {

    /**
     *
     * Problem Statement:
     *
     * Given an array arr[] of positive integers. Find the number of pairs of integers whose difference equals a given number k.
     * Note: (a, b) and (b, a) are considered the same. Also, the same numbers at different indices are considered different.
     *
     * Examples:
     *
     * Input: arr[] = [1, 5, 3, 4, 2], k = 3
     * Output: 2
     * Explanation: There are 2 pairs with difference 3,the pairs are {1, 4} and {5, 2}
     * Input: arr[] = [8, 12, 16, 4, 0, 20], k = 4
     * Output: 5
     * Explanation: There are 5 pairs with difference 4, the pairs are {0, 4}, {4, 8}, {8, 12}, {12, 16} and {16, 20}.
     * Constraints:
     * 1 <= arr.size() <= 106
     * 1 <= k <= 106
     * 0 <= arr[i] <= 106
     *
     *
     *
     * Company : Adobe
     *
     */

    void main() {
        int arr[] = {8, 12, 16, 4, 0, 20}, k = 4;
        System.out.println(STR."Count pairs with difference of  \{k} in \{Arrays.toString(arr)} are \{countPairsWithDiffK(arr,k)}");
    }

    static int countPairsWithDiffK(int[] arr, int k) {
        // code here
        int ans = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int x  : arr) map.put(x,map.getOrDefault(x,0)+1);
        for(int x : arr) ans +=map.getOrDefault(x+k,0);
        return ans;

    }


}
