package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MakeArrayStrictlyIncreasing {

    /*
    1187. Make Array Strictly Increasing
    Hard
    2K
    44
    Companies
    Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

    In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

    If there is no way to make arr1 strictly increasing, return -1.



    Example 1:

    Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
    Output: 1
    Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
     */

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Map<Integer, Integer> dp = new HashMap<>();
        Arrays.sort(arr2);
        int n = arr2.length;
        dp.put(-1, 0);

        for (int i = 0; i < arr1.length; i++) {
            Map<Integer, Integer> newDp = new HashMap<>();
            for (int prev : dp.keySet()) {
                if (arr1[i] > prev) {
                    newDp.put(arr1[i], Math.min(newDp.getOrDefault(arr1[i], Integer.MAX_VALUE), dp.get(prev)));
                }
                int idx = bisectRight(arr2, prev);
                if (idx < n) {
                    newDp.put(arr2[idx], Math.min(newDp.getOrDefault(arr2[idx], Integer.MAX_VALUE), 1 + dp.get(prev)));
                }
            }
            dp = newDp;
        }

        int answer = Integer.MAX_VALUE;
        for (int value : dp.values()) {
            answer = Math.min(answer, value);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static int bisectRight(int[] arr, int value) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] array1 = {1,5,3,6,7}, array2 = {4,3,1};
        MakeArrayStrictlyIncreasing makeArrayStrictlyIncreasing =
                new MakeArrayStrictlyIncreasing();
        System.out.println(makeArrayStrictlyIncreasing.makeArrayIncreasing(array1, array2));
    }

}
