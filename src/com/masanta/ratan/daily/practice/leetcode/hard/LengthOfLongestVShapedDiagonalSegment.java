//package com.masanta.ratan.daily.practice.leetcode.hard;
//
//import java.util.Arrays;
//
//public class LengthOfLongestVShapedDiagonalSegment {
//
//    /**
//     * 3459. Length of Longest V-Shaped Diagonal Segment
//     *
//     *  Rating 2530
//     * Weekly Contest 437th Q4
//     * Hard
//     * Topics
//     * premium lock icon
//     * Companies
//     * Hint
//     * You are given a 2D integer matrix grid of size n x m, where each element is either 0, 1, or 2.
//     *
//     * A V-shaped diagonal segment is defined as:
//     *
//     * The segment starts with 1.
//     * The subsequent elements follow this infinite sequence: 2, 0, 2, 0, ....
//     * The segment:
//     * Starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right).
//     * Continues the sequence in the same diagonal direction.
//     * Makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.
//     *
//     *
//     * Return the length of the longest V-shaped diagonal segment. If no valid segment exists, return 0.
//     *
//     *
//     *
//     * Example 1:
//     *
//     * Input: grid = [[2,2,1,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
//     *
//     * Output: 5
//     *
//     * Explanation:
//     *
//     *
//     *
//     * The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: (0,2) → (1,3) → (2,4), takes a 90-degree clockwise turn at (2,4), and continues as (3,3) → (4,2).
//     *
//     * Example 2:
//     *
//     * Input: grid = [[2,2,2,2,2],[2,0,2,2,0],[2,0,1,1,0],[1,0,2,2,2],[2,0,0,2,2]]
//     *
//     * Output: 4
//     *
//     * Explanation:
//     *
//     *
//     *
//     * The longest V-shaped diagonal segment has a length of 4 and follows these coordinates: (2,3) → (3,2), takes a 90-degree clockwise turn at (3,2), and continues as (2,1) → (1,0).
//     *
//     * Example 3:
//     *
//     * Input: grid = [[1,2,2,2,2],[2,2,2,2,0],[2,0,0,0,0],[0,0,2,2,2],[2,0,0,2,0]]
//     *
//     * Output: 5
//     *
//     * Explanation:
//     *
//     *
//     *
//     * The longest V-shaped diagonal segment has a length of 5 and follows these coordinates: (0,0) → (1,1) → (2,2) → (3,3) → (4,4).
//     *
//     * Example 4:
//     *
//     * Input: grid = [[1]]
//     *
//     * Output: 1
//     *
//     * Explanation:
//     *
//     * The longest V-shaped diagonal segment has a length of 1 and follows these coordinates: (0,0).
//     *
//     *
//     *
//     * Constraints:
//     *
//     * n == grid.length
//     * m == grid[i].length
//     * 1 <= n, m <= 500
//     * grid[i][j] is either 0, 1 or 2.
//     *
//     */
//
//
//    void main(){
//        int[][] grid = {{2,2,2,2,2},{2,0,2,2,0},{2,0,1,1,0},{1,0,2,2,2},{2,0,0,2,2}};
//        System.out.println(lenOfVDiagonal(grid));
//    }
//
//
//    /**
//     *  DIRS: These are the four possible diagonal directions we can move.
//     *  The order is important! It dictates how we find the next direction after a turn.
//     *  If we are currently moving in direction k, the next direction after a 90-degree clockwise turn is (k + 1) % 4.
//     *  Example: If we are moving down-right (k=0), a turn leads us up-right (k=1).
//     */
//    private static final int[][] DIRS = {
//            { 1, 1 },
//            { 1, -1 },
//            { -1, -1 },
//            { -1, 1 },
//    };
//    private int[][][][] memo;
//    private int[][] grid;
//    private int m, n;
//
//
//
//
//    /**
//     * https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment/editorial/?envType=daily-question&envId=2025-08-27#approach-memoization-search
//     *
//     * Approach: Memoization Search
//     * Intuition
//     * According to the problem statement, the definition of a V-shaped diagonal segment is as follows:
//     *
//     * The starting element of the V-shaped diagonal segment must be 1, and the subsequent elements must alternate according to the sequence [2,0,2,0,⋯]. In other words, the access sequence of elements must be [1,2,0,2,0,⋯].
//     * Starting from one diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right), and continuing along that same diagonal, it is allowed to make at most one clockwise 90
//     * ∘
//     *   turn into another diagonal direction while still maintaining the sequence pattern.
//     * There are a total of 4 diagonal directions: from the upper left to the lower right, from the upper right to the lower left, from the lower right to the upper left, and from the lower left to the upper right. The corresponding coordinate offsets are (1,1),(1,−1),(−1,−1),(−1,1). We use subscripts 0 to 3 to represent these directions. If the current direction is d and it is rotated counterclockwise by 90
//     * ∘
//     *  , then the new diagonal direction is (d+1)mod4. Careful analysis shows that once the starting position and the initial diagonal direction of a V-shaped diagonal segment are determined, the maximum possible segment length depends on the longest valid continuation from the following position. At this point, dynamic programming can be applied to compute the maximum length of a V-shaped diagonal segment starting from each point.
//     *
//     * For convenience, we use a top-down memoization search. Let dfs(x,y,direction,turn,target) represent the maximum length of a V-shaped diagonal segment starting from position (x,y), where the current diagonal direction is direction, the expected element value is target, and the current rotation state is turn. We maintain memo to record the maximum values of all substates, and initialize all states to −1 for ease of calculation. Since adjacent elements must follow the V-shaped sequence pattern, we also need to verify whether the current element’s value is valid given the previous one. This is an important detail in the search.
//     *
//     * The calculation process of dfs(x,y,direction,turn,target) is as follows:
//     *
//     * From the previous position (x,y), the next position (nx,ny) is computed using the offset corresponding to direction. We then check whether (nx,ny) is within bounds and whether grid[nx][ny] equals target. If it goes out of bounds or does not match the target, the path is invalid and we return 0.
//     *
//     * If the path continues without rotation, the next call is dfs(nx,ny,direction,turn,2−target). If the path rotates, the next call is dfs(nx,ny,(direction+1)mod4,turn,2−target). The maximum length starting from (nx,ny) is the maximum of these two cases, plus 1. Thus, the recurrence is:
//     *
//     * dfs(x,y,direction,turn,target)=max(dfs(nx,ny,direction,turn,2−target),dfs(nx,ny,(direction+1)mod4,turn,2−target))+1
//     *
//     * Since the target value of each element can be derived directly from its position relative to the previous element, we do not need to store the target in the memoization state. This simplifies the recurrence to:
//     *
//     * dfs(x,y,direction,turn)=max(dfs(nx,ny,direction,turn),dfs(nx,ny,(direction+1)mod4,turn))+1
//     *
//     * Finally, since the starting element of any valid V-shaped diagonal segment must be 1, we iterate through the grid, launch DFS from every position where the element equals 1, and compute the maximum length among all V-shaped diagonal segments.
//     *
//     */
//    public int lenOfVDiagonal(int[][] grid) {
//        this.grid = grid;
//        this.m = grid.length; // Total number of rows on the map.
//        this.n = grid[0].length; // Total number of columns on the map.
//        this.memo = new int[m][n][4][2];
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < 4; k++) {
//                    Arrays.fill(memo[i][j][k], -1);
//                }
//            }
//        }
//
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1) {
//                    for (int direction = 0; direction < 4; direction++) {
//                        res = Math.max(res, dfs(i, j, direction, true, 2) + 1);
//                    }
//                }
//            }
//        }
//        return res;
//    }
//
//    private int dfs(int cx, int cy, int direction, boolean turn, int target) {
//        int nx = cx + DIRS[direction][0];
//        int ny = cy + DIRS[direction][1];
//        /* If it goes beyond the boundary or the next node's value is not the target value, then return */
//        if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] != target) {
//            return 0;
//        }
//
//        int turnInt = turn ? 1 : 0;
//        if (memo[nx][ny][direction][turnInt] != -1) {
//            return memo[nx][ny][direction][turnInt];
//        }
//
//        /* Continue walking in the original direction. */
//        int maxStep = dfs(nx, ny, direction, turn, 2 - target);
//        if (turn) {
//            /* Clockwise rotate 90 degrees turn */
//            maxStep = Math.max(
//                    maxStep,
//                    dfs(nx, ny, (direction + 1) % 4, false, 2 - target)
//            );
//        }
//        memo[nx][ny][direction][turnInt] = maxStep + 1;
//        return maxStep + 1;
//    }
//
//    public int lenOfVDiagonalApproach2(int[][] grid) {
//        int rows = grid.length, columns = grid[0].length, max = Integer.MIN_VALUE;
//        for(int i = 0; i < rows; i++){
//            for(int j = 0; j < columns; j++){
//                if(grid[i][j] == 1){
//                    for(int  k = 0; k < DIRS.length; k++){
//                        boolean canTurn = true;
//                        max = Math.max(findLength(i,j,k, 2, grid, canTurn), max);
//                    }
//
//                }
//            }
//        }
//        return max;
//    }
//
//    private int findLength(int row, int column, int k, int val, int[][] grid, boolean canTurn) {
//        int i = row + DIRS[k][0];
//        int j = column + DIRS[k][1];
//
//        if((i >= grid.length && j > grid[0].length)|| grid[i][j] != val){
//            return 0;
//        }
//
//        int length = 0;
//        if(grid[i][j] == val){
//            length = 1 + findLength();
//        }
//
//    }
//
//
//
//}
