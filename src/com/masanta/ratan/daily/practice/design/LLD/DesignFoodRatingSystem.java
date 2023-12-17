package com.masanta.ratan.daily.practice.design.LLD;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DesignFoodRatingSystem {

    /*
    Design a food rating system that can do the following:

    Modify the rating of a food item listed in the system.
    Return the highest-rated food item for a type of cuisine in the system.
    Implement the FoodRatings class:

    FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
    foods[i] is the name of the ith food,
    cuisines[i] is the type of cuisine of the ith food, and
    ratings[i] is the initial rating of the ith food.
    void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
    String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
    Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.



    Example 1:

    Input
    ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
    [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
    Output
    [null, "kimchi", "ramen", null, "sushi", null, "ramen"]

    Explanation
    FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
    foodRatings.highestRated("korean"); // return "kimchi"
                                        // "kimchi" is the highest rated korean food with a rating of 9.
    foodRatings.highestRated("japanese"); // return "ramen"
                                          // "ramen" is the highest rated japanese food with a rating of 14.
    foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
    foodRatings.highestRated("japanese"); // return "sushi"
                                          // "sushi" is the highest rated japanese food with a rating of 16.
    foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
    foodRatings.highestRated("japanese"); // return "ramen"
                                          // Both "sushi" and "ramen" have a rating of 16.
                                          // However, "ramen" is lexicographically smaller than "sushi".


     */

    public class FoodInfo {
        String food;
        String cuisine;
        int rating;
        public FoodInfo(String food, String cuisine, int rating){
            this.food=food;
            this.cuisine=cuisine;
            this.rating=rating;
        }
    }
    private Map<String, PriorityQueue<FoodInfo>> cuisineMap;
    private Map<String, FoodInfo> foodMap;


    public DesignFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        cuisineMap=new HashMap<>();
        foodMap=new HashMap<>();
        for(int i=0; i<foods.length; i++){
            FoodInfo combo=new FoodInfo(foods[i],cuisines[i],ratings[i]);
            foodMap.put(foods[i],combo);
            if(cuisineMap.containsKey(cuisines[i])){
                cuisineMap.get(cuisines[i]).add(combo);
            }
            else{
                PriorityQueue<FoodInfo> pq=new PriorityQueue<FoodInfo>(new Comparator<FoodInfo>(){
                    @Override
                    public int compare(FoodInfo a, FoodInfo b){
                        int result=b.rating-a.rating;
                        if(result==0){
                            return (a.food).compareTo(b.food);
                        }
                        return result;
                    }
                });
                pq.add(combo);
                cuisineMap.put(cuisines[i],pq);
            }
        }
    }

    public void changeRating(String food, int newRating) {
        FoodInfo prev=foodMap.get(food);
        FoodInfo curr= new FoodInfo(food,prev.cuisine,newRating);
        foodMap.put(food,curr);
        prev.food="";
        cuisineMap.get(prev.cuisine).add(curr);



    }

    public String highestRated(String cuisine) {
        while( cuisineMap.get(cuisine).peek().food.equals("")){
            cuisineMap.get(cuisine).remove();
        }
        return cuisineMap.get(cuisine).peek().food;

    }


    // Run the class
    public static void main(String[] args) {
        String[] foodArray = new String[] {"Pizza", "Biryani", "Pasta", "Chicken Kosha", "Aaloo Postu"};
        String[] cuisineArray = new String[] {"European", "Indian", "European", "Indian", "Indian"};
        int[] foodRatingArray = new int[] {5,3,4,2,1};
        DesignFoodRatingSystem designFoodRatingSystem = new DesignFoodRatingSystem(foodArray, cuisineArray, foodRatingArray);
        System.out.println(designFoodRatingSystem.highestRated("Indian"));
        designFoodRatingSystem.changeRating("Pasta", 5);
        designFoodRatingSystem.changeRating("Pizza", 4);
        System.out.println(designFoodRatingSystem.highestRated("European"));
    }



}
