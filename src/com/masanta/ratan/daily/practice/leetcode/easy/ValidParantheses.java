package com.masanta.ratan.daily.practice.leetcode.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParantheses {

    /**
     * 20. Valid Parentheses
     *
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     *
     * @param paranthesisString input string parantheses
     * @return if the string is a valid parantheses or not
     */
    public boolean checkValidParantheses(String paranthesisString){
        Map<Character, Character> charMap = new HashMap<Character, Character>();
        charMap.put(')','(');
        charMap.put('}','{');
        charMap.put(']','[');
        Stack<Character> stackChar = new Stack<Character>();
        char[] paranthesisCharArray = paranthesisString.toCharArray();
        for(char charVal: paranthesisCharArray){
            if(charMap.containsKey(charVal)){
                if(!stackChar.isEmpty() && stackChar.peek().equals(charMap.get(charVal))){
                    stackChar.pop();
                } else {
                    return false;
                }
            } else {
                stackChar.push(charVal);
            }
        }
        return stackChar.isEmpty();
    }

    public static void main(String[] args) {
        ValidParantheses validParantheses = new ValidParantheses();
        String paranthesisString = "()[]{}";
        System.out.println(validParantheses.checkValidParantheses(paranthesisString));
    }

}
