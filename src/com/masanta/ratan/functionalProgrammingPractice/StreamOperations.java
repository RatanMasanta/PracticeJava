package com.masanta.ratan.functionalProgrammingPractice;

import java.util.List;

public class StreamOperations {

    public static void main(String[] args) {
        List<Integer> integerList = List.of(12,6,9,13,4,2,4,12,15);
        List<String> courseList = List.of("Spring","Spring Boot","API","Microservices","AWS","PCF","Azure","Docker","Kubernetes");
        printAllNumbersInListStructured(integerList);
        System.out.println("Print all the even integers in the list");
        printEvenNumbersInListStructured(integerList);
        printStringsContainingSpring(courseList);
        printCourseNameWithAtleastFourLetters(courseList);
        printSquaresOfEvenNumbers(integerList);
        printCubesOfOddNumbers(integerList);
        printNumberOfCharactersInEachCoure(courseList);
    }

    private static void printNumberOfCharactersInEachCoure(List<String> courseList) {
        courseList.stream()
                .map(courseName -> courseName + " " + courseName.length())
                .forEach(System.out::println);
    }

    private static void printCubesOfOddNumbers(List<Integer> integerList) {
        integerList.stream()
                .filter(num -> num % 2 != 0)
                .map(number -> number * number * number)
                .forEach(System.out::println);
    }

    private static void printSquaresOfEvenNumbers(List<Integer> integerList) {
        integerList.stream()
                .filter(integer -> integer % 2 == 0)
                .map(number -> number * number)
                .forEach(System.out::println);
    }

    private static void printCourseNameWithAtleastFourLetters(List<String> courseList) {
        courseList.stream()
                .filter(courseName -> courseName.length() > 3)
                .forEach(System.out::println);
    }

    private static void printStringsContainingSpring(List<String> courseList) {
        courseList.stream()
                .filter(coursename -> coursename.contains("Spring"))
                .forEach(System.out::println);
    }

    private static void printEvenNumbersInListStructured(List<Integer> integerList) {
        integerList.stream()
                .filter(x -> x % 2 == 0) //Lambda Expression
                .forEach(System.out::println);
    }

    private static void print(int number){
        System.out.println(number);
    }
    private static void printAllNumbersInListStructured(List<Integer> integers) {
        // Direct approach using sout
        integers.stream() //convert to stream
                .forEach(x -> System.out.println(x));
        // We send the value directly to the print method
        integers.stream()
                .forEach(System.out::println);
        //Class' method usage
        System.out.println("Use print private method.");
        integers.stream()
                .forEach(StreamOperations::print);//Method reference
    }
}
