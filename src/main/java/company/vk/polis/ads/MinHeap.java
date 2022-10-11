package company.vk.polis.ads;

import java.util.Arrays;

class MinHeap<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] minHeap;
    private int size;


    public MinHeap() {
        this.minHeap = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public MinHeap(int initialCapacity) {
        this.minHeap = (T[]) new Comparable[initialCapacity];
    }

    public int getSize() {
        return size;
    }

    public T peek() {
        return minHeap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(T x) {
        if (this.size >= this.minHeap.length) {
            this.minHeap = this.resize();
        }
        size++;
        minHeap[size - 1] = x;
        siftUp(size - 1);
    }

    public T extract() {
        T min = minHeap[0];
        minHeap[0] = minHeap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    private T[] resize() {
        int newCapacity = minHeap.length * 3 / 2 + 1;
        return Arrays.copyOf(minHeap, newCapacity);
    }

    private void swap(int a, int b) {
        T temp = minHeap[a];
        minHeap[a] = minHeap[b];
        minHeap[b] = temp;
    }

    private void siftUp(int k) {
        while (k > 0 && minHeap[k].compareTo(minHeap[(k - 1) / 2]) < 0) {
            swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void siftDown(int k) {
        while (2 * k + 1 < size) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int j = left;
            if (right < size && minHeap[j].compareTo(minHeap[right]) > 0) {
                j++;
            }
            if (minHeap[k].compareTo(minHeap[j]) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

}