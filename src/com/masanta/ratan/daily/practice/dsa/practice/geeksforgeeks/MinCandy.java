package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

public class MinCandy {

    /*

        Problem statement:

        There are N children standing in a line. Each child is assigned a rating value given in the integer array ratings.
        You are giving candies to these children subjected to the following requirements:

        Each child must have atleast one candy.
        Children with a higher rating than its neighbors get more candies than their neighbors.
        Return the minimum number of candies you need to have to distribute.

        Example 1:

        Input:
        N = 3
        ratings = [1, 0, 2]
        Output:
        5
        Explanation:
        You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
        Example 2:

        Input:
        N = 3
        ratings = [1, 2, 2]
        Output:
        4
        Explanation:
        You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
        The third child gets 1 candy because it statisfies the above two conditions.
        Your Task:
        You don't need to read input or print anything. Your task is to complete the function minCandy() which takes the interger N and integer array ratings as parameters and returns the minimum number of candies you need to have to distribute.

        Expected Time Complexity: O(N)
        Expected Auxiliary Space: O(N)

        Constraints:
        1 ≤ N ≤ 105
        0 ≤ ratings[i] ≤ 109
     */

    static int minCandy(int N, int ratings[]) {
        // code here
        Map<Integer, Integer> candyMap = new HashMap<>();
        int[] l = new int[N];
        int[] r = new int[N];

        l[0] = 1;
        for (int i = 1; i < N; i++) {
            if (ratings[i] > ratings[i - 1]) {
                l[i] = l[i - 1] + 1;
            } else {
                l[i] = 1;
            }
        }

        r[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                r[i] = r[i + 1] + 1;
            } else {
                r[i] = 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(l[i], r[i]);
            candyMap.put(i, Math.max(l[i], r[i]));
        }

        return ans;
    }

    static int minCandy2(int N, int ratings[]) {
        int[] leftToRight = new int[N];
        int[] rightToLeft = new int[N];
        leftToRight[0] = 1;
        rightToLeft[N-1] = 1;
        int j = N-1;
        for(int i = 1; i < N; i++) {
            if(ratings[i] > ratings[i-1]) leftToRight[i] = leftToRight[i-1]+1;
            else leftToRight[i] = 1;
        }
        for(int i = N-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1]) rightToLeft[i] = rightToLeft[i+1]+1;
            else rightToLeft[i] = 1;
        }
        int cnt = 0;
        for(int i = 0; i < N; i++)
            cnt += Math.max(leftToRight[i], rightToLeft[i]);

        return cnt;
    }

    public static void main(String[] args) {
        int numberOfChildren = 10;
        int[] ratings = {1,2,2,4,1,5,6,7,0,8};
        System.out.println(MinCandy.minCandy(numberOfChildren, ratings));
        System.out.println(MinCandy.minCandy2(numberOfChildren, ratings));
    }


}
