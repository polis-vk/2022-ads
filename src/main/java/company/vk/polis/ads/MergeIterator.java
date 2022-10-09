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
    private final HeapKeyValue<T> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        heap = new HeapKeyValue<>(iterators.size() + 1);

        for (Iterator<T> iterator : iterators) {
            valueToHeap(iterator);
        }
    }

    @Override
    public boolean hasNext() {
        return heap.getSizeMin() > 0;
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

        Pair<T> pair = heap.extractMin();
        Iterator<T> iterator = pair.getIterator();
        valueToHeap(iterator);

        return pair.getValue();
    }

    private void valueToHeap(Iterator<T> iterator) {
        if (iterator.hasNext()) {
            heap.insertMin(new Pair<>(iterator.next(), iterator));
        }
    }
}
