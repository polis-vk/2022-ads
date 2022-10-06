package company.vk.polis.ads;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {

    private static class Heap<T extends Comparable<T>> {
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

        public T get() {
            return (T) data[1];
        }
    }

    /**
     * Top-K with O(n logk) complexity.
     * @param list input list
     * @param k amount of max values to carry out
     * @return list with k max elements ordered descending
     * @param <T> type of elements
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        Heap<T> heap = new Heap<>(k);

        for (T item : list) {
            if (heap.size() < k) {
                heap.insert(item);
            } else {
                if (item.compareTo(heap.get()) > 0) {
                    heap.delete();
                    heap.insert(item);
                }
            }
        }

        LinkedList<T> result = new LinkedList<>();

        while (heap.size() > 0) {
            result.addFirst(heap.delete());
        }

        return result;
    }
}
