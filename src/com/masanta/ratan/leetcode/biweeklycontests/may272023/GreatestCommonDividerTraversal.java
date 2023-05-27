package com.masanta.ratan.leetcode.biweeklycontests.may272023;

import java.util.HashMap;
import java.util.Map;

public class GreatestCommonDividerTraversal {

    /**
     * 2709. Greatest Common Divisor Traversal
     *
     * You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.
     *
     * Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.
     *
     * Return true if it is possible to traverse between all such pairs of indices, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,3,6]
     * Output: true
     * Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
     * To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
     * To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
     *
     * Solution:
     *
     * Intuition
     * Find all prime factors of each array element and construct a graph.
     *
     * Construct an undirected graph.
     * Node: Each array element is a node.
     * Edge: If 2 array elements have at least one common prime factor, then we create an edge connecting them.
     * However, constructing the graph directly has higher time complexity. We can find all prime factors of each array element, and put the array element into the corresponding lists according to its prime factors.
     *
     * For example, 60 = 2 ^ 2 * 3 * 5
     * We put 60 in the list of 2, 3, and 5.
     * Now each pair of elements in the same list should have an edge.
     * And the key point here is for each list, we just need to create an edge between the first element and any other elements instead.
     *
     * For example, 3, 15, 60, 99 are in the list of 3.
     * We only need to create edges (3, 15), (3, 60) and (3, 99).
     *
     * The question is to ask whether the graph is connected.
     *
     * Approach
     * Construct the above graph, and check the connectivity.
     * One way to do it is to use union-find set and check whether it has only one connected component. (Using BFS/DFS is also fine.)
     *
     * Complexity
     * Time complexity:
     * O(sqrt(M) * N) where M is the largest number (considering each union-find operation takes O(1) time).
     *
     * Space complexity:
     * O(N)
     *
     * @param nums
     * @return
     */
    public boolean canTraverseAllPairs(int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return true;
        }
        int[] f = new int[n], num = new int[n];
        for (int i = 0; i < n; ++i) {
            f[i] = i;
            num[i] = 1;
        }
        Map<Integer, Integer> have = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (x == 1) {
                return false;
            }
            for (int d = 2; d * d <= x; ++d) {
                if (x % d == 0) {
                    if (have.containsKey(d)) {
                        merge(f, num, i, have.get(d));
                    } else {
                        have.put(d, i);
                    }
                    while (x % d == 0) {
                        x /= d;
                    }
                }
            }
            if (x > 1) {
                if (have.containsKey(x)) {
                    merge(f, num, i, have.get(x));
                } else {
                    have.put(x, i);
                }
            }
        }
        return num[getf(f, 0)] == n;
    }

    private int getf(int[] f, int x) {
        return f[x] == x ? x : (f[x] = getf(f, f[x]));
    }

    private void merge(int[] f, int[] num, int x, int y) {
        x = getf(f, x);
        y = getf(f, y);
        if (x == y) {
            return;
        }
        if (num[x] < num[y]) {
            int t = x;
            x = y;
            y = t;
        }
        f[y] = x;
        num[x] += num[y];
    }

    public static void main(String[] args) {

        int[] nums = {3,9,5};
        GreatestCommonDividerTraversal greatestCommonDividerTraversal =
                new GreatestCommonDividerTraversal();
        System.out.println(greatestCommonDividerTraversal.canTraverseAllPairs(nums));

    }
}
