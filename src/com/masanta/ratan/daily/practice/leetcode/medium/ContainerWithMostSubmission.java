package com.masanta.ratan.daily.practice.leetcode.medium;

public class ContainerWithMostSubmission {

    void main(){
        int[] arr = new int[] {8,7,2,1};
        System.out.println("Find the max area by normal approach of while loop: " + maxArea(arr));
        System.out.println("Find the max area by sliding window approach of while loop: " + maxAreaSlidingWindow(arr));
    }

    /**
     *
     * Approach: In this solution, we have to find the max area we can make in between two height array values.
     * How do we decide which point to take?
     * We start from both sides. From left and right side, we calculate the current point and take the minimal height, and multiply with
     * the gap in between.
     * Next is the switch, we have to decide which way to move. So we check whichever value is higher, and we keep that, the other one
     * we move in the following pattern:
     *
     *   if(leftTop > rightTop){
     *      right--;
     *   } else {
     *      left++;
     *   }
     *
     * We take the max area found by this patten.
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param height array of heights for containers
     * @return maximum area of water that can be stored with the heights array
     */
    public int maxArea(int[] height) {
        // keep max area by choosing max lengths from both sides
        int left = 0, areaMax = 0, right = height.length - 1;
        while( left < right){
            int leftTop = height[left];
            int rightTop = height[right];
            int area = Math.min(leftTop, rightTop) * (right - left);
            areaMax  = Math.max(area, areaMax);
            if(leftTop > rightTop){
                right--;
            } else {
                left++;
            }
        }
        return areaMax;

    }

    /**
     *
     * Approach: We are using sliding window approach here and are calculating all possible areas. This is a naive approach.
     *
     * TC: O(n^2)
     * SC: O(1)
     * @param height
     * @return
     */
    public int maxAreaSlidingWindow(int[] height) {
        // keep max area by choosing max lengths from both sides
        int areaMax = 0;
        for(int i = 0; i < height.length - 1; i++){
            // check max area for all left
            areaMax = Math.max(areaMax, maxAreaViaSlidingWindow(i, height.length - 1, height));
        }
        return areaMax;

    }

    private int maxAreaViaSlidingWindow(int start, int end, int[] height){
        int area = 0;
        for(int i = start; i <= end; i++){
            area = Math.max(area, (i - start) * Math.min(height[start], height[i]));
        }
        return area;
    }




}
