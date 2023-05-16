package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class MaximumProductSubArray {

    /**
     *
     * 152. Maximum Product Subarray
     *
     * Given an integer array nums, find a
     * subarray
     *  that has the largest product, and return the product.
     *
     * The test cases are generated so that the answer will fit in a 32-bit integer.
     *
     * Example 1:
     *
     * Input: nums = [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     *
     * First I thought
     * This is similar to max sum subarray using kadane's algo.
     * But failed because
     * Two negative numbers are added to make negative number but if two negative numbers are multiplied to make a positive number
     *
     * Thus, I need
     * To take care of the current product (which is maximum product now) and the previous product (which became minimum product)
     *
     * if negative number comes say -1 and suppose if current product =4 (max) and previous product = -2 (min)
     *
     * then 4 * (-1) = - 4 and -2 * (-1) = 2
     *
     * Due to negative number, minproduct becomes max and maxproduct becomes minimum.
     *
     * If I use this maxproduct (which has became minimum) then the answer will be wrong because I want maximum product to update the answer
     *
     * Thus, I need to swap the min and max products so that the minimum product will give me the maximum value and I can use this value to update the answer.
     *
     * So, technically while explaining to interviewer, you can say that this is the variation of Kadane's algo. But the difference is that for max-sum subarray we were taking care of the maxsum but here, we need to take care of maxproduct as well as minproduct because minproduct helps to eliminate the problem of (-) * (-) = (+) in the array
     *
     * TC= O(n) and SC=O(1)
     *
     * @param nums integer array provided
     * @return max product of a subarray
     */
    public int maxProduct(int[] nums) {
        int maxPro=nums[0];
        int minPro=nums[0];
        int answer = nums[0];

        for(int i =1; i<nums.length; i++){
            if(nums[i]<0){
                // if next is -1 and maxpro=4 then 4 x -1 = -4 which is minimum
                // thus swap maxPro and minPro
                int temp=maxPro;
                maxPro=minPro;
                minPro=temp;
            }
            // maxproduct
            maxPro= Math.max( maxPro*nums[i],   nums[i]);
            // minimum product
            minPro= Math.min( minPro*nums[i], nums[i]);

            answer=Math.max(answer, maxPro);

        }

        return answer;
    }

    /**
     *
     *
     * @param A int array provided
     * @return max product of a subarray
     */
    public int maxProduct2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = A[0], min = A[0], result = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max * A[i], min * A[i]), A[i]);
            min = Math.min(Math.min(temp * A[i], min * A[i]), A[i]);
            if (max > result) {
                result = max;
            }
        }
        return result;
    }

    /*
    Leetcode discus:
    https://leetcode.com/problems/maximum-product-subarray/solutions/2781113/kadane-s-algo-for-maximum-product-subarray/?envType=study-plan&id=dynamic-programming-i&languageTags=java&topicTags=dynamic-programming
     */

    public static void main(String[] args) {
        MaximumProductSubArray maximumProductSubArray = new MaximumProductSubArray();
        int[] nums = {2,3,-2,4};
        System.out.println(maximumProductSubArray.maxProduct(nums));
        System.out.println(maximumProductSubArray.maxProduct2(nums));
    }
}
