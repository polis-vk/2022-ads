package company.vk.polis.ads;

import java.util.Arrays;

public class MinHeap<T extends Comparable<T>> {
    private T[] a;
    private int n;

    private int capacity;

    public MinHeap(int capacity) {
        this.a = (T[]) new Comparable[capacity + 1];
        this.capacity = capacity;
    }

    public void insert(T v) {
        a[++n] = v;
        swim(n);
    }

    private void swim(int k) {
        while (k > 1 && a[k].compareTo(a[k / 2]) < 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;// left child
            if (j < n && a[j].compareTo(a[j + 1]) < 0) {
                j++;
            }
            if (a[k].compareTo(a[j]) < 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }
    private void swap(int i, int j) {
        T temp = a[i];
        a[i] = this.a[j];
        a[j] = temp;
    }

    public T delMin() {
        T min = a[1];
        swap(1, n--);
        sink(1);
        return min;
    }

    public T root () {
        return a[1];
    }

    public int size() {
        return n;
    }
}
