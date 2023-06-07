package com.masanta.ratan.daily.practice.leetcode.medium;

public class DetonteTheMaximumBombs {

    /*
     *2101. Detonate the Maximum Bombs
        Medium
        2.4K
        124
        Companies
        You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

        The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

        You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

        Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.



        Example 1:


        Input: bombs = [[2,1,3],[6,1,4]]
        Output: 2
        Explanation:
        The above figure shows the positions and ranges of the 2 bombs.
        If we detonate the left bomb, the right bomb will not be affected.
        But if we detonate the right bomb, both bombs will be detonated.
        So the maximum bombs that can be detonated is max(1, 2) = 2.
     */

    public int maximumDetonation(int[][] bombs) {
        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            max = Math.max(max, getMaxDFS(i, bombs, new boolean[bombs.length]));
        }
        return max;
    }

    private int getMaxDFS(int index, int[][] bombs, boolean[] seen) {
        int count = 1;
        seen[index] = true;

        for (int i = 0; i < bombs.length; i++) {
            if (!seen[i] && isInRange(bombs[index], bombs[i])) {
                count += getMaxDFS(i, bombs, seen);
            }
        }

        return count;
    }

    private boolean isInRange(int[] point1, int[] point2) {
        long dx = point1[0] - point2[0], dy = point1[1] - point2[1], radius = point1[2];
        long distance = dx * dx + dy * dy;
        return distance <= radius * radius;
    }

    public static void main(String[] args) {
        int[][] bombs = {{1,1,5},{10,10,5}};
        DetonteTheMaximumBombs detonteTheMaximumBombs = new DetonteTheMaximumBombs();
        System.out.println(detonteTheMaximumBombs.maximumDetonation(bombs));
    }

}
