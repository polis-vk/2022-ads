package company.vk.polis.ads.paikeee.part3;

import java.util.Arrays;
import java.util.Scanner;


// проходят только два теста
public final class Median {

    private Median() {

    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);
        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();
        int median = 0;
        int index = 1;
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n == -1) {
                break;
            }
            if (index == 1) {
                median = n;
            } else if (index % 2 == 0) {
                if (median > n) {
                    minHeap.insert(median);
                    maxHeap.insert(n);
                } else {
                    minHeap.insert(n);
                    maxHeap.insert(median);
                }
                median = (minHeap.peek() + maxHeap.peek()) / 2;
            } else {
                if (n > median) {
                    median = minHeap.extract();
                    minHeap.insert(n);
                } else {
                    median = maxHeap.extract();
                    maxHeap.insert(n);
                }
            }
            index++;
        }
        System.out.println(median);
    }

    private static class MaxHeap {

        int[] heap;
        int size;
        private int initialCapacity = 16;

        public MaxHeap() {
            heap = new int[initialCapacity + 1];
            size = 0;
        }

        int peek() {
            return heap[1];
        }

        void insert(int elem) {
            if (size == initialCapacity) {
                initialCapacity *= 2;
                heap = Arrays.copyOf(heap, initialCapacity + 1);
            }
            heap[++size] = elem;
            swim(size);
        }

        int extract() {
            int max = heap[1];
            swap(1, size--);
            sink();
            return max;
        }

        private void sink() {
            int parent = 1;
            while (size >= parent * 2) {
                int child = parent * 2;
                if (child < size && heap[child] < heap[child + 1]) {
                    child++;
                }
                if (heap[parent] >= heap[child]) {
                    break;
                }
                swap(parent, child);
                parent = child;
            }
        }

        private void swim(int child) {
            while (child > 1 && heap[child] > heap[child / 2]) {
                swap(child, child / 2);
                child /= 2;
            }
        }

        private void swap(int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
    }

    private static class MinHeap {

        int[] heap;
        int size;
        private int initialCapacity = 16;

        public MinHeap() {
            heap = new int[initialCapacity + 1];
            size = 0;
        }

        int peek() {
            return heap[1];
        }

        void insert(int elem) {
            if (size == initialCapacity) {
                initialCapacity *= 2;
                heap = Arrays.copyOf(heap, initialCapacity + 1);
            }
            heap[++size] = elem;
            swim(size);
        }

        int extract() {
            int min = heap[1];
            swap(1, size--);
            sink();
            return min;
        }

        private void sink() {
            int parent = 1;
            while (size >= parent * 2) {
                int child = parent * 2;
                if (child < size && heap[child] > heap[child + 1]) {
                    child++;
                }
                if (heap[parent] <= heap[child]) {
                    break;
                }
                swap(parent, child);
                parent = child;
            }
        }

        private void swim(int child) {
            while (child > 1 && heap[child] < heap[child / 2]) {
                swap(child, child / 2);
                child /= 2;
            }
        }

        private void swap(int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }
    }
}