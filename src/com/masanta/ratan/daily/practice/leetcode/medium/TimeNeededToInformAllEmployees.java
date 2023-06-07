package com.masanta.ratan.daily.practice.leetcode.medium;

import java.util.ArrayList;

public class TimeNeededToInformAllEmployees {

    /*
        1376. Time Needed to Inform All Employees
        Medium
        3.7K
        250
        Companies
        A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company is the one with headID.

        Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination relationships have a tree structure.

        The head of the company wants to inform all the company employees of an urgent piece of news. He will inform his direct subordinates, and they will inform their subordinates, and so on until all employees know about the urgent news.

        The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).

        Return the number of minutes needed to inform all the employees about the urgent news.



        Example 1:

        Input: n = 1, headID = 0, manager = [-1], informTime = [0]
        Output: 0
        Explanation: The head of the company is the only employee in the company.

     */
    int maxTime = Integer.MIN_VALUE;

    void DFS(ArrayList<ArrayList<Integer>> adjList, int[] informTime, int curr, int time) {
        // Maximum time for an employee to get the news.
        maxTime = Math.max(maxTime, time);

        for (int adjacent : adjList.get(curr)) {
            // Visit the subordinate employee who gets the news after informTime[curr] unit time.
            DFS(adjList, informTime, adjacent, time + informTime[curr]);
        }
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(n);

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        // Making an adjacent list, each index stores the Ids of subordinate employees.
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adjList.get(manager[i]).add(i);
            }
        }

        DFS(adjList, informTime, headID, 0);
        return maxTime;
    }

    public static void main(String[] args) {
        TimeNeededToInformAllEmployees timeNeededToInformAllEmployees =
                new TimeNeededToInformAllEmployees();
        int n = 6, headID = 2;
        int[] manager = {2,2,-1,2,2,2}, informTime = {0,0,1,0,0,0};
        System.out.println(timeNeededToInformAllEmployees.numOfMinutes(n, headID, manager, informTime));
    }
}
