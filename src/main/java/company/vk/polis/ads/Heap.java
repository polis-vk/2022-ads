package company.vk.polis.ads;

public class Heap <T extends Comparable<T>> {
    private int size = 0;
    private final T[] storage;

    public Heap(int size) {
        storage = (T[]) new Comparable[size + 1];
    }

    public void insert (T element) {
        storage[++size] = element;
        swim(size);
    }

    public T returnMin() {
        T minElement = storage[1];
        swap(storage, 1, size--);
        sink(1);

        return minElement;
    }

    public T getMin() {
        return storage[1];
    }

    private void swim(int n) {
        int indexOfNewElement = n;

        while (indexOfNewElement > 1 && storage[indexOfNewElement].compareTo(storage[indexOfNewElement / 2]) < 0) {
            swap(storage, indexOfNewElement, indexOfNewElement / 2);
            indexOfNewElement = indexOfNewElement / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && storage[j].compareTo(storage[j + 1]) > 0) {
                j++;
            }
            if (storage[k].compareTo(storage[j]) <= 0) {
                break;
            }
            swap(storage, k, j);
            k = j;
        }
    }
    public void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}



