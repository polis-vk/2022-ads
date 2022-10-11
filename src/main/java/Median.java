//https://www.eolymp.com/ru/submissions/11748820

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Median {
    private static final MinHeap minHeap = new MinHeap();
    private static final MaxHeap maxHeap = new MaxHeap();

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                add(in.nextInt());
                System.out.println(getMedian());
            }
        }
    }

    static void add(int num) {
        if (minHeap.getSize() == maxHeap.getSize()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    static int getMedian() {
        return minHeap.getSize() > maxHeap.getSize() ? minHeap.peek() : (minHeap.peek() + maxHeap.peek()) / 2;
    }

    private abstract static class Heap {
        private int[] heap;
        private int capacity;
        private int size;

        public Heap() {
            capacity = 512;
            heap = new int[capacity];
            size = 0;
        }

        int getSize() {
            return size;
        }

        int parent(int root) {
            return root / 2;
        }

        int left(int root) {
            return root * 2;
        }

        int right(int root) {
            return root + 1;
        }

        void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        void offer(int x) {
            if (size + 1 == capacity) {
                int[] temp = heap;
                capacity = capacity + capacity / 2;
                heap = new int[capacity];
                if (this.size + 1 >= 0) {
                    System.arraycopy(temp, 0, heap, 0, this.size + 1);
                }
            }
            size++;
            heap[size] = x;
            swim(size);
        }

        public int poll() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            int max = heap[1];
            swap(1, size);
            size--;
            sink(1);
            return max;
        }

        public int peek() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            return heap[1];
        }

        abstract void swim(int k);

        abstract void sink(int k);
    }

    private static class MaxHeap extends Heap {

        @Override
        void swim(int k) {
            while (k > 1 && super.heap[k] > super.heap[parent(k)]) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        @Override
        void sink(int k) {
            while (left(k) <= super.size) {
                int j = left(k);
                if (j < super.size && super.heap[j] < super.heap[right(j)]) {
                    j++;
                }
                if (super.heap[k] >= super.heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }

    private static class MinHeap extends Heap {

        @Override
        void swim(int k) {
            while (k > 1 && super.heap[k] < super.heap[parent(k)]) {
                swap(k, parent(k));
                k = parent(k);
            }
        }

        @Override
        void sink(int k) {
            while (left(k) <= super.size) {
                int j = left(k);
                if (j < super.size && super.heap[j] > super.heap[right(j)]) {
                    j++;
                }
                if (super.heap[k] <= super.heap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }
}