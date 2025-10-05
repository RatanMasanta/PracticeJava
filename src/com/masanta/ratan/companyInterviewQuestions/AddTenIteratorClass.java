package com.masanta.ratan.companyInterviewQuestions;

import java.util.Iterator;

public class AddTenIteratorClass implements Iterator<Integer> {

    private Iterator<Integer> itr;

    AddTenIteratorClass(Iterator<Integer> itr) {
        this.itr = itr;
    }


    @Override
    public boolean hasNext() {
        return itr.hasNext();
    }

    @Override
    public Integer next() {
        return itr.next()  + 10;
    }
}
