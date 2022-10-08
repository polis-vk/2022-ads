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
    private MinHeap<Tuple> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        initHeap(iterators);
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

        Tuple currTuple = heap.remove();
        Iterator<T> currIterator = iterators.get(currTuple.iterIndex);
        icreaseHeap(currIterator, currTuple.iterIndex);
        return currTuple.value;
    }

    private void initHeap(List<Iterator<T>> iterators) {
        heap = new MinHeap<>(iterators.size());

        int index = 0;
        for (Iterator<T> iterator : iterators) {
            icreaseHeap(iterator, index++);
        }
    }

    private void icreaseHeap(Iterator<T> iterator, int index) {
        if (iterator.hasNext()) {
            heap.add(new Tuple(iterator.next(), index));
        }
    }

    private class Tuple implements Comparable<Tuple> {
        T value;
        int iterIndex;

        public Tuple(T value, int iterIndex) {
            this.value = value;
            this.iterIndex = iterIndex;
        }

        @Override
        public int compareTo(Tuple tuple) {
            return value.compareTo(tuple.value);
        }
    }
}

