package company.vk.polis.ads;

public class MinHeap<T extends Comparable<T>> {
    private T[] heap;
    private int capacity;
    private int size;

    public MinHeap() {
        capacity = 512;
        heap = (T[]) new Comparable[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private int parent(int root) {
        return root / 2;
    }

    private int left(int root) {
        return root * 2;
    }

    private int right(int root) {
        return root + 1;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private boolean more(int i, int j) {
        return heap[i].compareTo(heap[j]) > 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k, parent(k))) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void sink(int k) {
        while (left(k) <= size) {
            int j = left(k);
            if (j < size && more(j, right(j))) {
                j++;
            }
            if (!more(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public void insert(T x) {
        if (size + 1 == capacity) {
            T[] temp = heap;
            capacity = capacity + capacity / 2;
            heap = (T[]) new Comparable[capacity];
            if (this.size + 1 >= 0) {
                System.arraycopy(temp, 0, heap, 0, this.size + 1);
            }
        }
        size++;
        heap[size] = x;
        swim(size);
    }

    public T popMin() {
        if (size == 0) {
            return null;
        }
        T min = heap[1];
        swap(1, size);
        size--;
        sink(1);
        return min;
    }

    public T getMin() {
        return size == 0 ? null : heap[1];
    }
}
