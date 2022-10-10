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

    private final MinHeap<Pair<T>> heap = new MinHeap<>();
    private final List<Iterator<T>> iterators;


    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        for (Iterator<T> iterator : iterators) {
            if (iterator.hasNext()) {
                heap.insert(new Pair<>(iterator.next(), iterator));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return heap.size() > 0;
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        //следующий элемент из того списка, чей минимум достали из кучи в последний раз
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Pair<T> pair = heap.extract();
        if (pair.iterator.hasNext()) {
            heap.insert(new Pair<>(pair.iterator.next(), pair.iterator));
        } else {
            for (Iterator<T> iterator : iterators) {
                if (iterator.hasNext()) {
                    heap.insert(new Pair<>(iterator.next(), iterator));
                    break;
                }
            }
        }
        return pair.value;
    }


    private class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
        private final T value;
        private final Iterator<T> iterator;

        public Pair(T value, Iterator<T> iterator) {
            this.value = value;
            this.iterator = iterator;
        }

        @Override
        public int compareTo(Pair<T> o) {
            return value.compareTo(o.value);
        }
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
