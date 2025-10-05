package com.masanta.ratan.companyInterviewQuestions;

import java.util.*;

/**
 *
 * Implement a method that will return distinct elements and will implement iterator.
 * List items are in sorted order.
 * We cannot hold the full data in the class
 *
 * first implement even element iterator
 */


public class SortedIterator {


    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 1, 2, 2, 2, 3, 3, 5, 6, 9, 9));


//        Iterator<Integer> wrappedIterator = getWrappedIter(list.iterator());
//
//
//        while (wrappedIterator.hasNext()) {
//
//            System.out.println(wrappedIterator.next());
//
//        }


		Iterator<Integer> distinctIterator = getWrappedIter(list.iterator());



		while (distinctIterator.hasNext()) {

			distinctIterator.hasNext();

			System.out.println(distinctIterator.next());

		}

        System.out.println("****");
        Iterator<Integer> distinctIterator2 = getWrappedIter(list.iterator());
        //Iterator<Integer> distinctIterator2 = list.iterator();
		try {

			while (true) {

				System.out.println(distinctIterator2.next());

			}

		} catch (Exception e) {

		}


    }


    private static Iterator<Integer> getWrappedIter(Iterator<Integer> itr) {

        return new EvenItemIterator(itr);
//        return new Iterator<Integer>() {
//
//
//            @Override
//
//            public boolean hasNext() {
//
//                return itr.hasNext();
//
//            }
//
//
//            @Override
//
//            public Integer next() {
//
//                return itr.next() + 10;
//
//            }
//
//        };

    }


    private static Iterator<Integer> getDistinctIter(Iterator<Integer> itr) {

        return null;

    }

}