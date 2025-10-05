package com.masanta.ratan.companyInterviewQuestions;

import com.masanta.ratan.daily.practice.leetcode.medium.ImplementTrie;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenItemIterator implements Iterator<Integer> {


    private Iterator<Integer> itr;
    private int currentObject;


    EvenItemIterator(Iterator<Integer> itr){
        this.itr = itr;
    }

    @Override
    public boolean hasNext() {
        while(itr.hasNext()){
            int item = itr.next();
            if(item % 2 == 0){
                currentObject = item;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if(itr.hasNext()){
            return itr.next();
        } else {
            throw new NoSuchElementException();
        }
    }
}
