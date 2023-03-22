package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.*;

public class MinimumScoreOfPathBetweenTwoCities {

    public int minScore(int n, int[][] roads) {
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for(
    int[] road :roads)

    {
        int u = road[0], v = road[1], w = road[2];
        graph.computeIfAbsent(u, k -> new HashMap<>()).put(v, w);
        graph.computeIfAbsent(v, k -> new HashMap<>()).put(u, w);
    }

    int res = Integer.MAX_VALUE;
    Set<Integer> visited = new HashSet<>();
    Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while(!queue.isEmpty())

    {
        int node = queue.poll();
        for (Map.Entry<Integer, Integer> entry : graph.get(node).entrySet()) {
            int adj = entry.getKey(), score = entry.getValue();
            if (!visited.contains(adj)) {
                queue.offer(adj);
                visited.add(adj);
            }
            res = Math.min(res, score);
        }
    }

        return res;
}

    public static void main(String[] args) {
        int[][] roads = {{1,2,9}, {2,3,6}, {2,4,5}, {1,4,7}};
        int n = 4;
        MinimumScoreOfPathBetweenTwoCities minimumScoreOfPathBetweenTwoCities = new MinimumScoreOfPathBetweenTwoCities();
        System.out.println(minimumScoreOfPathBetweenTwoCities.minScore(n, roads));
    }

    /*
        https://www.youtube.com/watch?v=rlJXKEVjeC8
     */


}
