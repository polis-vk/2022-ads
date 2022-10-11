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
    private final HeapMin<Pair<T>> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        this.heap = new HeapMin<>(iterators.size());
        for (Iterator<T> it : iterators) {
            if (it.hasNext()) {
                heap.insert(new Pair<>(it, it.next()));
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
        if (!hasNext()){
            throw new NoSuchElementException();
        }

        Pair<T> pair = heap.extract();
        Iterator<T> iterator = pair.iterator;
        T value = pair.value;

        if (iterator.hasNext()) {
            heap.insert(new Pair<>(iterator, iterator.next()));
        } else {
            for (Iterator<T> it : iterators) {
                if (it.hasNext()) {
                    heap.insert(new Pair<>(it, it.next()));
                    break;
                }
            }
        }
        return value;
    }

    private static class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
        private final Iterator<T> iterator;
        private final T value;

        public Pair(Iterator<T> iterator, T value) {
            this.iterator = iterator;
            this.value = value;
        }

        @Override
        public int compareTo(Pair<T> o) {
            return this.value.compareTo(o.value);
        }
    }
}
