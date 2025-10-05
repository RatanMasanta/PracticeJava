package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Arrays;

public class New21Game {
    /**
     * Alice plays the following game, loosely based on the card game "21".
     *
     * Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.
     *
     * Alice stops drawing numbers when she gets k or more points.
     *
     * Return the probability that Alice has n or fewer points.
     *
     * Answers within 10-5 of the actual answer are considered accepted.
     *
     * Example 1:
     *
     * Input: n = 10, k = 1, maxPts = 10
     * Output: 1.00000
     * Explanation: Alice gets a single card, then stops.
     * Example 2:
     *
     * Input: n = 6, k = 1, maxPts = 10
     * Output: 0.60000
     * Explanation: Alice gets a single card, then stops.
     * In 6 out of 10 possibilities, she is at or below 6 points.
     * Example 3:
     *
     * Input: n = 21, k = 17, maxPts = 10
     * Output: 0.73278
     *
     * */

    void main(){
        long start1 = System.nanoTime();
        System.out.println(new21Game(21, 17, 10));
        long end1 = System.nanoTime();
        System.out.println("Runtime for first method is: " + (end1 - start1) + "ns");
        System.out.println(new21Game(6, 1, 10));
        long start2 = System.nanoTime();
        System.out.println(new21Game2TLE(21, 17, 10));
        long end2 = System.nanoTime();
        System.out.println("Runtime for first method is: " + (end2 - start2) + "ns");
        System.out.println(new21Game2TLE(6, 1, 10));
        long start3 = System.nanoTime();
        System.out.println(new21Game2Optimized(21,17,10));
        long end3 = System.nanoTime();
        System.out.println("Runtime for first method is: " + (end3 - start3) + "ns");
        System.out.println(new21Game2Optimized(6,1,10));

    }


    /**
     *
     * Model it as DP with state = score.
     *
     * Build recurrence based on possible moves.
     *
     * Use window trick to optimize.
     *
     * Sum terminal probabilities for answer.
     *
     *
     *
     * @param n
     * @param k
     * @param maxPoints
     * @return
     */
    public double new21Game(int n, int k, int maxPoints){
        if (k == 0 || n >= k + maxPoints - 1) return 1.0;
        double[] dp = new double[n+1];
        dp[0] = 1; // if we have to find the sum 0, we will always have it, hence the probability is 1.
        double windowSum = 1; // Sum of last MaxPoints dp values
        double result = 0;

        for(int i = 1; i <= n; i++){
            /* We have to continue till the sum goes till k points,
                and the sum formula would be dp[j+x] = dp[j]/maxPoints;
                or, dp[i] = (1/maxPoints) * sum (dp[i-x])
                if(i -x >= 0 and i-x < k)

                So, we can only stop at k, k1, k2, ... n
                if( i>n), we don't count it.
                So, we take a sliding window sum:
                dp [i] = (dp[i-1] + dp[i-2] + ... + dp[i-maxPoints])/maxPoints;
                We have to keep it updated as we move it forward.
             */
            dp[i] = windowSum/maxPoints;
            if(i < k){
                windowSum += dp[i]; // Alice is still drawing cards
            } else {
                result += dp[i]; // We have stopped at i
            }

            if(i - maxPoints >= 0){
                windowSum -= dp[i - maxPoints];
            }
        }
        return result;

    }


    public double new21Game2TLE(int n, int k, int maxPoints){
        double[] dp = new double[n+1];
        // dp[i] = probability of getting i
        dp[0] = 1.0;
        for(int i = 1; i <=n; i++){
            for(int cardValue = 1; cardValue <= maxPoints; cardValue++){

                //Probability of score card value= 1/maxPoints
                // Remaining score = P[i-cardValue];

                if( (i-cardValue) >= 0 && (i-cardValue) < k){ // If we get to K, then we can't continue the game forward
                    dp[i] += dp[i-cardValue]/maxPoints;
                }
            }
        }

        // Add probability to get probability from k to n
        double result = 0.0;
        for(int i = k; i <=n ; i++){
            result += dp[i];
        }
        return result;
    }
    public double new21Game2Optimized(int n, int k, int maxPoints){
        double dp[] = new double[n+1];
        dp[0] = 1;
        double probabilitySum =  ( k == 0) ? 0 : 1;
        for(int i = 1; i <= n; i++){
            dp[i] = probabilitySum/maxPoints;
            if(i < k){
                probabilitySum += dp[i];
            }
            if(i - maxPoints >= 0 && i- maxPoints < k){
                probabilitySum -= dp[i-maxPoints];
            }
        }
        double result = 0d;
        for(int i = k; i <= n; i++){
            result += dp[i];
        }
        return result;
    }
}
