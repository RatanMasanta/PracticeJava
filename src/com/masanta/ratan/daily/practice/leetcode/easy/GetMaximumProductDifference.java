package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.Arrays;

public class GetMaximumProductDifference {

    public int maxProductDifferenceOne(int[] nums) {
        //O(n log(n)) solution
        Arrays.sort(nums);
        int len = nums.length;
        return (nums[len - 1] * nums[len-2] - nums[0]*nums[1]);
    }

    public int maxProductDifferenceTwo(int[] nums) {

        //O(n) solution
        int firstBig = 0, secondBig = 0;
        int firstSmall = Integer.MAX_VALUE, secondSmall = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n < firstSmall) {
                secondSmall = firstSmall;
                firstSmall = n;
            } else if (n < secondSmall) {
                secondSmall = n;
            }

            if (n > firstBig) {
                secondBig = firstBig;
                firstBig = n;
            } else if (n > secondBig) {
                secondBig = n;
            }
        }

        return firstBig * secondBig - firstSmall * secondSmall;
        }

    public int maxProductDifferenceThree(int[] nums) {
        int[] cloneNums = Arrays.copyOf(nums, nums.length);
        int[] fmaxmin = getMaxMin(cloneNums);
        int[] smaxmin = getMaxMin(cloneNums);

        return fmaxmin[0] * smaxmin[0] - fmaxmin[1] * smaxmin[1];
}

    private int[] getMaxMin(int[] nums) {
        int len = nums.length;
        int maxi = 0, mini = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxi = i;
            }
            if (nums[i] < min && nums[i] != Integer.MIN_VALUE) {
                min = nums[i];
                mini = i;
            }
        }
        nums[maxi] = Integer.MIN_VALUE;
        nums[mini] = Integer.MIN_VALUE;
        return new int[] {max, min};
    }

    public static void main(String[] args) {
        GetMaximumProductDifference getMaximumProductDifference = new GetMaximumProductDifference();
        int[] nums = {4,2,5,9,7,4,8};
        System.out.println(getMaximumProductDifference.maxProductDifferenceOne(nums));
        System.out.println(getMaximumProductDifference.maxProductDifferenceTwo(nums));
        System.out.println(getMaximumProductDifference.maxProductDifferenceThree(nums));
    }

}
