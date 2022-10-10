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
     class Pair implements Comparable<Pair> {
        private final int index;
        private final T data;

        public Pair(int indexOfMassive, T data){
            this.data = data;
            index = indexOfMassive;
        }

        public int getIndex() {
            return index;
        }

        public T getData() {
            return data;
        }

        @Override
        public int compareTo(Pair o) {
            return data.compareTo(o.getData());
        }
    }

    private final List<Iterator<T>> iterators;
    private final MinHeap<Pair> minHeap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        minHeap = new MinHeap<>(iterators.size());
        for (int i = 0; i < iterators.size(); i++){
            if (iterators.get(i).hasNext()){
                Pair heapPair = new Pair(i, iterators.get(i).next());
                minHeap.insert(heapPair);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return minHeap.getSize() > 0;
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
        Pair result = minHeap.extract();
        if (!iterators.get(result.getIndex()).hasNext()){
            for (int i = 0; i < iterators.size(); i++){
                if (iterators.get(i).hasNext()){
                    Pair heapPair = new Pair(i, iterators.get(i).next());
                    minHeap.insert(heapPair);
                    break;
                }
            }
        }
        else {
            int indexOfResult = result.getIndex();
            Pair heapPair = new Pair(indexOfResult, iterators.get(indexOfResult).next());
            minHeap.insert(heapPair);
        }

        return result.getData();
    }

}
