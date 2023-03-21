package com.masanta.ratan.daily.practice.corejava.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PracticeStream {

    private static int[] array1 = {1,2,3,4,5,6,7,8,9,10,1,3,12,15};
    private static List numList = new ArrayList<>();


    public static void main(String[] args) {
        numList = Arrays.asList(array1);
        List<Integer> sampleList = new ArrayList<Integer>();
        sampleList.add(10);
        sampleList.add(15);
        sampleList.add(20);
        sampleList.add(30);
        System.out.println(Arrays.stream(array1).filter(x -> x <6).count());
        System.out.println(Arrays.stream(array1).max().getAsInt());
        System.out.println(Arrays.stream(array1).min().getAsInt());
        System.out.println(Arrays.stream(array1).allMatch(x -> x > 0));
        System.out.println(Arrays.stream(array1).boxed());
        List<Integer> filteredList = sampleList.stream()
                .filter(i -> i >= 15)
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(filteredList.toArray()));
        System.out.println(filteredList.stream().count());
        List<Integer> descOrderList = filteredList.stream().
                sorted((i1, i2) -> i2.compareTo(i1)).
                collect(Collectors.toList());
        System.out.println(Arrays.toString(descOrderList.toArray()));
        Stream sampleStream = Stream.of(1,11,111,11,1111,1111111);
    }

    /*

    // Accumulate names into a List
 List<String> list = people.stream()
   .map(Person::getName)
   .collect(Collectors.toList());

 // Accumulate names into a TreeSet
 Set<String> set = people.stream()
   .map(Person::getName)
   .collect(Collectors.toCollection(TreeSet::new));

 // Convert elements to strings and concatenate them, separated by commas
 String joined = things.stream()
   .map(Object::toString)
   .collect(Collectors.joining(", "));

 // Compute sum of salaries of employee
 int total = employees.stream()
   .collect(Collectors.summingInt(Employee::getSalary));

 // Group employees by department
 Map<Department, List<Employee>> byDept = employees.stream()
   .collect(Collectors.groupingBy(Employee::getDepartment));

 // Compute sum of salaries by department
 Map<Department, Integer> totalByDept = employees.stream()
   .collect(Collectors.groupingBy(Employee::getDepartment,
                                  Collectors.summingInt(Employee::getSalary)));

 // Partition students into passing and failing
 Map<Boolean, List<Student>> passingFailing = students.stream()
   .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));

     */
}

class Person {
    int age;
    double weight;
    long social_id;
    BigDecimal netWorth;
    String bloodType;


}