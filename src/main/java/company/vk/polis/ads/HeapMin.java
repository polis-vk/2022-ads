package company.vk.polis.ads;

import java.security.PublicKey;

public class HeapMin<T extends Comparable<T>> {
    private T[] array;
    private int n;

    public HeapMin(int capacity) {
        this.array = (T[]) new Comparable[capacity + 1];
    }

    public int size() {
        return n;
    }

    public void insert(T x) {
        array[++n] = x;
        swim(n);
    }

    public T extract() {
        T max = array[1];
        swap(1, n--);
        sink(1);
        return max;
    }

    public T peek() {
        return array[1];
    }

    void swim(int k) {
        while (k > 1 && array[k].compareTo(array[k/2]) < 0) {
            swap(k, k/2);
            k = k/2;
        }
    }

    void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && array[j].compareTo(array[j + 1]) > 0) j++;
            if (array[k].compareTo(array[j]) <= 0) break;
            swap(k, j);
            k = j;
        }
    }

    void swap(int k1, int k2) {
        T temp = array[k1];
        array[k1] = array[k2];
        array[k2] = temp;
    }
}
