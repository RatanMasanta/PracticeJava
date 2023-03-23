package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.UnionFind;

public class MinimumScoreOfPathBetweenTwoCitiesUsingUnionFind {

    /*  2492. Minimum Score of a Path Between Two Cities
        You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.

        The score of a path between two cities is defined as the minimum distance of a road in this path.

        Return the minimum possible score of a path between cities 1 and n.

        Note:

        A path is a sequence of roads between two cities.
        It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
        The test cases are generated such that there is at least one path between 1 and n.
     */

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(4);
        int[][] cityWithWeightedRoadsArray = new int[][] {{1,2,9}, {2,3,6}, {2,4,5}, {1,4,7}};
        for(int[] arrayCombo: cityWithWeightedRoadsArray){
            uf.setUnion(arrayCombo[0], arrayCombo[1], arrayCombo[2]);
        }
        System.out.println(uf.getMinWeight(1,4));
    }

    /*
        Solution: https://www.youtube.com/watch?v=pAQncVDfzSk

     */


}
