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

    class Pair implements Comparable<Pair> {
        private T elem;
        private int inx;

        public Pair(T elem, int inx) {
            this.elem = elem;
            this.inx = inx;
        }

        public T getElem() {
            return elem;
        }

        public int getInx() {
            return inx;
        }

        @Override
        public int compareTo(Pair pair) {
            return elem.compareTo(pair.elem);
        }
    }

    private GenericHeap<Pair> minHeap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        minHeap = new GenericHeap<>(iterators.size());
        for (int i = 0; i < iterators.size(); ++i) {
            if (iterators.get(i).hasNext()) {
                minHeap.insert(new Pair(iterators.get(i).next(), i));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return minHeap.getHeapSize() > 0;
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        Pair tmp = minHeap.extract();
        int indexOfList = tmp.getInx();
        if (iterators.get(indexOfList).hasNext()) {
            minHeap.insert(new Pair(iterators.get(indexOfList).next(), indexOfList));
        }
        return tmp.elem;
    }
}
