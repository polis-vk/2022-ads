package company.vk.polis.ads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class MinHeap<T extends Comparable<T>> {
    private static final int INITIAL_SIZE = 16;
    private static final int EXPANSION_COEFFICIENT = 13;
    private int n;
    private Object[] heap;
    public MinHeap() {
        heap = new Object[INITIAL_SIZE];
        n = 0;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public void add(T element) {
        if (n == heap.length - 1) {
            heap = Arrays.copyOf(heap, heap.length * EXPANSION_COEFFICIENT);
        }
        heap[++n] = (Object) element;
        swim(n);
    }
    public T popRoot() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T root = getCasted(1);
        swap(1, n--);
        sink(1);
        return root;
    }
    private void sink(int k) {
        while (k * 2 <= n) {
            int j = k * 2;
            if (j < n && getCasted(j + 1).compareTo(getCasted(j)) < 0) {
                j++;
            }
            if (getCasted(k).compareTo(getCasted(j)) <= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }
    private void swim(int k) {
        while (k > 1 && getCasted(k).compareTo(getCasted(k / 2)) < 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }
    private void swap(int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private T getCasted(int index) {
        return (T)heap[index];
    }
}

class Wrapper<T extends Comparable<T>> implements Comparable<Wrapper<T>> {
    private int index;
    private T value;
    public Wrapper(int index, T value) {
        this.index = index;
        this.value = value;
    }
    public int getIndex() {
        return index;
    }
    public T getValue() {
        return value;
    }

    @Override
    public int compareTo(Wrapper<T> o) {
        return value.compareTo(o.value);
    }
}


/**
 * Iterator that merges k input iterators ordered ascending.
 * Each value returned by #next() is greater than or equal to previous one.
 * Total cost of iteration is O(n logk).
 *
 * @param <T> type of elements
 */
public final class MergeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final List<Iterator<T>> iterators;
    private final MinHeap<Wrapper<T>> minHeap = new MinHeap<>();
    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        if (minHeap.isEmpty()) {
            for (int i = 0; i < iterators.size(); i++) {
                if (iterators.get(i).hasNext()) {
                    minHeap.add(new Wrapper<>(i, iterators.get(i).next()));
                }
            }
        }
        return !minHeap.isEmpty();
    }

    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        if (minHeap.isEmpty()) {
            for (int i = 0; i < iterators.size(); i++) {
                if (iterators.get(i).hasNext()) {
                    minHeap.add(new Wrapper<>(i, iterators.get(i).next()));
                }
            }
        }
        Wrapper<T> rootWrapper = minHeap.popRoot();
        if (iterators.get(rootWrapper.getIndex()).hasNext()) {
            minHeap.add(new Wrapper<>(rootWrapper.getIndex(), iterators.get(rootWrapper.getIndex()).next()));
        }
        return rootWrapper.getValue();
    }
}
