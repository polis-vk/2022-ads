package company.vk.polis.ads;

import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> heap = new MinHeap<>(k);
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext() && heap.size() < k) {
            heap.add(iterator.next());
        }
        T currElement;
        while (iterator.hasNext()) {
            currElement = iterator.next();
            if (heap.peek().compareTo(currElement) < 0) {
                heap.remove();
                heap.add(currElement);
            }
        }

        List<T> largest = new ArrayList<>();
        while (heap.size() > 0) {
            largest.add(heap.remove());
        }
        return largest.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}

class MinHeap<T extends Comparable<T>> {
    private static final int ROOT_INDEX = 1;
    private T[] values;
    private int size;

    public MinHeap(int capacity) {
        values = (T[]) new Comparable<?>[capacity + 1];
    }

    public void add(T element) {
        if (size == values.length - 1) {
            values = Arrays.copyOf(values, values.length * 3 / 2);
        }
        values[++size] = element;
        swim(size);
    }

    public T peek() {
        return values[ROOT_INDEX];
    }

    public T remove() {
        T root = values[ROOT_INDEX];
        swap(ROOT_INDEX, size--);
        sink(ROOT_INDEX);
        return root;
    }

    public int size() {
        return size;
    }

    private void swap(int firstIndex, int secondIndex) {
        T temp = values[firstIndex];
        values[firstIndex] = values[secondIndex];
        values[secondIndex] = temp;
    }

    private void swim(int index) {
        int currIndex = index;
        while (currIndex > 1 && values[currIndex].compareTo(values[currIndex / 2]) < 0) {
            swap(currIndex, currIndex / 2);
            currIndex /= 2;
        }
    }

    private void sink(int index) {
        int currIndex = index;
        int childIndex;
        while (2 * currIndex <= size) {
            childIndex = 2 * currIndex;
            if (childIndex < size && values[childIndex].compareTo(values[childIndex + 1]) > 0) {
                childIndex++;
            }
            if (values[currIndex].compareTo(values[childIndex]) <= 0) {
                break;
            }
            swap(childIndex, currIndex);
            currIndex = childIndex;
        }
    }
}

