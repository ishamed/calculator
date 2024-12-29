package model.dataStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList <E> implements Iterable<E>{
    private static final int CAPACITY = 1000;
    private E[] list;
    private int size;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        list = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void add(E e) {
        if (size >= list.length) {
            resize();
        }
        list[size++] = e;
    }

    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        return list[i];
    }

    public void remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        }
        System.arraycopy(list, i + 1, list, i, size - i - 1);
        list[--size] = null;
    }

    public int size() {
        return size;
    }

    private void resize() {
        list = Arrays.copyOf(list, list.length * 2);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return list[currentIndex++];
            }
        };
    }
}
