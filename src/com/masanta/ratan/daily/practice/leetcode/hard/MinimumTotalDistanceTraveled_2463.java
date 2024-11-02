package com.masanta.ratan.daily.practice.leetcode.hard;

import java.util.*;

public class MinimumTotalDistanceTraveled_2463 {

    /**
     *
     * Problem Statement:
     *
     * There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj] indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj robots.
     *
     * The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be in the same position as a factory initially.
     *
     * All the robots are initially broken; they keep moving in one direction. The direction could be the negative or the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory repairs the robot, and it stops moving.
     *
     * At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total distance traveled by all the robots.
     *
     * Return the minimum total distance traveled by all the robots. The test cases are generated such that all the robots can be repaired.
     *
     * Note that
     *
     * All robots move at the same speed.
     * If two robots move in the same direction, they will never collide.
     * If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
     * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
     * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
     *
     *
     * Example 1:
     *
     *
     * Input: robot = [0,4,6], factory = [[2,2],[6,2]]
     * Output: 4
     * Explanation: As shown in the figure:
     * - The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
     * - The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
     * - The third robot at position 6 will be repaired at the second factory. It does not need to move.
     * The limit of the first factory is 2, and it fixed 2 robots.
     * The limit of the second factory is 2, and it fixed 1 robot.
     * The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
     * Example 2:
     *
     *
     * Input: robot = [1,-1], factory = [[-2,1],[2,1]]
     * Output: 2
     * Explanation: As shown in the figure:
     * - The first robot at position 1 moves in the positive direction. It will be repaired at the second factory.
     * - The second robot at position -1 moves in the negative direction. It will be repaired at the first factory.
     * The limit of the first factory is 1, and it fixed 1 robot.
     * The limit of the second factory is 1, and it fixed 1 robot.
     * The total distance is |2 - 1| + |(-2) - (-1)| = 2. It can be shown that we cannot achieve a better total distance than 2.
     *
     *
     * Constraints:
     *
     * 1 <= robot.length, factory.length <= 100
     * factory[j].length == 2
     * -109 <= robot[i], positionj <= 109
     * 0 <= limitj <= robot.length
     * The input will be generated such that it is always possible to repair every robot.
     */

    void main() {
        List<Integer> robot = new ArrayList<>();
        robot.add(0);
        robot.add(4);
        robot.add(6);
        int[][] factory = {{2,2},{6,2}};
        System.out.println(STR." With robots at \{robot}, and factories at: {2,2},{6,2}, minimum total distance travelled: \{minimumTotalDistance(robot,factory)}");
    }


    /**
     *
     * SOlution: https://leetcode.com/problems/minimum-total-distance-traveled/editorial/?envType=daily-question&envId=2024-11-02
     *
     * @param robot
     * @param factory
     * @return
     */
     private static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Sort robots and factories by position for optimal assignment
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        int m = robot.size();
        int n = factory.length;

        // dp[i][j] represents min total distance for robots[i:] using factories[j:]
        long[][] dp = new long[m + 1][n + 1];

        // Set last column to MAX_VALUE as boundary condition
        for (int i = 0; i < m; i++) {
            dp[i][n] = Long.MAX_VALUE;
        }

        // Process each factory from right to left
        for (int j = n - 1; j >= 0; j--) {
            // Track cumulative distance from current factory to robots
            long prefix = 0;
            // Use deque to maintain potential optimal assignments
            Deque<Pair<Integer, Long>> qq = new ArrayDeque<>();
            // Initialize with boundary condition
            qq.offer(new Pair<>(m, 0L));

            // Process each robot from right to left
            for (int i = m - 1; i >= 0; i--) {
                // Add distance from current robot to current factory
                prefix += Math.abs(robot.get(i) - factory[j][0]);

                // Remove assignments that exceed factory capacity
                while (!qq.isEmpty() && qq.peekFirst().getKey() > i + factory[j][1]) {
                    qq.pollFirst();
                }

                // Remove suboptimal assignments
                while (!qq.isEmpty() && qq.peekLast().getValue() >= dp[i][j + 1] - prefix) {
                    qq.pollLast();
                }

                // Add current state to deque
                qq.offerLast(new Pair<>(i, dp[i][j + 1] - prefix));
                // Update dp with optimal assignment
                dp[i][j] = qq.peekFirst().getValue() + prefix;
            }
        }

        // Return minimum total distance for all robots
        return dp[0][0];
    }

    // Helper class to store key-value pairs
    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }



}
