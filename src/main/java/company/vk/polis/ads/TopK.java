package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {

    private class Heap<T extends Comparable<T>> {

        private final List<T> a;
        private int size;

        public Heap(int capacity) {
            this.a = new ArrayList<>();
            for (int i = 0; i < capacity + 1; i++) {
                a.add(null);
            }
            this.size = 0;
        }

        public void insert(T x) {
            a.set(++size, x);
            swim(size);
        }

        public T extract() {
            T min = a.get(1);

            swap(1, size--);
            sink(1);

            return min;
        }

        public void swap(int i, int j) {
            T temp = a.get(i);
            a.set(i, a.get(j));
            a.set(j, temp);
        }

        public void swim(int index) {
            int k = index;

            while (k > 1 && a.get(k).compareTo(a.get(k / 2)) < 0) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        public void sink(int index) {
            int k = index;

            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a.get(j + 1).compareTo(a.get(j)) < 0) {
                    j++;
                }
                if (a.get(k).compareTo(a.get(j)) <= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        public T peek() {
            return a.get(1);
        }
    }

    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        Heap<T> heap = new Heap<>(k);

        Iterator<T> iter = list.iterator();
        for (int i = 0; i < k; i++) {
            if (iter.hasNext()) {
                heap.insert(iter.next());
            }
        }

        while (iter.hasNext()) {
            T cur = iter.next();
            if (cur.compareTo(heap.peek()) > 0) {
                heap.extract();
                heap.insert(cur);
            }
        }

        List<T> result = new LinkedList<>();
        while (heap.size > 0) {
            result.add(heap.extract());
        }
        Collections.reverse(result);
        return result;
    }
}
