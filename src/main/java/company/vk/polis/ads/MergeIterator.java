package company.vk.polis.ads;

import java.util.Arrays;
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
    private MinHeap<Pair<T>> heap;

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        makeHeap(iterators);
    }

    @Override
    public boolean hasNext() {
        return heap.size > 0;
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
        Pair<T> pair = heap.extract();
        if (pair.getIterator().hasNext()) {
            T nextValue = pair.getIterator().next();
            heap.insert(new Pair<T>(nextValue, pair.getIterator()));
        } else {
            for (Iterator<T> iterator : iterators) {
                if (iterator.hasNext()) {
                    T nextValue = iterator.next();
                    heap.insert(new Pair<T>(nextValue, iterator));
                    break;
                }
            }
        }
        return pair.getValue();
    }

    private void makeHeap(List<Iterator<T>> iterators) {
        heap = new MinHeap<Pair<T>>(iterators.size());
        for (var iterator : iterators) {
            if (iterator.hasNext()) {
                heap.insert(new Pair<T>(iterator.next(), iterator));
            }
        }
    }

    private static class Pair<T extends Comparable<T>> implements Comparable<Pair<T>> {
        private final T value;
        private final Iterator<T> iterator;

        public Pair(T value, Iterator<T> iterator) {
            this.iterator = iterator;
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Iterator<T> getIterator() {
            return iterator;
        }

        @Override
        public int compareTo(Pair<T> o) {
            return this.getValue().compareTo(o.getValue());
        }
    }

    private static class MinHeap<T extends Comparable<T>> {
        private T[] heap;
        private int size;
        private int arrCapacity;

        public MinHeap(int arrCapacity) {
            this.arrCapacity = arrCapacity;
            heap = (T[]) new Comparable<?>[arrCapacity];
            size = 0;
        }

        public T extract() {
            T max = heap[1];
            swap(heap, 1, size--);
            sink(1);
            return max;
        }

        public T peek() {
            return heap[1];
        }

        public void insert(T x) {
            if (size + 1 >= arrCapacity) {
                increaseCapacity();
            }
            heap[++size] = x;
            swim(size);
        }

        private void swim(int child) {
            while (child > 1 && heap[child].compareTo(heap[child / 2]) < 0) {
                swap(heap, child, child / 2);
                child /= 2;
            }
        }

        private void sink(int parent) {
            while (2 * parent <= size) {
                int child = 2 * parent;
                if (child < size && heap[child].compareTo(heap[child + 1]) > 0) {
                    child++;
                }
                if (heap[parent].compareTo(heap[child]) <= 0) {
                    break;
                }
                swap(heap, parent, child);
                parent = child;
            }
        }

        private void increaseCapacity() {
            arrCapacity = (arrCapacity * 3 / 2) + 1;
            heap = Arrays.copyOf(heap, arrCapacity);
        }

        private void swap(T[] arr, int a, int b) {
            T temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
