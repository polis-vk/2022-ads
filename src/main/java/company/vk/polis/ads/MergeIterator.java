package company.vk.polis.ads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator that merges k input iterators ordered ascending.
 * Each value returned by #next() is greater than or equal to previous one.
 * Total cost of iteration is O(n logk).
 *
 * @param <T> type of elements
 */
public final class MergeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final List<Iterator<T>> iterators;
    private final Heap heap;
    private final Object[] valuesOfIterators;
    private final int size;

    private class Heap {
        private Object[] data;
        private int n;

        public Heap(int size) {
            data = new Object[size + 1];
        }

        public int size() {
            return n;
        }

        private void expand() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        private void swap(int i, int j) {
            Object temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        private void swim(int i) {
            int current = i;
            int parent = i >> 1;

            while (current > 1 && ((T) data[current]).compareTo((T) data[parent]) < 0) {
                swap(current, parent);
                current = parent;
                parent >>= 1;
            }
        }

        private void sink(int i) {
            int parent = i;
            int current = i << 1;

            while (current <= n) {
                if (current < n && ((T) data[current]).compareTo((T) data[current + 1]) > 0) {
                    current++;
                }

                if (((T) data[parent]).compareTo((T) data[current]) <= 0) {
                    break;
                }

                swap(parent, current);

                parent = current;

                current <<= 1;
            }
        }

        public T delete() {
            T max = (T) data[1];
            swap(1, n);
            n--;
            sink(1);
            return max;
        }

        public void insert(T value) {
            if (n >= data.length - 1) {
                expand();
            }
            n++;
            data[n] = value;
            swim(n);
        }
    }

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        this.size = iterators.size();
        this.heap = new Heap(size);
        this.valuesOfIterators = new Object[size];
    }

    @Override
    public boolean hasNext() {
        for (Iterator<T> iterator : iterators) {
            if (iterator.hasNext()) {
                return true;
            }
        }
        return heap.size() > 0;
    }

    private static <T> int index(T[] a, T object) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("There are no more elements left!");
        }

        int i = 0;
        T value;
        T temp;

        if (heap.size() < size) {
            while (hasNext() && heap.size() < size && i < size) {
                if (iterators.get(i).hasNext()) {
                    value = iterators.get(i).next();
                    heap.insert(value);
                    valuesOfIterators[i] = value;
                }
                i++;
            }
        }

        value = heap.delete();
        i = index(valuesOfIterators, value);

        if (i != -1) {
            if (iterators.get(i).hasNext()) {
                temp = iterators.get(i).next();
                heap.insert(temp);
                valuesOfIterators[i] = temp;
            } else {
                valuesOfIterators[i] = -1;
            }
        }

        return value;
    }
}
