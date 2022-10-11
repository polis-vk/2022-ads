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
    private final Heap<WrapperIterator<T>> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        this.heap = new Heap<>(iterators.size());

        for (int i = 0; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext()) {
                heap.insert(new WrapperIterator<>(i, iterators.get(i).next()));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !heap.isEmpty();
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

        if (heap.isEmpty()) {
            for (int i = 0; i < iterators.size(); i++) {
                if (iterators.get(i).hasNext()) {
                    heap.insert(new WrapperIterator<>(i, iterators.get(i).next()));
                }
            }
        }

        WrapperIterator<T> wrapper = heap.extract();

        if (iterators.get(wrapper.getIndex()).hasNext()) {
            heap.insert(new WrapperIterator<>(wrapper.getIndex(), iterators.get(wrapper.getIndex()).next()));
        }

        return wrapper.getValue();
    }
}
