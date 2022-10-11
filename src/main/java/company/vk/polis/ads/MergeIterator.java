package company.vk.polis.ads;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * Iterator that merges k input iterators ordered ascending.
 * Each value returned by #next() is greater than or equal to previous one.
 * Total cost of iteration is O(n logk).
 *
 * @param <T> type of elements
 */
public final class MergeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final List<Iterator<T>> iterators;
    private final MinHeap<Pair<T>> minHeap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        minHeap = new MinHeap<>();
        IntStream.range(0, iterators.size()).filter(i -> iterators.get(i).hasNext()).mapToObj(i -> new Pair<>(iterators.get(i).next(), i)).forEach(minHeap::insert);
    }

    @Override
    public boolean hasNext() {
        return iterators.stream().anyMatch(Iterator::hasNext) || minHeap.getSize() != 0;
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
        Pair<T> pair = minHeap.popMin();
        if (iterators.get(pair.iterator).hasNext()) {
            minHeap.insert(new Pair<>(iterators.get(pair.iterator).next(), pair.iterator));
        } else {
            IntStream.range(0, iterators.size()).filter(i -> iterators.get(i).hasNext()).findFirst().ifPresent(i -> minHeap.insert(new Pair<>(iterators.get(i).next(), i)));
        }
        return pair.value;
    }


    private record Pair<T extends Comparable<T>>(T value, int iterator) implements Comparable<Pair<T>> {

        @Override
        public int compareTo(Pair<T> pair) {
            return this.value.compareTo(pair.value);
        }
    }
}