package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 2336. Smallest Number In Infinite Set
 *
 * You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
 *
 * Implement the SmallestInfiniteSet class:
 *
 * SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
 * int popSmallest() Removes and returns the smallest integer contained in the infinite set.
 * void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 *
 *
 * Example 1:
 *
 * Input
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * Output
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 *
 * Explanation
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
 * smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
 * smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
 * smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
 * smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
 * smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
 *                                    // is the smallest number, and remove it from the set.
 * smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
 * smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
 */
public class SmallestNumberInInfiniteSet {

    /*

    Approach 1: Hashset + Heap
Intuition
We have a set of all positive integers (a set will always contain unique elements). Now say this problem statement had only one method popSmallest(), then, we could have just kept one data structure (an array) and inserted 1000 numbers from 1 - 1000 in it in increasing order, and while calling this method we will just move our index pointer from left to right by 1.

But we know that the lowest positive integer is 1 and with each pop we move to the next positive integer which will be one larger than the previous, thus instead of using an additional array we can use one integer variable currentInteger initialized to 1 (denoting the smallest positive integer), and with each method call, we will return the current smallest integer and increment currentInteger by 1 (we move to the next greater positive integer).

slide1

But here we are also given one more method addBack(num), which will insert an integer back into our set (if the integer num is already present in our set then it won't do anything).

We can keep a separate space (another data structure) for re-added integers. We only need to keep only integers smaller than currentInteger in this data structure. That way, if the data structure is not empty, then the smallest available integer will surely be in it.

We need to insert some numbers and always keep track of the smallest number among them, we could use an array and sort it again and again after each insert, but it will be very inefficient. Instead, we can use a min-heap data structure here.

A min-heap is a specialized tree-based data structure that is used to efficiently maintain and retrieve the minimum element from a collection of elements. A heap is typically implemented as a binary tree, where each node in the tree represents an element in the collection.

If you are new to this data structure then we recommend you visit our Heap Explore Card for a better understanding.

But our heap will not support insertions of only unique elements (for example, if addBack is called with the same number multiple times in a row), thus we will need an additional data structure to check already inserted elements and not insert them again in the heap. We can use a hash set for this.

To summarize, we will use an integer variable currentInteger which tracks the largest integer if we do not have addBack, and a min-heap addedInteger plus a hash set isPresent to handle numbers that get added back.

Algorithm
Initialize some variables:

isPresent, a hash set to store the removed numbers added again.
addedIntegers, a min-heap priority queue to store the minimum of all added numbers on the top.
currentInteger, an integer variable initialized to 1, used to denote the current minimum value number in the set of all positive numbers.
In the popSmallest() method:

If we have any element present in the min-heap addedIntegers, then the minimum number present in it is the answer. We remove it from the min-heap and the hash set isPresent.
Otherwise, the number denoted by currentInteger is our answer, and then we increment currentInteger by 1 which denotes we removed the previous number and moved to the next number in our set of all positive numbers.
In the end, we return answer.
In the addBack(num) method:

If the 'num' is already present in our set, then we do nothing and return. This is the case if currentInteger <= num or num is in isPresent.
Otherwise, we push it into min-heap addedIntegers and hash set isPresent.
     */

    private HashSet<Integer> isPresent;
    private PriorityQueue<Integer> addedIntegers;
    private Integer currentInteger;

    public SmallestNumberInInfiniteSet() {
        isPresent = new HashSet<>();
        addedIntegers = new PriorityQueue<>();
        currentInteger = 1;
    }

    public int popSmallest() {
        int answer;
        // If there are numbers in the min-heap,
        // top element is lowest among all the available numbers.
        if (!addedIntegers.isEmpty()) {
            answer = addedIntegers.poll();
            isPresent.remove(answer);
        }
        // Otherwise, the smallest number of large positive set
        // denoted by 'currentInteger' is the answer.
        else {
            answer = currentInteger;
            currentInteger += 1;
        }
        return answer;
    }

    public void addBack(int num) {
        if (currentInteger <= num || isPresent.contains(num)) {
            return;
        }
        // We push 'num' in the min-heap if it isn't already present.
        addedIntegers.add(num);
        isPresent.add(num);
    }

    public static void main(String[] args) {
        SmallestNumberInInfiniteSet smallestNumberInInfiniteSet = new SmallestNumberInInfiniteSet();
        smallestNumberInInfiniteSet.addBack(2);
        System.out.println(smallestNumberInInfiniteSet.popSmallest());
        System.out.println(smallestNumberInInfiniteSet.popSmallest());
        System.out.println(smallestNumberInInfiniteSet.popSmallest());
        smallestNumberInInfiniteSet.addBack(1);
        System.out.println(smallestNumberInInfiniteSet.popSmallest());
        System.out.println(smallestNumberInInfiniteSet.popSmallest());
        System.out.println(smallestNumberInInfiniteSet.popSmallest());

    }
}
