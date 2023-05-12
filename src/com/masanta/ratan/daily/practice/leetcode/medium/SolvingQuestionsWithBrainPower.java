package com.masanta.ratan.daily.practice.leetcode.medium;

public class SolvingQuestionsWithBrainPower {

    /*Both choices affect the options on the remaining questions. This distinctive feature implies that we can use dynamic programming. */

    /**
     *
     * 2140. Solving Questions With Brainpower
     *
     * You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
     *
     * The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.
     *
     * For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
     * If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
     * If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
     * Return the maximum points you can earn for the exam.
     *
     *
     *
     * Example 1:
     *
     * Input: questions = [[3,2],[4,3],[4,4],[2,5]]
     * Output: 5
     * Explanation: The maximum points can be earned by solving questions 0 and 3.
     * - Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
     * - Unable to solve questions 1 and 2
     * - Solve question 3: Earn 2 points
     * Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.
     *
     * ITERATIVE DP
     *
     * @param questions 2-D matrix with points, brain power values in that order
     * @return maximum points which can be made from the given 2-D array
     */
    public long mostPointsIterativeDP(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        dp[n-1] = questions[n-1][0];
        for(int i = n - 2; i >=0; --i){
            dp[i] = questions[i][0];
            int skip = questions[i][1];
            if(i + skip + 1 < n){
                dp[i] += dp[i + skip + 1];
            }
            dp[i] = Math.max(dp[i],dp[i+1]);
        }
        return dp[0];
    }


    private long[] dp;

    /**
     * RECURSIVE DP
     * @param questions 2-D matrix with points, brain power values in that order
     * @return maximum points which can be made from the given 2-D array
     */
    public long mostPointsRecursiveDP(int[][] questions){
        int n = questions.length;
        dp = new long[n];
        return dfs(questions, 0);
    }

    private long dfs(int[][] questions, int i ){
        //Base Condition
        if( i >= questions.length){
            return 0;
        }
        if(dp[i] != 0){
            return dp[i];
        }
        long points = questions[i][0];
        int skip = questions[i][1];
        // Update the max value
        dp[i] = Math.max(points + dfs(questions, i + skip + 1), dfs(questions, i+ 1));
        return dp[i];
    }

    public static void main(String[] args) {
        SolvingQuestionsWithBrainPower questionsWithBrainPower = new SolvingQuestionsWithBrainPower();
        int[][] questions = {{3,2},{4,3},{4,4},{2,5}};
        System.out.println(questionsWithBrainPower.mostPointsIterativeDP(questions));
        System.out.println(questionsWithBrainPower.mostPointsRecursiveDP(questions));
    }
}
