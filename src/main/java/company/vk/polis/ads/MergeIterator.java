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
    private final Heap minHeap;

    private class Temp {
        private final T value;
        private final Iterator<T> iterator;

        public Temp(T val, Iterator<T> iter) {
            this.value = val;
            this.iterator = iter;
        }

        public T value() {
            return value;
        }

        public Iterator<T> iterator() {
            return iterator;
        }
    }

    public class Heap {

        private final List<Temp> heapList;
        private int currentSize;

        public Heap(int size) {
            this.currentSize = 0;
            this.heapList = new ArrayList<>();
            for (int i = 0; i < size + 1; i++) {
                heapList.add(new Temp(null, null));
            }
        }

        public void insert(Temp value) {
            heapList.set(++currentSize, value);
            swim(currentSize);
        }

        public Temp exctract() {
            Temp root = heapList.get(1);
            Collections.swap(heapList, 1, currentSize--);
            sink(1);
            return root;
        }

        private void swim(int k) {
            while (k > 1 && heapList.get(k).value().compareTo(heapList.get(k / 2).value()) < 0) {
                Collections.swap(heapList, k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= currentSize) {
                int j = 2 * k;
                if (j < currentSize && heapList.get(j + 1).value().compareTo(heapList.get(j).value()) < 0) {
                    j++;
                }
                if (heapList.get(k).value().compareTo(heapList.get(j).value()) <= 0) {
                    break;
                }
                Collections.swap(heapList, k, j);
                k = j;
            }
        }

        public int getSize() {
            return currentSize;
        }
    }

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        minHeap = new Heap(iterators.size());
        for (Iterator<T> element : iterators) {
            if (element.hasNext()) {
                minHeap.insert(new Temp(element.next(), element));
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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var minElement = minHeap.exctract();
        var iterator = minElement.iterator();
        if (iterator.hasNext()) {
            minHeap.insert(new Temp(iterator.next(), iterator));
        }
        return minElement.value();
    }
}
