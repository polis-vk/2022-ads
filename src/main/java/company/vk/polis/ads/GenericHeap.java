package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GenericHeap<T extends Comparable<T>> {

    private List<T> storage;
    private int heapSize;

    public GenericHeap(int k) {
        storage = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            storage.add(null);
        }
        this.heapSize = 0;
    }

    public void insert(T x) {
        storage.set(heapSize++, x);
        swim(heapSize - 1);
    }

    public T extract() {
        if (heapSize == 0) {
            throw new NoSuchElementException();
        }
        T max = storage.get(0);
        swap(--heapSize, 0);
        sink(0);
        return max;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public T getTop() {
        return storage.get(0);
    }

    private void sink(int k) {
        int left = getLeft(k);
        int right = getRight(k);
        int largest = k;

        if (left < heapSize && storage.get(k).compareTo(storage.get(left)) > 0) {
            largest = left;
        }

        if (right < heapSize && storage.get(largest).compareTo(storage.get(right)) > 0) {
            largest = right;
        }

        if (largest != k) {
            swap(largest, k);
            sink(largest);
        }
    }

    private void swim(int k) {
        while (k > 0 && storage.get(k).compareTo(storage.get(getParent(k))) < 0) {
            swap(k, getParent(k));
            k = getParent(k);
        }
    }

    private int getParent(int k) {
        return (k - 1) / 2;
    }

    private int getLeft(int k) {
        return 2 * k + 1;
    }

    private int getRight(int k) {
        return 2 * k + 2;
    }

    private void swap(int i, int j) {
        T tmp = storage.get(i);
        storage.set(i, storage.get(j));
        storage.set(j, tmp);
    }

}
