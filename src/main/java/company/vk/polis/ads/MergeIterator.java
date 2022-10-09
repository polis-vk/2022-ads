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

    private class CustomPair implements Comparable<CustomPair> {
        private T value;
        private int index;

        public CustomPair(T value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(CustomPair pair) {
            return value.compareTo(pair.value);
        }
    }

    private final List<Iterator<T>> iterators;
    private final Heap<CustomPair> heap;
    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        heap = new Heap(iterators.size());
        for (int i = 0; i < iterators.size(); i++) {
            if(iterators.get(i).hasNext()) {
                heap.insert(new CustomPair(iterators.get(i).next(), i));
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
        CustomPair pair = heap.delMin();
        int index = pair.index;
        if (iterators.get(index).hasNext()) {
            heap.insert(new CustomPair(iterators.get(index).next(), index));
        }
        return pair.value;
    }
}
