package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {

    /*
        Statement:

        1637. Widest Vertical Area Between Two Points Containing No Points

        Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.

        A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.

        Note that points on the edge of a vertical area are not considered included in the area.

        Constraints:

        n == points.length
        2 <= n <= 105
        points[i].length == 2
        0 <= xi, yi <= 10^9

     */

    public static int maxWidthOfVerticalArea(int[][] points) {
        int[] xSorted = Arrays.stream(points).mapToInt(point -> point[0]).sorted().toArray();

        int maxWidth = 0;
        for (int i = 0; i < xSorted.length - 1; i++) {
            maxWidth = Math.max(maxWidth, xSorted[i + 1] - xSorted[i]);
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        int[][] points = {{3,1},{9,0},{1,0},{1,4},{5,3},{8,8}};
        System.out.println(WidestVerticalAreaBetweenTwoPointsContainingNoPoints.maxWidthOfVerticalArea(points));
    }

    /**
     *
     * Algorithm explained:
     * Approach
     * How we think about a solution
     * We need to find widest vertical area, so simply we sort x-coordinates in input array, then find the widest area.
     *
     * Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
     * We don't need y-coordinates, so take all x-coordinates only and sort them.
     *
     * [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
     * ↓
     * [3,9,1,1,5,8]
     * ↓
     * x_sorted = [1, 1, 3, 5, 8, 9]
     * Since it's sorted, the latter number should be larger. Width between two points should be
     *
     * width = (number at current index + 1) - (number at current index)
     * Let's see one by one. Number of loop should be length of x_sorted - 1 to prevent out of bounds
     *
     * index = 0
     *
     * [1, 1, 3, 5, 8, 9]
     *
     * max_width = max(max_width(= 0), 1 - 1)
     *
     * first 1 from index 1
     * second 1 from index 0
     *
     * max_width = 0
     * index = 1
     *
     * [1, 1, 3, 5, 8, 9]
     *
     * max_width = max(max_width(= 0), 3 - 1)
     *
     * 3 from index 2
     * 1 from index 1
     *
     * max_width = 2
     * index = 2
     *
     * [1, 1, 3, 5, 8, 9]
     *
     * max_width = max(max_width(= 2), 5 - 3)
     *
     * 5 from index 3
     * 3 from index 2
     *
     * max_width = 2
     * index = 3
     *
     * [1, 1, 3, 5, 8, 9]
     *
     * max_width = max(max_width(= 2), 8 - 5)
     *
     * 8 from index 4
     * 5 from index 3
     *
     * max_width = 3
     * index = 4
     *
     * [1, 1, 3, 5, 8, 9]
     *
     * max_width = max(max_width(= 3), 9 - 8)
     *
     * 9 from index 5
     * 8 from index 4
     *
     * max_width = 3
     * Output: 3
     * Easy!😄
     * Let's see solution codes.
     *
     * Complexity & Solution codes
     * Time complexity: O(nlogn)O(n log n)O(nlogn)
     * Space complexity: O(logn)O(log n)O(logn) or O(n)O(n)O(n)
     * Depends on language you use. Sorting need some extra space.
     * class Solution {
     *     public int maxWidthOfVerticalArea(int[][] points) {
     *         int[] xSorted = Arrays.stream(points).mapToInt(point -> point[0]).sorted().toArray();
     *
     *         int maxWidth = 0;
     *         for (int i = 0; i < xSorted.length - 1; i++) {
     *             maxWidth = Math.max(maxWidth, xSorted[i + 1] - xSorted[i]);
     *         }
     *
     *         return maxWidth;
     *     }
     * }
     * Step by step algorithm
     * Sorting the x-coordinates:
     *
     * x_sorted = sorted([x for x, _ in points])
     * This line creates a list x_sorted by extracting the x-coordinates from the input points and sorting them in ascending order.
     *
     * Initializing the maximum width variable:
     *
     * max_width = 0
     * Here, max_width is initialized to zero. This variable will be used to track the maximum width between consecutive x-coordinates.
     *
     * Iterating through the sorted list:
     *
     * for i in range(len(x_sorted) - 1):
     * The loop iterates through the sorted list of x-coordinates. The - 1 ensures that the loop doesn't go out of bounds, as we are comparing each element with the next one.
     *
     * Calculating and updating the maximum width:
     *
     * max_width = max(max_width, x_sorted[i + 1] - x_sorted[i])
     * Within the loop, the width between the current x-coordinate (x_sorted[i]) and the next one (x_sorted[i + 1]) is calculated. If this width is greater than the current max_width, it updates max_width to store the maximum width encountered so far.
     *
     * Returning the maximum width:
     *
     * return max_width
     * Finally, the function returns the maximum width between consecutive x-coordinates.
     *
     * In summary, the algorithm sorts the x-coordinates, iterates through the sorted list, calculates the width between consecutive x-coordinates, and keeps track of the maximum width encountered. The sorted order ensures that the latter number in each pair is larger, simplifying the width calculation. The final result is the maximum width between any two consecutive x-coordinates.
     *
     *
     */


}
