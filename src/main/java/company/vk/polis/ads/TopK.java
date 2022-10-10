package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> heap = new MinHeap<T>();
        List<T> topK = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            heap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            if (list.get(i).compareTo(heap.peek()) > 0) {
                heap.extract();
                heap.insert(list.get(i));
            }
        }
        while (heap.size() > 0) {
            topK.add(heap.extract());
        }
        topK.sort(Comparator.reverseOrder());
        return topK;

    }

    public static class MinHeap<T extends Comparable<T>> {
        protected final List<T> a;

        public MinHeap() {
            a = new ArrayList<>();
            a.add(null);
        }

        public void insert(T x) {
            a.add(x);
            swim(size());
        }


        public T extract() {
            T max = a.get(1);
            swap(1, size());
            a.remove(size());
            sink(1);
            return max;
        }

        public T peek() {
            return a.get(1);
        }


        protected void sink(int k) {
            while (2 * k <= size()) {
                int j = 2 * k;
                if (j < size() && a.get(j).compareTo(a.get(j + 1)) > 0) {
                    j++;
                }
                if (a.get(k).compareTo(a.get(j)) <= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        protected void swim(int k) {
            while (k > 1 && a.get(k).compareTo(a.get(k / 2)) < 0) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void swap(int k1, int k2) {
            T temp = a.get(k1);
            a.set(k1, a.get(k2));
            a.set(k2, temp);
        }

        private int size() {
            return a.size() - 1;
        }

    }
}
