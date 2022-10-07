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

    private class Pair {

        private final T val;
        private final int number; // Индекс итератора, откуда пришел элемент.

        public Pair(T val, int number) {
            this.val = val;
            this.number = number;
        }
    }

    private class Heap {

        private final List<Pair> a;
        private int size;

        public Heap(int capacity) {
            this.a = new ArrayList<>();
            for (int i = 0; i < capacity + 1; i++) {
                a.add(new Pair(null,-1));
            }
            this.size = 0;
        }

        public void insert(Pair x) {
            a.set(++size, x);
            swim(size);
        }

        public Pair extract() {
            Pair min = a.get(1);

            swap(1, size--);
            sink(1);

            return min;
        }

        public void swap(int i, int j) {
            Pair temp = a.get(i);
            a.set(i, a.get(j));
            a.set(j, temp);
        }

        public void swim(int index) {
            int k = index;

            while (k > 1 && a.get(k).val.compareTo(a.get(k / 2).val) < 0) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        public void sink(int index) {
            int k = index;

            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a.get(j + 1).val.compareTo(a.get(j).val) < 0) {
                    j++;
                }
                if (a.get(k).val.compareTo(a.get(j).val) <= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }

    private final List<Iterator<T>> iterators;
    private final Heap heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        this.heap = new Heap(iterators.size());

        int count = 0;
        for (Iterator<T> iter : iterators) {
            if (iter.hasNext()) {
                heap.insert(new Pair(iter.next(), count));
            }
            count++;
        }
    }

    @Override
    public boolean hasNext() {
        return heap.size > 0;
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Pair result = heap.extract(); // Получили минимальный элемент из кучи.

        Iterator<T> curIter = iterators.get(result.number); // Получили итератор, откуда взяли минимальный элемент.
        if (curIter.hasNext()) {
            heap.insert(new Pair(curIter.next(), result.number));
        } else {
            int count = 0;
            for (Iterator<T> iter : iterators) {
                if (iter.hasNext()) {
                    heap.insert(new Pair(iter.next(), count));
                    break;
                }
                count++;
            }
        }

        return result.val;
    }
}
