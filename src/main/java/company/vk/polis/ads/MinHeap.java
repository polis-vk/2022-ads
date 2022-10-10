package company.vk.polis.ads;

import java.util.Arrays;

public class MinHeap<T extends Comparable<T>> {
    private T[] a;//Куча
    private int size;//Мощность
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        a = (T[]) new Comparable[capacity + 1];
        size = 0;

    }

    public void insert(T v) {
        if (a.length - 1 == size) {
            a = Arrays.copyOf(a, a.length * 2);
        }
        a[++size] = v;
        swim(size);
    }

    private void swim(int k) {
        while (k > 1 && a[k].compareTo(a[k / 2]) < 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;// left child
            if (j < size && a[j].compareTo(a[j + 1]) < 0) {
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
        swap(1, size--);
        sink(1);
        return min;
    }

    public T root () {
        return a[1];
    }

    public int size() {
        return size;
    }
}
