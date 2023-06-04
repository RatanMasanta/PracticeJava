package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class UniquePaths {

    /*
        62. Unique Paths
        Medium
        13.9K
        388
        Companies
        There is a robot on an m x n grid. The robot is initially located at
        the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner
        (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

        Given the two integers m and n, return the number of possible unique paths that the robot can take to
         reach the bottom-right corner.

        The test cases are generated so that the answer will be less than or equal to 2 * 109.

        Input: m = 3, n = 7
    Output: 28

     */

    /*
   Lets consider an 2d grid of length m x n where each cell represents total number of unique paths that
   are possible.

    By the problem description we can conclude that the unique paths from a particular cell
    will be the sum of unique paths of its right and down cells.
    For ex :-

    grid[r][c] = grid[r+1][c] + grid[r][c+1]
    So, we can use bottom-up approach of Dynamic Programming to solve this problem.

    Now,
    Initially the last target position grid[m-1][n-1] has value 1 as one path.
    Now we traverse through the last cell until grid[0][0] and calculate paths of each cell.
    In the end, total paths from starting position 0,0 will be dp[0][0].
   */
    public int uniquePaths(int m, int n) {
        int grid[][] = new int[m][n];
        grid[m-1][n-1] = 1;
        for(int r = m-1; r >= 0; r--){
            for(int c = n-1; c >= 0; c--){
                if(r+1 < m){
                    grid[r][c] += grid[r+1][c];
                }
                if(c+1 < n){
                    grid[r][c] += grid[r][c+1];
                }
            }
        }
        return grid[0][0];
    }

    /**
     *
     * Intuition
     * We will be solving this problem using the concept of Combination and just apply the formula to get the answer.
     *
     * Approach
     * Theory
     * Combination is the number of ways to choose a sample of r elements from a set of N distinct objects where order does not matter and replacements are not allowed.
     *
     * In our case NNN is the number of steps needed to reach the goal and rrr is the possible right moves (or) down moves
     *
     * Procedure
     * To reach the end goal the bot must take exactly m+n−2m+n-2m+n−2 number of steps.
     * N=m+n−2N= m+n-2N=m+n−2
     *
     * It can either take m-1 number of down moves or n-1 number of right moves. Let's consider he is taking m-1 number of down moves.
     * r=m−1r=m-1r=m−1
     *
     * Now we simply calculate Ncr using the following formula inside the for loop
     *
     * res=res∗(N−r+i)/ires = res * (N - r + i) / ires=res∗(N−r+i)/i where 1<=i<=r
     *
     * Example
     * Let's consider the first example where m=3 and n=7
     *
     * N = 3+7-2 = 8
     * r = 3-1 = 2
     * 8C2 = 28
     * Complexity
     * Time complexity: O(n)O(n)O(n)
     * Space complexity: O(1)O(1)O(1)
     *
     * @param m number of rows
     * @param n number of columns
     * @return number of ways to reach from one poiny to other
     */
    public int uniquePathsCombinatrics(int m, int n) {
        int N = n+m-2;
        int r = m-1;
        double res=1;
        for(int i=1;i<=r;i++){
            res = res * (N - r + i) / i;

        }
        return (int)res;

    }

    /**
     *
     * Intuition
     * The concept that is used to solve is combinatorics.
     * you have to go right or bottom till you reach grid[m-1][n-1]
     * clearly we know that you can go right only 4 times if no of columns are 5.
     * smilarly you can move to bottom only 4 times if rows are 5.
     * now you have 4 + 4 total 8 movements those are combinations of R and D you just have to find number of ways to place the R and D.
     *
     * Approach
     * Let us take an example with 5 rows and 5 columns.
     *
     *              0      1    2    3     4
     *
     *         0   curr -- R -- R -- R  -- R
     *                                     |
     *         1                           D    (m + n - 2)
     *                                     |               C
     *         2                           D                 min{m,n}-1
     *                                     |
     *         3                           D
     *                                     |
     *         4                           D
     * Above is an example which is one of the ways to destination.
     * {source -> R -> R -> R -> R -> D -> D -> D -> D -> Destination}
     * If we neglate the source and destination which are the start place and end place then total remaining places would be m + n - 2
     * Now we have m + n - 2 places we have to choose total no of R's and total no of D's and find no of ways to place them in m + n -2 places.
     * To optimize we can choose minimum of (no of R's and D's) if you place R's then automatically D would be placed.
     *
     * Complexity
     * Time complexity:
     * O(min(m,n))
     * Space complexity:
     * O(1)
     *
     * @param m number of rows
     * @param n number of columns
     * @return number of ways to reach from one poiny to other
     */
    public int uniquePathsCombinatrics2(int m, int n) {

        int total = m + n - 2;
        int r = Math.min(m,n)-1;
        double ans = 1;
        for(int i = 1;i<=r;i++) {
            ans = ans * (total-r+i)/i;
        }
        return (int)ans;
    }


    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        int m = 3, n = 7;
        System.out.println(uniquePaths.uniquePaths(m,n));
        System.out.println(uniquePaths.uniquePathsCombinatrics(m,n));
        System.out.println(uniquePaths.uniquePathsCombinatrics2(m,n));
    }
}
