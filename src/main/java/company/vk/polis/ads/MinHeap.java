package company.vk.polis.ads;

import java.util.Arrays;

class MinHeap<T extends Comparable<T>> {
    private static final int ROOT_INDEX = 1;
    private T[] values;
    private int size;

    public MinHeap(int capacity) {
        values = (T[]) new Comparable<?>[capacity + 1];
    }

    public void add(T element) {
        if (size == values.length - 1) {
            values = Arrays.copyOf(values, values.length * 3 / 2);
        }
        values[++size] = element;
        swim(size);
    }

    public T peek() {
        return values[ROOT_INDEX];
    }

    public T remove() {
        T root = values[ROOT_INDEX];
        swap(ROOT_INDEX, size--);
        sink(ROOT_INDEX);
        return root;
    }

    public int size() {
        return size;
    }

    private void swap(int firstIndex, int secondIndex) {
        T temp = values[firstIndex];
        values[firstIndex] = values[secondIndex];
        values[secondIndex] = temp;
    }

    private void swim(int index) {
        int currIndex = index;
        while (currIndex > 1 && values[currIndex].compareTo(values[currIndex / 2]) < 0) {
            swap(currIndex, currIndex / 2);
            currIndex /= 2;
        }
    }

    private void sink(int index) {
        int currIndex = index;
        int childIndex;
        while (2 * currIndex <= size) {
            childIndex = 2 * currIndex;
            if (childIndex < size && values[childIndex].compareTo(values[childIndex + 1]) > 0) {
                childIndex++;
            }
            if (values[currIndex].compareTo(values[childIndex]) <= 0) {
                break;
            }
            swap(childIndex, currIndex);
            currIndex = childIndex;
        }
    }
}

