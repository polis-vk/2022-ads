package company.vk.polis.ads;

import java.util.Comparator;

public class Heap {

    private static final float GOLDEN_RATIO = (float) 1.62;
    private int[] storage;
    private int heapSize;
    private int capacity;
    Comparator<Integer> cmp;

    public Heap(Comparator<Integer> cmp) {
        this(10, cmp);
    }

    public Heap(int size, Comparator<Integer> cmp) {
        capacity = size * 2;
        storage = new int[capacity];
        heapSize = 0;
        this.cmp = cmp;
    }

    public void insert(int x) {
        if (heapSize + 1 > capacity) {
            resize();
        }
        storage[heapSize++] = x;
        swim(heapSize - 1);
    }

    public int extract() {
        int max = storage[0];
        swap(--heapSize, 0);
        sink(0);
        return max;
    }

    public void buildHeapOnArray(int [] arr) {
        for(int i = 0; i < arr.length; ++i) {
            insert(arr[i]);
        }
    }

    public int getTop() {
        return storage[0];
    }

    private void sink(int k) {
        int left = getLeft(k);
        int right = getRight(k);
        int largest = k;

        if (left < heapSize && cmp.compare(storage[left], storage[k]) > 0) {
            largest = left;
        }

        if (right < heapSize && cmp.compare(storage[right], storage[largest]) > 0) {
            largest = right;
        }

        if (largest != k) {
            swap(largest, k);
            sink(largest);
        }
    }

    private void swim(int k) {
        while (k > 0 && cmp.compare(storage[k], storage[getParent(k)]) > 0) {
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
        int tmp = storage[i];
        storage[i] = storage[j];
        storage[j] = tmp;
    }

    private void resize() {
        int newCapacity = (int) (capacity * GOLDEN_RATIO);
        int[] tmp = new int[newCapacity];
        for (int i = 0; i < capacity; ++i) {
            tmp[i] = storage[i];
        }
        capacity = newCapacity;
        storage = tmp;
    }
}
