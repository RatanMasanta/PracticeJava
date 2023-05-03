package com.masanta.ratan.daily.practice.leetcode.easy;

public class AverageSalaryExcludingMinimumAndMaximumSalary {

    /**
     *
     * 1491. Average Salary Excluding the Minimum and Maximum Salary
     *
     * You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
     *
     * Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.
     *
     *
     *
     * Example 1:
     *
     * Input: salary = [4000,3000,1000,2000]
     * Output: 2500.00000
     * Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
     * Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
     *
     * @param salary Salary Array
     * @return average value excluding the maxima and minima
     */
    public double average(int[] salary){
        int maxSalary = Integer.MIN_VALUE, minSalary = Integer.MAX_VALUE, sum = 0, length = salary.length;
        for(int amount: salary){
            sum += amount;
            maxSalary = Math.max(maxSalary, amount);
            minSalary = Math.min(minSalary, amount);
        }
        int amountExcludingMinAndMaxSalary = sum - minSalary - maxSalary;
        return (double) amountExcludingMinAndMaxSalary/(length - 2); // convert to double as int/int is normally not going to return int instead of double
    }

    public static void main(String[] args) {
        int[] salaryArray = {48000,59000,99000,13000,78000,45000,31000,17000,39000,37000,93000,77000,33000,28000,4000,54000,67000,6000,1000,11000};
        AverageSalaryExcludingMinimumAndMaximumSalary averageSalaryExcludingMinimumAndMaximumSalary =
                new AverageSalaryExcludingMinimumAndMaximumSalary();
        System.out.println(averageSalaryExcludingMinimumAndMaximumSalary.average(salaryArray));
    }
}
