package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n logk) complexity.
     * @param list input list
     * @param k amount of max values to carry out
     * @return list with k max elements ordered descending
     * @param <T> type of elements
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MaxHeap<T> maxHeap = new MaxHeap<>(k + 1);
        for (int i = 0; i < k; i++) {
            maxHeap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            T value = list.get(i);
            if (value.compareTo(maxHeap.peek()) > 0) {
                maxHeap.extract();
                maxHeap.insert(value);
            }
        }
        List<T> result = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            result.add(maxHeap.array[i]);
        }
        return result;
    }

    private static class MaxHeap<T extends Comparable<T>> {

        T[] array;
        int size;

        public MaxHeap(int capacity) {
            array = (T[]) new Comparable[capacity];
        }

        void swap(T[] array, int i, int j) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        void swim(int k) {
            while (k > 1 && array[k].compareTo(array[k / 2]) > 0) {
                swap(array, k, k / 2);
                k = k / 2;
            }
        }

        void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && array[j].compareTo(array[j + 1]) < 0) {
                    j++;
                }
                if (array[k].compareTo(array[j]) <= 0) {
                    break;
                }
                swap(array, k, j);
                k = j;
            }
        }

        void insert(T v) {
            array[++size] = v;
            swim(size);
        }

        T extract() {
            T max = array[1];
            swap(array, 1, size--);
            sink(1);
            return max;
        }

        T peek() {
            return array[1];
        }
    }
}
