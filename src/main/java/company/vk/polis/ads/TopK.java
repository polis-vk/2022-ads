package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        MinHeap<T> minHeap = new MinHeap<>(k);
        for (int i = 0; i < k; i++) {
            minHeap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            T currentElement = list.get(i);
            if (currentElement.compareTo(minHeap.peek()) > 0) {
                minHeap.extract();
                minHeap.insert(currentElement);
            }
        }

        List<T> topK = new ArrayList<>();
        while (minHeap.size > 0) {
            topK.add(minHeap.extract());
        }
        return topK.stream().sorted(Comparator.reverseOrder()).toList();
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
                if (child < size && heap[child].compareTo(heap[child + 1]) < 0) {
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
