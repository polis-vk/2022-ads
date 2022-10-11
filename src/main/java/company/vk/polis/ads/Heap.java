package company.vk.polis.ads;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

class Heap <T extends Comparable<T>>{

    private Object[] objects;
    private int size;

    public Heap(int size) {
        objects = new Object[size + 1];
        size = 0;
    }

    public void insert(T object) {
        objects[++size] = object;
        swim(size);
    }

    public T extract() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T min = (T)objects[1];
        swap(1, size--);
        sink(1);
        return min;
    }

    private void sink(int k) {
        while (k * 2 <= size) {
            int j = k * 2;
            if (j < size && ((T)objects[j + 1]).compareTo((T)objects[j]) < 0) {
                j++;
            }
            if (((T)objects[k]).compareTo((T)objects[j]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }
    private void swim(int k) {
        while (k > 1 && ((T)objects[k]).compareTo((T)objects[k / 2]) < 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    public List<T> asOrderedList(Comparator<T> comparator) {
        List<T> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            list.add((T) objects[i]);
        }

        list.sort(comparator);
        return list;
    }

    private void swap(int i, int j) {
        Object temp = objects[i];
        objects[i] = objects[j];
        objects[j] = temp;
    }

    public T peek() {
        return (T) objects[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}


