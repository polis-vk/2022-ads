package company.vk.polis.ads;

public class MinHeap<T extends Comparable<T>> {
    private T[] array;
    private int size;
    private int capacity;

    public MinHeap() {
        this.clear();
    }

    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        this.capacity = 10;
        this.size = 0;
        this.array = (T[]) new Comparable[this.capacity];
    }

    public T delMin() {
        if (size == 0) {
            return null;
        }

        T max = array[1];

        swap(1, size--);
        sink(1);

        return max;
    }

    public T min() {
        if (size == 0) {
            return null;
        }

        return array[1];
    }

    public void insert(T value) {
        if (this.size + 1 == this.capacity) {
            this.resize();
        }

        array[++size] = value;
        swim(size);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] oldArray = this.array;
        this.capacity = (int) Math.ceil((double) this.capacity * 1.5);
        this.array = (T[]) new Comparable[this.capacity];

        for (int i = 0; i < this.size + 1; i++) {
            this.array[i] = oldArray[i];
        }
    }

    private void swim(int k) {
        while (k > 1 && array[k].compareTo(array[k / 2]) < 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && array[j].compareTo(array[j + 1]) > 0) {
                j++;
            }

            if (array[k].compareTo(array[j]) <= 0) {
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
