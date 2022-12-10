package company.vk.polis.ads;

import java.util.ArrayList;
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
    private final MinHeap heap;

    /**
     * Constructor
     *
     * @@ -20,11 +94,18 @@
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        this.heap = new MinHeap(iterators.size());

        for (Iterator<T> iter : iterators) {
            if (iter.hasNext()) {
                heap.insert(new Pair(iter.next(), iter));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return heap.size > 0;
    }

    /**
     * @@ -34,6 +115,24 @@ public boolean hasNext() {
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Pair result = heap.extract();

        Iterator<T> current = result.iter;
        if (current.hasNext()) {
            heap.insert(new Pair(current.next(), current));
        } else {
            for (int i = 0; i < iterators.size(); i++) {
                if (iterators.get(i).hasNext()) {
                    heap.insert(new Pair(iterators.get(i).next(), iterators.get(i)));
                    break;
                }
            }
        }
        return result.data;
    }

    private class Pair {
        private final T data;
        private final Iterator<T> iter;

        public Pair() {
            this.data = null;
            this.iter = null;
        }

        public Pair(T data, Iterator<T> iter) {
            this.data = data;
            this.iter = iter;
        }
    }

    private class MinHeap {
        private final ArrayList<Pair> a;
        private int size;

        public MinHeap(int size) {
            this.a = new ArrayList<Pair>();
            this.size = 0;

            for (int i = 0; i < size + 1; i++) {
                a.add(new Pair());
            }

        }

        public void insert(Pair x) {
            size++;
            a.set(size, x);
            swim(size);
        }

        public Pair extract() {
            Pair min = a.get(1);
            swap(1, size--);
            sink(1);
            return min;
        }

        public void swap(int i, int j) {
            Pair tmp = a.get(i);
            a.set(i, a.get(j));
            a.set(j, tmp);
        }

        public void swim(int index) {
            int k = index;
            while (k > 1 && a.get(k).data.compareTo(a.get(k / 2).data) < 0) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        public void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a.get(j).data.compareTo(a.get(j + 1).data) > 0) {
                    j++;
                }
                if (a.get(k).data.compareTo(a.get(j).data) <= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }
}
