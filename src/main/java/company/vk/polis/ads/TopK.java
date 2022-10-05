package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    private static class MinHeap<T extends Comparable<T>> {
        private static final int INITIAL_SIZE = 16;
        private static final int EXPANSION_COEFFICIENT = 2;
        private int n;
        private Object[] heap;
        public MinHeap() {
            n = 0;
            heap = new Object[INITIAL_SIZE];
        }
        private T getCasted(int index) {
            return (T)heap[index];
        }
        private void add(T element) {
            if (n == heap.length - 1) {
                heap = Arrays.copyOf(heap, heap.length * EXPANSION_COEFFICIENT);
            }
            heap[++n] = (Object)element;
            swim(n);
        }
        private T popRoot() {
            T root = getCasted(1);
            swap(1, n--);
            sink(1);
            return root;
        }
        private T peekRoot() {
            return getCasted(1);
        }
        private void sink(int k) {
            while (k * 2 <= n) {
                int j = k * 2;
                if (j < n && getCasted(j + 1).compareTo(getCasted(j)) < 0) {
                    j++;
                }
                if (getCasted(k).compareTo(getCasted(j)) <= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
        private void swim(int k) {
            while (k > 1 && getCasted(k).compareTo(getCasted(k / 2)) < 0) {
                swap(k, k / 2);
                k /= 2;
            }
        }
        private void swap(int i, int j) {
            Object temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
        public List<T> asOrderedList(Comparator<T> comparator) {
            List<T> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                list.add(getCasted(i));
            }
            list.sort(comparator);
            return list;
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
        MinHeap<T> minHeap = new MinHeap<>();
        for (int i = 0; i < k; i++) {
            minHeap.add(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            T element = list.get(i);
            if (element.compareTo(minHeap.peekRoot()) > 0) {
                minHeap.popRoot();
                minHeap.add(element);
            }
        }
        return minHeap.asOrderedList((e1, e2) -> -e1.compareTo(e2));
    }
}
