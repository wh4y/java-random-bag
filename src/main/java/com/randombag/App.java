package com.randombag;

import com.randombag.RandomBag.RandomBag;
import com.randombag.RandomBag.RandomBagImpl;

import java.util.Iterator;


public class App {
    public static void main(String[] args) {

        final RandomBag<Integer> bag = new RandomBagImpl<>();

        System.out.printf("Is empty: %b!%n", bag.isEmpty());

        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(5);

        System.out.println("Items added!");
        System.out.printf("Is empty: %b!%n", bag.isEmpty());
        System.out.printf("Size: %d!%n", bag.size());

        final Iterator<Integer> iterator = bag.iterator();

        System.out.println("\nLet's iterate it!\n");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
