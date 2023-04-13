package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.Stack;

public class ValidateStackSequences {

    /**
     * 946. Validate Stack Sequences
     *
     * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.
     *
     * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * Output: true
     * Explanation: We might do the following sequence:
     * push(1), push(2), push(3), push(4),
     * pop() -> 4,
     * push(5),
     * pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     *
     Approach:
     We can simulate the stack operations using a list or a stack data structure.
     We iterate through the pushed list and push each element onto the stack. For each element pushed, we check if it matches the current element to be popped from popped. If it does, we pop the element from the stack and move on to the next element in popped.
     If the stack is empty and we have iterated through all the elements in popped, then the sequences are valid.

     Intuition:
     The problem requires us to validate if a given sequence of push and pop operations on a stack is valid or not.
     We can use the fact that if an element is pushed onto the stack, it can only be popped after all the elements pushed before it have been popped.
     We can use a stack to keep track of the elements pushed onto it and simulate the pop operation by checking if the element to be popped matches the top element of the stack.
     If the sequences are valid, then all elements should have been pushed onto the stack and popped in the correct order.

     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack();

        int j = 0;
        for (int x: pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }

    public static void main(String[] args) {
        ValidateStackSequences validateStackSequences = new ValidateStackSequences();
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(validateStackSequences.validateStackSequences(pushed, popped));
    }

    /**
     *
     * https://leetcode.com/problems/validate-stack-sequences/editorial/
     *
     */

}
