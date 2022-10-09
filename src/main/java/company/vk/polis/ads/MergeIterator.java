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
    MinHeap<Element<T>> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        heap = new MinHeap<Element<T>>(iterators.size());
        int cnt = 0;
        for (Iterator<T> iterator: iterators){
            if (iterator.hasNext()){
                heap.add(new Element<>(iterator.next(), cnt));
            }
            cnt++;
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
        var ansElement = heap.pop();
        var iterator = iterators.get(ansElement.getSourceIndex());
        if (iterator.hasNext()){
            heap.add(new Element<>(iterator.next(), ansElement.getSourceIndex()));
        }
        return ansElement.getValue();
    }

}
