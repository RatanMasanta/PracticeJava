package com.masanta.ratan.daily.practice.leetcode.hard;

public class StoneGame_Three {

    private int notComputed = 1000000000;
    private int[] dp;

    private int f(int[] stoneValue, int n, int i) {
        if (i == n) {
            return 0;
        }
        if (dp[i] != notComputed) {
            return dp[i];
        }
        dp[i] = stoneValue[i] - f(stoneValue, n, i + 1);
        if (i + 2 <= n) {
            dp[i] = Math.max(dp[i], stoneValue[i]
                    + stoneValue[i + 1] - f(stoneValue, n, i + 2));
        }
        if (i + 3 <= n) {
            dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1]
                    + stoneValue[i + 2] - f(stoneValue, n, i + 3));
        }
        return dp[i];
    }

    /**
     *
     * 1406. Stone Game III
     *
     * Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
     *
     * Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.
     *
     * The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.
     *
     * The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.
     *
     * Assume Alice and Bob play optimally.
     *
     * Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.
     *
     *
     *
     * Example 1:
     *
     * Input: values = [1,2,3,7]
     * Output: "Bob"
     * Explanation: Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
     *
     * Approach 2: Top-Down Dynamic Programming (Memoization)
     * Intuition
     * In this approach, we will calculate the same DP as in the previous one, but the manner of organizing computations will differ.
     *
     * We will use the recursive function f(i)f(i)f(i) that returns the value of dp[i]\text{dp}[i]dp[i].
     *
     * The base case of the recursive function is i=ni = ni=n – the same as the base case of the DP: f(n)f(n)f(n) returns zero.
     *
     * One can rewrite the DP recurrence relation in terms of fff as follows: f(i)f(i)f(i) returns the maximum of three values: stoneValue[i]−f(i+1)\text{stoneValue}[i] - f(i + 1)stoneValue[i]−f(i+1) (take one stone), stoneValue[i]+stoneValue[i+1]−f(i+2)\text{stoneValue}[i] + \text{stoneValue}[i + 1] - f(i + 2)stoneValue[i]+stoneValue[i+1]−f(i+2) (take two stones), stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−f(i+3)\text{stoneValue}[i] + \text{stoneValue}[i + 1] + \text{stoneValue}[i + 2] - f(i + 3)stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−f(i+3) (take three stones).
     *
     * The answer to the problem is f(0)=dp[0]f(0) = \text{dp}[0]f(0)=dp[0].
     *
     * The issue here is that fff might be called (exponentially) many times for the same parameter iii.
     *
     * Each time we call, for instance, f(4)f(4)f(4), we recompute the same result for i=4i = 4i=4.
     *
     * Instead, one may keep the calculated values of f(i)f(i)f(i) in memory. We will store the same DP array as in the previous approach. In this case, the process will be as follows.
     *
     * For example, we call f(4)f(4)f(4) for the first time, calculate the result for i=4i = 4i=4, and write this result into dp[4]\text{dp}[4]dp[4]. When we call f(4)f(4)f(4) for the second time, we immediately return dp[4]\text{dp}[4]dp[4].
     *
     * In this way, we calculate the value of fff for each state (each parameter iii) only once.
     *
     * There remains one small technical question: how to know whether we call f(i)f(i)f(i) for the first time and need to compute the result, or we call it later and can return dp[i]\text{dp}[i]dp[i] found earlier? One can handle this by initializing the dp\text{dp}dp array with a huge value like 10910^910
     * 9
     *   that could not possibly occur normally. Then dp[i]=109\text{dp}[i] = 10^9dp[i]=10
     * 9
     *   will mean that we have not calculated f(i)f(i)f(i) yet. As soon as we find the result of f(i)f(i)f(i), we will write it into dp[i]\text{dp}[i]dp[i], and this value will not be 10910^910
     * 9
     *   anymore.
     *
     * Algorithm
     * The function fff takes a parameter iii.
     *
     * If i=ni = ni=n, return 000.
     * If dp[i]\text{dp}[i]dp[i] was computed previously, return dp[i]\text{dp}[i]dp[i].
     * Set dp[i]=stoneValue[i]−f(i+1)\text{dp}[i] = \text{stoneValue}[i] - f(i + 1)dp[i]=stoneValue[i]−f(i+1) (take one stone).
     * If i+2≤ni + 2 \le ni+2≤n, update dp[i]\text{dp}[i]dp[i] with stoneValue[i]+stoneValue[i+1]−f(i+2)\text{stoneValue}[i] + \text{stoneValue}[i + 1] - f(i + 2)stoneValue[i]+stoneValue[i+1]−f(i+2) (take two stones).
     * If i+3≤ni + 3 \le ni+3≤n, update dp[i]\text{dp}[i]dp[i] with stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−f(i+3)\text{stoneValue}[i] + \text{stoneValue}[i + 1] + \text{stoneValue}[i + 2] - f(i + 3)stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−f(i+3) (take three stones).
     * Return dp[i]\text{dp}[i]dp[i].
     * One needs to call f(0)f(0)f(0).
     *
     * If f(0)>0f(0) > 0f(0)>0, Alice wins.
     * If f(0)<0f(0) < 0f(0)<0, Bob wins.
     * If f(0)=0f(0) = 0f(0)=0, it is a tie.
     *
     * @param stoneValue  Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
     * @return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.
     */
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        dp = new int [n + 1];
        for (int i = 0; i < n; i++) {
            dp[i] = notComputed;
        }
        int dif = f(stoneValue, stoneValue.length, 0);
        if (dif > 0) {
            return "Alice";
        }
        if (dif < 0) {
            return "Bob";
        }
        return "Tie";
    }

    /**
     *
     * Approach 1: Bottom-Up Dynamic Programming
     * Intuition
     * Let nnn denote the number of stones in the row.
     *
     * For 0≤i≤n0 \le i \le n0≤i≤n, define dp[i]\text{dp}[i]dp[i] as follows. Consider a game with only the last n−in - in−i stones (imagine stoneValues[i]\text{stoneValues}[i]stoneValues[i] is the first stone). dp[i]\text{dp}[i]dp[i] is the first player's score minus the second player's score at the end of the game.
     *
     * The base case of this DP is dp[n]=0\text{dp}[n] = 0dp[n]=0. Since there are no stones in the game, thus the players cannot make any moves, and the difference between their scores will be zero.
     *
     * Consider now i<ni < ni<n when at least one stone is in the game. Let's call the first player X and the second one Y. Then dp[i]\text{dp}[i]dp[i] is the difference scoreX−scoreY\text{score}_\text{X} - \text{score}_\text{Y}score
     * X
     *  −score
     * Y
     *  .
     *
     * If the player X takes 111 stone (with index iii), X's score for the current move is stoneValue[i]\text{stoneValue}[i]stoneValue[i]. After that, the next state will be dp[i+1]\text{dp}[i + 1]dp[i+1], since there is one less stone in the game. However, the players exchange their roles - X becomes Y, and Y becomes X.
     * This might be confusing, so consider an example with names. Let's say X = Alice, and Y = Bob, at dp[i]\text{dp}[i]dp[i]. Alice takes one stone, and now we move to dp[i+1]\text{dp}[i + 1]dp[i+1]. However, now it's Bob's turn. We defined X as the player who takes the first turn, and in this new state dp[i+1]\text{dp}[i + 1]dp[i+1], Bob is moving first. Therefore, X is now Bob, and Y is now Alice.
     *
     * Remember, we defined dp[i]\text{dp}[i]dp[i] as scoreX−scoreY\text{score}_\text{X} - \text{score}_\text{Y}score
     * X
     *  −score
     * Y
     *  . Thus, dp[i+1]\text{dp}[i + 1]dp[i+1] is actually the future value of scoreY−scoreX\text{score}_\text{Y} - \text{score}_\text{X}score
     * Y
     *  −score
     * X
     *   from the "perspective" of dp[i]\text{dp}[i]dp[i], since X and Y have swapped.
     * Thus, if player X only takes 111 stone, then it will result in a score difference of stoneValue[i]−dp[i+1]\text{stoneValue}[i] - \text{dp}[i + 1]stoneValue[i]−dp[i+1]. The minus is to flip scoreY−scoreX\text{score}_\text{Y} - \text{score}_\text{X}score
     * Y
     *  −score
     * X
     *   into scoreX−scoreY\text{score}_\text{X} - \text{score}_\text{Y}score
     * X
     *  −score
     * Y
     *  .
     * Similarly, if X takes two stones (with indices iii and i+1i + 1i+1), the difference scoreX−scoreY\text{score}_\text{X} - \text{score}_\text{Y}score
     * X
     *  −score
     * Y
     *   will be stoneValue[i]+stoneValue[i+1]−dp[i+2]\text{stoneValue}[i] + \text{stoneValue}[i + 1] - \text{dp}[i + 2]stoneValue[i]+stoneValue[i+1]−dp[i+2].
     * Finally, if X takes three stones, the difference will be stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−dp[i+3]\text{stoneValue}[i] + \text{stoneValue}[i + 1] + \text{stoneValue}[i + 2] - \text{dp}[i + 3]stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−dp[i+3].
     * If you're confused about this logic, think about it like this.
     *
     * dp[i]\text{dp}[i]dp[i] is some number that the first player wants to maximize.
     *
     * After the first player moves, the second player moves. Therefore the next dp\text{dp}dp state will be some number the second player wants to maximize.
     *
     * Since both players try to maximize these values, we can think of these values as their scores.
     *
     * Because one player's gain is the other player's loss, we need to subtract the next dp\text{dp}dp state. It is because the next dp\text{dp}dp state represents the other player's "score", and their gain is our loss.
     *
     * Since X plays optimally, they will choose the option that maximizes the difference scoreX−scoreY\text{score}_\text{X} - \text{score}_\text{Y}score
     * X
     *  −score
     * Y
     *  . It implies that dp[i]\text{dp}[i]dp[i] is the maximum of the above three values. We will try all three.
     *
     * Having all DP values computed, one can answer who wins using only dp[0]\text{dp}[0]dp[0], which is the score difference in the game with all nnn stones present. Since Alice is the first player, this value being positive means Alice wins.
     *
     * Algorithm
     * Let nnn be the number of stones.
     * Declare the array dp\text{dp}dp of size n+1n + 1n+1.
     * Set dp[n]=0\text{dp}[n] = 0dp[n]=0. (The base case of the DP).
     * Iterate iii from n−1n - 1n−1 to 000.
     * Set dp[i]=stoneValue[i]−dp[i+1]\text{dp}[i] = \text{stoneValue}[i] - \text{dp}[i + 1]dp[i]=stoneValue[i]−dp[i+1] (take one stone).
     * If i+2≤ni + 2 \le ni+2≤n, update dp[i]\text{dp}[i]dp[i] with stoneValue[i]+stoneValue[i+1]−dp[i+2]\text{stoneValue}[i] + \text{stoneValue}[i + 1] - \text{dp}[i + 2]stoneValue[i]+stoneValue[i+1]−dp[i+2] (take two stones) if it's larger.
     * If i+3≤ni + 3 \le ni+3≤n, update dp[i]\text{dp}[i]dp[i] with stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−dp[i+3]\text{stoneValue}[i] + \text{stoneValue}[i + 1] + \text{stoneValue}[i + 2] - \text{dp}[i + 3]stoneValue[i]+stoneValue[i+1]+stoneValue[i+2]−dp[i+3] (take three stones) if it's larger.
     * If dp[0]>0\text{dp}[0] > 0dp[0]>0, Alice wins.
     * If dp[0]<0\text{dp}[0] < 0dp[0]<0, Bob wins.
     * If dp[0]=0\text{dp}[0] = 0dp[0]=0, it is a tie.
     *
     *
     * @param stoneValue  Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
     * @return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.
     */
    public String stoneGameIIITwo(int[] stoneValue) {
         int n = stoneValue.length;
         int[] dp = new int [n + 1];
         for (int i = n - 1; i >= 0; i--) {
             dp[i] = stoneValue[i] - dp[i + 1];
             if (i + 2 <= n) {
                 dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
             }
             if (i + 3 <= n) {
                 dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
             }
         }
         if (dp[0] > 0) {
             return "Alice";
         }
         if (dp[0] < 0) {
             return "Bob";
         }
         return "Tie";
     }

}
