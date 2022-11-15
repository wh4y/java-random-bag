package com.randombag.RandomBag;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBagImpl<Item> implements RandomBag<Item> {

    private Node rootItem;
    private int size;

    public RandomBagImpl() {
        this.rootItem = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Item item) {
        final Node currentRootItem = this.rootItem;
        this.rootItem = new Node(item, currentRootItem);

        this.size++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class Node {
        public Item value;
        public Node next;

        public Node(Item value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private class RandomBagIterator implements Iterator<Item> {
        private Item[] itemCollection;
        private int count;

        public RandomBagIterator() {
            this.count = size;

            initItemCollection();
        }

        private void initItemCollection() {
            this.itemCollection = (Item[]) new Object[size];
            Node target = rootItem;

            for (int i = 0; i < size; i++) {
                this.itemCollection[i] = target.value;
                target = target.next;
            }
        }

        @Override
        public boolean hasNext() {
            return this.count > 0;
        }

        @Override
        public Item next() throws NoSuchElementException {
            if (!this.hasNext()) throw new NoSuchElementException();

            final int currentItemIndex = ThreadLocalRandom.current().nextInt(0, count);
            final Item currentItem = this.itemCollection[currentItemIndex];

            removeItemFromCollection(currentItemIndex);
            this.count--;

            return currentItem;
        }

        private void removeItemFromCollection(int index) {
            Item[] newCollection = (Item[]) new Object[count - 1];

            for (int i = 0, j = 0; i < this.count; i++) {
                if (i == index) continue;
                newCollection[j] = this.itemCollection[i];
                j++;
            }

            this.itemCollection = newCollection;
        }
    }
}
