package com.masanta.ratan.daily.practice.leetcode.medium;

public class ZeroFilledSubArray {

    // default constructor
    public ZeroFilledSubArray(){}

    /**
     * This method uses n*(n+1)/2 to get the summation till the point we get continuos zeroes
     * and then add it up continuously
     * @param nums
     * @return
     */
    public long zeroFilledSubarrayPart2(int[] nums) {
        long numOfZeroFilledSubarray = 0;
        long countZeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                countZeros++;
            else {
                numOfZeroFilledSubarray += (countZeros * (countZeros + 1) / 2);
                countZeros = 0;
            }
        }
        numOfZeroFilledSubarray += (countZeros * (countZeros + 1) / 2);
        return numOfZeroFilledSubarray;
    }

    /**
     * This method is going to update result as we go on and it's gonna update using the logic
     *         res += ++lengthOfTrailingZeroes;
     * @param nums
     * @return
     */
    public long zeroFilledSubarray(int[] nums) {
        long res = 0;
        long length = 0;
        for(int num : nums){
            if(num == 0){
                res += ++length; // continuous zeroes will add an extra length and res will have the additional value
            } else {
                length = 0; // reset length if you get non zero number
            }
        }
        return res;
    }


    public static void main(String[] args) {
        ZeroFilledSubArray zeroFilledSubArray = new ZeroFilledSubArray();
        int[] array = {1,2,3,0,0,4,0,0,0,3,1,0,2,0,0};
        System.out.println("The number of subarrays which can be only filled with zeroes: "
                + zeroFilledSubArray.zeroFilledSubarray(array));
        System.out.println("The number of subarrays which can be only filled with zeroes: "
                + zeroFilledSubArray.zeroFilledSubarrayPart2(array));
    }
}
