package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SimplifyPath {

    /**
     * 71. Simplify Path
     *
     * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
     *
     * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
     *
     * The canonical path should have the following format:
     *
     * The path starts with a single slash '/'.
     * Any two directories are separated by a single slash '/'.
     * The path does not end with a trailing '/'.
     * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
     * Return the simplified canonical path.
     *
     * Intuition
     * The problem requires to convert a given absolute path to a simplified canonical path. The simplified canonical path should have the following format:
     *
     * The path starts with a single slash '/'.
     *
     * Any two directories are separated by a single slash '/'.
     *
     * The path does not end with a trailing '/'.
     *
     * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..').
     *
     * Approach
     * The problem can be solved using a stack to keep track of the directories in the path. We split the input path by slash '/', iterate over the directories, and perform the following operations:
     *
     * Ignore the current directory '.' and empty directories.
     * Go one level up for double period '..' by popping the top element from the stack if it is not empty.
     * For any other directory, push it to the stack.
     * Finally, we join the directories in the stack with slash '/' and add a slash at the beginning to form the simplified canonical path.
     * Complexity
     * Time complexity:
     * The time complexity of the algorithm is O(n)O(n)O(n), where n is the length of the input path. This is because we iterate over each directory in the path only once.
     *
     * Space complexity:
     * The space complexity of the algorithm is O(n)O(n)O(n)
     *
     * @param path absolute path provided
     * @return canonical path
     */
    public String simplifyPathUsingStack(String path) {
        Stack<String> stack = new Stack<>(); // create a stack to keep track of directories
        String[] directories = path.split("/"); // split the path by slash '/'
        for (String dir : directories) { // iterate over the directories
            if (dir.equals(".") || dir.isEmpty()) { // ignore the current directory '.' and empty directories
                continue;
            } else if (dir.equals("..")) { // go one level up for double period '..'
                if (!stack.isEmpty()) { // if stack is not empty, pop the top element
                    stack.pop();
                }
            } else { // for any other directory, push it to the stack
                stack.push(dir);
            }
        }
        return "/" + String.join("/", stack); // join the directories in the stack with slash '/' and add a slash at the beginning
    }

    public String simplifyPathUsingDeueue(String path) {
        Deque<String> dirOrFiles = new ArrayDeque<>();
        for (String dirOrFile : path.split("/")) {
            if (!dirOrFiles.isEmpty() && dirOrFile.equals("..")) {
                dirOrFiles.removeLast();
            } else if (!dirOrFile.equals(".") && !dirOrFile.equals("") && !dirOrFile.equals("..")) {
                dirOrFiles.addLast(dirOrFile);
            }
        }
        StringBuilder simplified_path = new StringBuilder();
        for (String dirOrFile : dirOrFiles) {
            simplified_path.append("/").append(dirOrFile);
        }
        return simplified_path.length() == 0 ? "/" : simplified_path.toString();
    }

    public String simplifyPathUsingTokensApproach(String path){
        Stack<String> stack = new Stack<>();
        String[] directoryArray = path.split("\\/+");
        for(String token: directoryArray){
            if(token.equals("") || token.equals(".")){
                continue;
            } else if (!stack.isEmpty() && token.equals("..")){
                stack.pop();
            } else {
                stack.push(token);
            }
        }
        String result = "";
        while(!stack.isEmpty()){
            result += "/" + stack.pop() + result;
        }
        return result;
    }


    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        String absolutePath = "/a/./b/../..///c/";
        System.out.println(simplifyPath.simplifyPathUsingStack(absolutePath));
        System.out.println(simplifyPath.simplifyPathUsingDeueue(absolutePath));
        System.out.println(simplifyPath.simplifyPathUsingTokensApproach(absolutePath));
    }

    /**
     * Leetcode solution: https://leetcode.com/problems/simplify-path/solutions/3407361/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/
     * https://leetcode.com/problems/simplify-path/solutions/3406788/image-explanation-simple-easy-concise-stack-c-java-python/
     * Youtube: https://www.youtube.com/watch?v=ZV-Hi1e1KL8
     */
}
