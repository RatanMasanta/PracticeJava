package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;

public class AlternateSorting {

    /**
     * Problem Statement:
     *
     * Given an array arr of distinct integers. Rearrange the array in such a way that the first element is the largest and the second element is the smallest, the third element is the second largest and the fourth element is the second smallest, and so on.
     *
     * Examples:
     *
     * Input: arr[] = [7, 1, 2, 3, 4, 5, 6]
     * Output: [7, 1, 6, 2, 5, 3, 4]
     * Explanation: The first element is first maximum and second element is first minimum and so on.
     * Input: arr[] = [1, 6, 9, 4, 3, 7, 8, 2]
     * Output: [9, 1, 8, 2, 7, 3, 6, 4]
     * Explanation: The first element is first maximum and second element is first minimum and so on.
     * Expected Time Complexity: O(nlogn).
     * Expected Auxiliary Space: O(n).
     *
     * Constraints:
     * 1 ≤ arr.size() ≤ 105
     * 1 ≤ arr[i] ≤ 105
     *
     */




    public static void main(String[] args) {
        int arr[] = new int []  {7, 1, 2, 3, 4, 5, 6};
        System.out.println(STR."The alternate sorting results for array \{Arrays.toString(arr)} are: \{alternateSort(arr)}");
    }

    /**
     * We sort the array and do a counter wise swap from left to right stopping when we get to middle.
     * @param arr
     * @return
     */
    public static ArrayList<Integer> alternateSort(int[] arr) {

        // Your code goes here
        ArrayList<Integer> list = new ArrayList<>();
        int n = arr.length;
        Arrays.sort(arr);
        int left=0;
        int right = n-1;
        while(left <= right){
            if(left == right){
                list.add(arr[left++]);
                return list;
            }
            list.add(arr[right--]);
            list.add(arr[left++]);
        }
        return list;

    }

}
