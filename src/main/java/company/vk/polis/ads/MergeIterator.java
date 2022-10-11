package company.vk.polis.ads;

import java.util.*;

/**
 * Iterator that merges k input iterators ordered ascending.
 * Each value returned by #next() is greater than or equal to previous one.
 * Total cost of iteration is O(n logk).
 *
 * @param <T> type of elements
 */
public final class MergeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final List<Iterator<T>> iterators;
    private int iterIndex;
    private final MinHeap<Pair> heap = new MinHeap<>();

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        initHeap();
    }

    public void initHeap() {
        int index = 0;
        for (Iterator<T> iter: iterators) {
            if (iter.hasNext()) {
                T currELem = iter.next();
                heap.insert(new Pair(currELem, index));
                if (heap.getRoot().element == currELem) {
                    iterIndex = index;
                }
            }
            index++;
        }
    }

    private class Pair implements Comparable<Pair> {
        private final T element;
        private final int index;

        public Pair(T element, int index) {
            this.element = element;
            this.index = index;
        }

        public T getElement() {
            return element;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare((Integer) this.element, (Integer) o.getElement());
        }
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
        if (heap.getSize() <= 0) {
            throw new NoSuchElementException();
        } else {
            Pair currPair = heap.extract();
            int currIndex = currPair.getIndex();
            iterIndex = currIndex;
            if (!iterators.get(currIndex).hasNext()) {
                int currIterIndex= 0;
                int prevIterIndex = iterIndex;
                for (Iterator<T> iter: iterators) {
                    if (iter.hasNext()) {
                        iterIndex = currIterIndex;
                        break;
                    }
                    currIterIndex++;
                }
                if (prevIterIndex == iterIndex) {
                    return currPair.element;
                }
            }
            heap.insert(new Pair(iterators.get(iterIndex).next(), iterIndex));
            return currPair.element;
        }
    }
}
