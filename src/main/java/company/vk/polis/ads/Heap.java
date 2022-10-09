package company.vk.polis.ads;

import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
    private T[] array;
    private int size;
    private int maxSize;

    public Heap(int size) {
        this.maxSize = size;
        this.size = 0;
        array = (T[]) new Comparable[maxSize + 1];
    }

    public void insert(T value) {
        if (maxSize - 1 == size) {
            this.maxSize *= 2;
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[++size] = value;
        swim(size);
    }

    public T delMin() {
        T max = array[1];
        swap(array, 1, size--);
        sink(1);
        return max;
    }

    public T peek() {
        return array[1];
    }

    public int size() {
        return size;
    }

    private void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void swim(int k) {
        while (k > 1 && array[k].compareTo(array[k / 2]) < 0) {
            swap(array, k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && array[j + 1].compareTo(array[j]) < 0) {
                j++;
            }
            if (array[k].compareTo(array[j]) <= 0) {
                break;
            }
            swap(array, k, j);
            k = j;
        }
    }

}