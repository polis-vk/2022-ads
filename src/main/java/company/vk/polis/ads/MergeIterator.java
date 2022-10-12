package company.vk.polis.ads;

import java.util.ArrayList;
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
    private final MinHeap<Pair<T>> minHeap = new MinHeap<>();

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        for (Iterator<T> iterator : iterators) {
            if (iterator.hasNext()) {
                minHeap.insert(new Pair<>(iterator.next(), iterator));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return minHeap.list.size() - 1 > 0;
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
        } else {
            Pair<T> pair = minHeap.extract();
            if (pair.iterator.hasNext()) {
                minHeap.insert(new Pair<>(pair.iterator.next(), pair.iterator));
            } else {
                for (Iterator<T> iterator : iterators) {
                    if (iterator.hasNext()) {
                        minHeap.insert(new Pair<>(iterator.next(), iterator));
                        break;
                    }
                }
            }
            return pair.data;
        }
    }

    private static class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
        private final T data;
        private final Iterator<T> iterator;

        public Pair(T data, Iterator<T> iter) {
            this.data = data;
            iterator = iter;
        }

        @Override
        public int compareTo(Pair<T> o) {
            return data.compareTo(o.data);
        }
    }


    public static class MinHeap<T extends Comparable<T>> {
        private final List<T> list;

        public MinHeap() {
            list = new ArrayList<>();
            list.add(null);
        }

        public void insert(T x) {
            list.add(x);
            swim(list.size() - 1);
        }


        public T extract() {
            T result = list.get(1);
            swap(1, list.size() - 1);
            list.remove(list.size() - 1);
            sink(1);
            return result;
        }


        private void sink(int index) {
            int parent = index;
            while (2 * parent <= list.size() - 1) {
                int child = 2 * parent;
                if (child < (list.size() - 1) && list.get(child).compareTo(list.get(child + 1)) > 0) {
                    child++;
                }
                if (list.get(parent).compareTo(list.get(child)) <= 0) {
                    break;
                }
                swap(parent, child);
                parent = child;
            }
        }

        protected void swim(int index) {
            while (index > 1 && list.get(index).compareTo(list.get(index / 2)) < 0) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void swap(int left, int right) {
            T temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
        }
    }
}

