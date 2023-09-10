package com.masanta.ratan.companyInterviewQuestions;

import java.util.HashMap;
import java.util.Map;

public class LTIMindtreeSolution {

    public static void main(String[] args) {
        String s1 = "Ratan123456 Masanta Ratan123456 Masantaxyz1";
        String [] words  = s1.split(" ");
        Map<String, Integer> wordLength = new HashMap<String, Integer>();
        int maxLength = Integer.MIN_VALUE;
        for(String word : words){
            maxLength = Math.max(maxLength,word.length());
        }
        for(String word: words){
            if(word.length() == maxLength){
                if(wordLength.containsKey(word)){
                    wordLength.put(word, wordLength.get(word) + 1);
                } else  {
                    wordLength.put(word, 1);
                }
            }
        }
        wordLength.entrySet().stream().forEach(x -> System.out.println(x.getKey() +" , " + x.getValue()));
    }
}
