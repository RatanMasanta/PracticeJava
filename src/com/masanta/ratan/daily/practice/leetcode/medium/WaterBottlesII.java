package com.masanta.ratan.daily.practice.leetcode.medium;

public class WaterBottlesII {



    void main(){
        System.out.println(maxBottlesDrunk(13,6));
        System.out.println(maxBottlesDrunk(10,3));
        System.out.println(maxBottlesDrunk2(13,6));
        System.out.println(maxBottlesDrunk2(10,3));
    }


    public int maxBottlesDrunk(int numBottles, int numExchange) {

        int bottlesDrunk = numBottles;   // drink all initial bottles
        int emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            // exchange
            emptyBottles -= numExchange;
            numExchange++;

            // drink the new bottle
            bottlesDrunk++;
            emptyBottles++;
        }

        return bottlesDrunk;
    }


    public int maxBottlesDrunk2(int numBottles, int numExchange) {
        // max out the number of bottles we drink
        // After our exchange numExchange increases

        int currentFullBottles = numBottles, currentEmptyBottles = 0, bottleDrunk = 0;

        /// strp 1: drink all bottles
        bottleDrunk += numBottles;
        currentEmptyBottles = numBottles;
        currentFullBottles = 0;
        return exchangeBottles(currentEmptyBottles, currentFullBottles, numExchange, bottleDrunk);
    }

    private int exchangeBottles( int currentEmptyBottles, int currentFullBottles, int numExchange, int bottleDrunk ){
        if(numExchange > currentEmptyBottles && currentFullBottles == 0 ){
            return bottleDrunk;
        }
        while(currentEmptyBottles >= numExchange){
            currentFullBottles += 1;
            currentEmptyBottles -= numExchange;
            numExchange++;

        }
        bottleDrunk += currentFullBottles;
        currentEmptyBottles += currentFullBottles;
        currentFullBottles = 0;

        return exchangeBottles(currentEmptyBottles, currentFullBottles, numExchange, bottleDrunk);

    }

}
