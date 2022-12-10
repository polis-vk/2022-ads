package company.vk.polis.ads;

import java.util.*;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {

    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> heap = new MinHeap<>(k);
        Iterator<T> iterator = list.iterator();

        for (int i = 0; i < k && iterator.hasNext(); i++) {
            heap.insert(iterator.next());
        }

        T currElement;

        while (iterator.hasNext()) {
            currElement = iterator.next();

            if (heap.getMin().compareTo(currElement) < 0) {
                heap.extract();
                heap.insert(currElement);
            }
        }

        List<T> result = new ArrayList<>();
        while (heap.getSize() > 0) {
            result.add(heap.extract());
        }
        Collections.reverse(result);
        return result;
    }

    static class MinHeap<T extends Comparable<T>> {

        private T[] a;
        private int size;

        MinHeap(int size) {
            this.a = (T[]) new Comparable<?>[size];
            this.size = 0;
        }

        void insert(T element) {
            if (size == a.length - 1) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[++size] = element;
            swim(size);
        }

        T extract() {
            T min = a[1];
            swap(1, size--);
            sink(1);
            return min;
        }

        void swap(int i, int j) {
            T tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        void swim(int index) {
            int k = index;

            while (k > 1 && a[k].compareTo(a[k / 2]) < 0) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j].compareTo(a[j + 1]) > 0) {
                    j++;
                }
                if (a[k].compareTo(a[j]) <= 0) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }

        T getMin() {
            return a[1];
        }

        int getSize() {
            return size;
        }
    }
}
