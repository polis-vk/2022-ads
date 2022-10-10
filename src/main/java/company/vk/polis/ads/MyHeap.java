package company.vk.polis.ads;

public class MyHeap<T extends Comparable<T>> {
    private T[] array;
    private int size = 0;

    MyHeap(int size) {
        array = (T[]) new Comparable[size + 1];
    }

    public int getSize() {
        return size;
    }

    private void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public void swim(int k) {
        int index = k;
        while (index > 1 && array[index].compareTo(array[index / 2]) < 0) {
            swap(array, index, index / 2);
            index /= 2;
        }
    }

    public void sink(int k) {
        int index = k;
        while (2 * index <= size) {
            int j = 2 * index;
            if (j < size && array[j].compareTo(array[j + 1]) > 0) j++;
            if (array[index].compareTo(array[j]) < 0) break;
            swap(array, index, j);
            index = j;
        }
    }

    public void insert(T element) {
        array[++size] = element;
        swim(size);
    }

    public T delMin() {
        T min = array[1];
        swap(array, 1, size--);
        sink(1);
        return min;
    }

    public T peek() {
        return array[1];
    }
}
