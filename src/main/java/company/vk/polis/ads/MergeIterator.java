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
    private final MinHeap<Pair<T>> heap;
    private boolean areIteratorsEmpty;


    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.heap = new MinHeap<>();
        this.iterators = iterators;

        for (int i = 0; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext()) {
                heap.insert(new Pair<>(iterators.get(i).next(), i));
            }
        }
    }

    @Override
    public boolean hasNext() {
        areIteratorsEmpty = true;

        for (Iterator<T> iterator : iterators) {
            if (iterator.hasNext()) {
                areIteratorsEmpty = false;
                break;
            }
        }

        return !areIteratorsEmpty || heap.size() != 0;
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        Pair<T> nextPair = heap.delMin();
        if (areIteratorsEmpty) {
            return nextPair.value;
        }

        if (iterators.get(nextPair.iteratorIndex).hasNext()) {
            heap.insert(new Pair<>(iterators.get(nextPair.iteratorIndex).next(), nextPair.iteratorIndex));
        } else {
            for (int i = 0; i < iterators.size(); i++) {
                if (iterators.get(i).hasNext()) {
                    heap.insert(new Pair<>(iterators.get(i).next(), i));
                    break;
                }
            }
        }

        return nextPair.value;
    }

    private class Pair<TT extends Comparable<TT>> implements Comparable<Pair<TT>> {
        private final T value;
        private final int iteratorIndex;

        public Pair(T value, int iteratorIndex) {
            this.value = value;
            this.iteratorIndex = iteratorIndex;
        }
        @Override
        public int compareTo(Pair<TT> pair) {
            return this.value.compareTo(pair.value);
        }
    }
}
