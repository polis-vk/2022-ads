package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
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
        MinHeap<T> minHeap = new MinHeap<>();
        for (int i = 0; i < k; i++) {
            minHeap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            if (list.get(i).compareTo(minHeap.getRoot()) > 0) {
                minHeap.delFirst();
                minHeap.insert(list.get(i));
            }
        }
        List<T> result = new ArrayList<>();
        int size = minHeap.size();
        for (int i = 1; i <= size; i++) {
            result.add(minHeap.getRoot());
            minHeap.delFirst();
        }
        Collections.reverse(result);
        return result;
    }

    final class MinHeap<T extends Comparable<T>> {

        private final List<T> heap = new ArrayList<>();

        public MinHeap() {
            heap.add(null);
        }

        private void sink(int k, int n) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && heap.get(j).compareTo(heap.get(j + 1)) > 0) j++;
                if (heap.get(k).compareTo(heap.get(j)) < 0) break;
                swap(k, j);
                k = j;
            }
        }

        T getRoot() {
            return heap.get(1);
        }

        private void swap(int k, int j) {
            T temp = heap.get(k);
            heap.set(k, heap.get(j));
            heap.set(j, temp);
        }

        private void swim(int pos) {
            while (pos > 1 && heap.get(pos).compareTo(heap.get(pos / 2)) < 0) {
                swap(pos, pos / 2);
                pos = pos / 2;
            }
        }

        void insert(T input) {
            heap.add(input);
            swim(heap.size() - 1);
        }

        void delFirst() {
            swap(1, size());
            heap.remove(size());
            sink(1, size());
        }

        private int size() {
            return heap.size() - 1;
        }
    }

}
