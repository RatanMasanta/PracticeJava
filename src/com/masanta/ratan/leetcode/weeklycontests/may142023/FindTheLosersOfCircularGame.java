package com.masanta.ratan.leetcode.weeklycontests.may142023;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheLosersOfCircularGame {

    /**
     *
     * 2682. Find the Losers of the Circular Game
     *
     * There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.
     *
     * The rules of the game are as follows:
     *
     * 1st friend receives the ball.
     *
     * After that, 1st friend passes it to the friend who is k steps away from them in the clockwise direction.
     * After that, the friend who receives the ball should pass it to the friend who is 2 * k steps away from them in the clockwise direction.
     * After that, the friend who receives the ball should pass it to the friend who is 3 * k steps away from them in the clockwise direction, and so on and so forth.
     * In other words, on the ith turn, the friend holding the ball should pass it to the friend who is i * k steps away from them in the clockwise direction.
     *
     * The game is finished when some friend receives the ball for the second time.
     *
     * The losers of the game are friends who did not receive the ball in the entire game.
     *
     * Given the number of friends, n, and an integer k, return the array answer, which contains the losers of the game in the ascending order.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 5, k = 2
     * Output: [4,5]
     * Explanation: The game goes as follows:
     * 1) Start at 1st friend and pass the ball to the friend who is 2 steps away from them - 3rd friend.
     * 2) 3rd friend passes the ball to the friend who is 4 steps away from them - 2nd friend.
     * 3) 2nd friend passes the ball to the friend who is 6 steps away from them  - 3rd friend.
     * 4) The game ends as 3rd friend receives the ball for the second time.
     * Example 2:
     *
     * Input: n = 4, k = 4
     * Output: [2,3,4]
     * Explanation: The game goes as follows:
     * 1) Start at the 1st friend and pass the ball to the friend who is 4 steps away from them - 1st friend.
     * 2) The game ends as 1st friend receives the ball for the second time.
     *
     * Approach
     * Take HashSet and all all the elements from 0 -> n-1.
     *
     * When an element is visited remove it from the HashSet.
     *
     * Do this until you find an element which is not present in HashSet.
     *
     * Rest all the remaining elements are the answerts.
     *
     * At the end convert 0 based indexing to 1 based by adding 1 ( 0 -> n-1 => 1 -> n).
     *
     * Continue to increase factor by 1 so that next increment can be calculated.
     * nextIndex = 1 * k
     * nextIndex = 2 * k
     * nextIndex = 3 * k
     * nextIndex = 4 * k and so on
     *
     * to make the array circular just use modulus operator while calculating next index.
     * ((location + (k * factor)) % n);
     *
     * Since this is 0 based indexing while generating the answer make it 1 by adding 1 to the solution.
     *
     * @param n number of players
     * @param k step incremental value
     * @return list of integers which weren't passed the ball
     */
    public int[] circularGameLosers(int n, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<n; i++) {
            set.add(i);
        }

        int location = 0;
        int factor = 1;
        while (set.contains(location)) {
            set.remove(location);
            location = ((location + (k * factor)) % n);
            factor += 1;
        }

        int[] result = new int[set.size()];
        int index=0;
        for (int num : set) {
            result[index++] = num + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 10;
        int n1 = 4;
        int k1 = 3;
        int k2 = 4;
        FindTheLosersOfCircularGame losersOfCircularGame = new FindTheLosersOfCircularGame();
        System.out.println(Arrays.toString(losersOfCircularGame.circularGameLosers(n,k1)));
        System.out.println(Arrays.toString(losersOfCircularGame.circularGameLosers(n1, k2)));
    }
}
