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
    private final Heap<Pair> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        heap = new Heap<>(iterators.size());
        int index = 0;
        for (Iterator<T> iterator : iterators) {
            if (iterator.hasNext()) {
                heap.insert(new Pair(iterator.next(), index++));
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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Pair pair = heap.extract();
        int index = pair.index;
        if (iterators.get(index).hasNext()) {
            heap.insert(new Pair(iterators.get(index).next(), index));
        }
        return pair.value;
    }

    private class Pair implements Comparable<Pair> {

        private final T value;
        private final int index;

        private Pair(T value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Pair pair) {
            return value.compareTo(pair.value);
        }
    }
}
