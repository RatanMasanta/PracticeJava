package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks.dsa.course;

public class ExactlyThreeDivisors {


    public static int exactly3Divisors(int N){
        int count=0;
        for(int i=2;i<=Math.sqrt(N);i++){
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }
    public static boolean isPrime(int i){
        if(i==2||i==3){
            return true;
        }
        if(i%2==0||i%3==0){
            return false;
        }
        for(int j=5;j*j<=i;j=j+6){
            if(i%j==0||i%(j+2)==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(ExactlyThreeDivisors.exactly3Divisors(15));
    }
}
