package company.vk.polis.ads;

import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
    private T[] array;
    private int size;

    public Heap(int size) {
        array = (T[]) new Comparable<?>[size + 1];
    }

    public int size() {
        return size;
    }

    public T peek() {
        return array[1];
    }

    public void insert(T element) {
        if (size == array.length - 1) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[++size] = element;
        swim(size);
    }

    public T extract() {
        T max = array[1];
        swap(array, 1, size--);
        sink(1);
        return max;
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
            if (j < size && array[j].compareTo(array[j + 1]) > 0) j++;
            if (array[k].compareTo(array[j]) <= 0) break;
            swap(array, k, j);
            k = j;
        }
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
