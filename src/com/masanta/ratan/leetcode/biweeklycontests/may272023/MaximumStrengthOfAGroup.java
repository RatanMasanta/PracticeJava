package com.masanta.ratan.leetcode.biweeklycontests.may272023;

import java.util.*;

public class MaximumStrengthOfAGroup {

    /**
     * 2708. Maximum Strength of a Group
     *
     * You are given a 0-indexed integer array nums representing the score of students in an exam. The teacher would like to form one non-empty group of students with maximal strength, where the strength of a group of students of indices i0, i1, i2, ... , ik is defined as nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​].
     *
     * Return the maximum strength of a group the teacher can create.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [3,-1,-5,2,5,-9]
     * Output: 1350
     * Explanation: One way to form a group of maximal strength is to group the students at indices [0,2,3,4,5]. Their strength is 3 * (-5) * 2 * 5 * (-9) = 1350, which we can show is optimal.
     *
     * Solution:
     *
     *
     * Intuition
     * Try to maximize the product by picking all positives elements and even number of negative elements
     *
     * Approach
     * Have Seperate arrays for positives and negatives. In order to maximize the product take even numbers of negative elements which would give the max value
     *
     * Complexity
     * Time complexity:
     * O(NLOGN) but since the constraints are very minimum it can be considered as O(1) 🙃
     *
     * Space complexity:
     * O(N)
     *
     * @param nums int array with each student's score
     * @return max strength of a group
     */
    public long maxStrength(int[] nums) {
        int n = nums.length;
        List<Long> negs = new ArrayList<>();
        List<Long> pos = new ArrayList<>();
        for (int num : nums) {
            if (num < 0)
                negs.add((long) num);
            if (num > 0)
                pos.add((long) num);
        }
        long prod = 1;
        long x = Collections.frequency(Arrays.asList(nums), 0);
        Collections.sort(negs);
        if (negs.size() <= 1 && pos.size() == 0)
            return Arrays.stream(nums).max().getAsInt();
        if (negs.size() % 2 == 0) {
            for (long num : negs)
                prod = prod * num;
            for (long num : pos)
                prod = prod * num;
            return prod;
        } else {
            for (int i = 0; i < negs.size() - 1; ++i)
                prod = prod * negs.get(i);
            for (long num : pos)
                prod = prod * num;
            return prod;
        }
    }


    /**
     *
     * Intuition
     * If we have positive numbers then for sure we need to multiply them.
     * If count of negative values is odd, we need to sure that one element will be left and it should be the smallest absolute value. For this purpose we use minHeap. We should multiply all negative values and leave only 1 val. We do it because want to have our res > 0.
     * If count of negative values is even then we multiply all of them.
     * We should not forget about edge cases: [0, -1], [0, 0, 0]
     * Complexity
     * Time complexity: O(nlogn), in the worst case when all elements are negative, should add them to minHeap and then remove from it
     * Space complexity: O(n)
     *
     *
     * @param nums int array with each student's score
     * @return max strength of a group
     */
    public long maxStrength2(int[] nums) {
        if(nums.length == 1)
            return nums[0];

        long res = 1;
        Queue<Integer> minHeap = new PriorityQueue<>();
        boolean isPos = false;
        boolean isZero = false;
        for(int num : nums) {
            if(num == 0) {
                isZero = true;
                continue;
            }

            if(num > 0) {
                isPos = true;
                res *= num;
            }
            else
                minHeap.add(num);
        }

        // for casees like [0, -1] & [0,0,0]
        if(!isPos && (minHeap.size() == 1 || minHeap.size() == 0) && isZero)
            return 0;

        if(minHeap.size() % 2 == 0) {
            while(!minHeap.isEmpty())
                res *= minHeap.poll();
        } else {
            while(minHeap.size() > 1)
                res *= minHeap.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-5,-4};
        MaximumStrengthOfAGroup maximumStrengthOfAGroup = new MaximumStrengthOfAGroup();
        System.out.println(maximumStrengthOfAGroup.maxStrength(nums));
        System.out.println(maximumStrengthOfAGroup.maxStrength2(nums));
    }

}
