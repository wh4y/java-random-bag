package com.randombag.RandomBag;

public interface RandomBag<Item> extends Iterable<Item> {
    boolean isEmpty();

    int size();

    void add(Item item);
}