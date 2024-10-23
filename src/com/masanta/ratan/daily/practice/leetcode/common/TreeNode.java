package com.masanta.ratan.daily.practice.leetcode.common;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
      private int val;
      TreeNode left;
      TreeNode right;
      public TreeNode() {}
      public TreeNode(int val) { this.val = val; }
      public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

      public int getVal() {
          return val;
      }

      public void setVal(int val) {
          this.val = val;
      }

      public TreeNode getLeft() {
          return left;
      }

      public void setLeft(TreeNode left) {
          this.left = left;
      }

      public TreeNode getRight() {
          return right;
      }

      public void setRight(TreeNode right) {
          this.right = right;
      }

      @Override
      public String toString(){
          StringBuilder sb = new StringBuilder();
          sb.append(val);
          if (left != null) {
              sb.append(" (L: ").append(left.toString()).append(")");
          }
          if (right != null) {
              sb.append(" (R: ").append(right.toString()).append(")");
          }
          return sb.toString();
      }



  }
