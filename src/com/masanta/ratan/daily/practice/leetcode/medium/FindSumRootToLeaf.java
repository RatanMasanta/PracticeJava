package com.masanta.ratan.daily.practice.leetcode.medium;

import com.masanta.ratan.daily.practice.leetcode.common.TreeNode;

public class FindSumRootToLeaf {

    private static int sum = 0;

    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.setLeft(new TreeNode(5));
        root.setRight(new TreeNode(7));
        root.getLeft().setLeft(new TreeNode(6));
        root.getRight().setLeft(new TreeNode(6));
        System.out.println(findSumRootToLeaf(root));
    }

    public static  int findSumRootToLeaf(TreeNode root){
        helper(root,"");
        return sum;
    }

    private static void helper(TreeNode root, String str){
        if(root == null) return;
        str += root.getVal();
        if(root.getLeft() == null || root.getRight() == null){
            sum += Integer.valueOf(str);
        }
        helper(root.getLeft(), str);
        helper(root.getRight(), str);
    }

}
