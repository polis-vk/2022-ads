package company.vk.polis.ads;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator that merges k input iterators ordered ascending.
 * Each value returned by #next() is greater than or equal to previous one.
 * Total cost of iteration is O(n logk).
 *
 * @param <T> type of elements
 */
public final class MergeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final List<Iterator<T>> iterators;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Implement me");
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        throw new UnsupportedOperationException("Implement me");
    }
}
