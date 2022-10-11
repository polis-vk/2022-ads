package company.vk.polis.ads;

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
    private MinHeap<Pair> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        fillHeap();
    }

    @Override
    public boolean hasNext() {
        return heap.getSize() > 0;
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
        Pair pair = heap.delMin();
        T res = pair.value;
        if (pair.iterator.hasNext()) {
            pair.value = pair.iterator.next();
            heap.insert(pair);
        }
        return res;
    }

    private void fillHeap() {
        heap = new MinHeap<>(iterators.size() + 1);
        for (Iterator<T> iterator : iterators) {
            heap.insert(new Pair(iterator.next(), iterator));
        }
    }

    private class Pair implements Comparable<Pair> {
        Iterator<T> iterator;
        T value;

        Pair(T value, Iterator<T> iterator) {
            this.iterator = iterator;
            this.value = value;
        }

        @Override
        public int compareTo(Pair pair) {
            return value.compareTo(pair.value);
        }
    }
}
