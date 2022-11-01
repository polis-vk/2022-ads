package company.vk.polis.ads.workshop;

import java.util.stream.IntStream;

public class Heapsort {
    public static Integer[] heapsort(Integer[] array) {
        Integer[] sortingArray = new Integer[array.length + 1];
        System.arraycopy(array, 0, sortingArray, 1, array.length);
        Heap heap = new Heap(sortingArray);
        heap.sort();
        System.arraycopy(sortingArray, 1, array, 0, array.length);
        return array;
    }

    private static class Heap {
        private final Integer[] heap;
        private int size;

        public Heap(Integer[] heap) {
            size = heap.length - 1;
            this.heap = heap;
            IntStream.iterate(size / 2, k -> k >= 1, k -> k - 1).forEach(this::sink);
        }

        public void sort() {
            while (size > 1) {
                swap(1, size--);
                sink(1);
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void sink(int k) {
            while (left(k) <= size) {
                int j = left(k);
                if (j < size && heap[j] < heap[right(j)]) {
                    j++;
                }
                if (heap[k] >= heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private int left(int root) {
            return root * 2;
        }

        private int right(int root) {
            return root + 1;
        }
    }
}
