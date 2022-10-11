//https://www.eolymp.com/ru/submissions/11749160

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InHeap {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            MaxHeap heap = new MaxHeap();
            int n = in.nextInt();
            IntStream.range(0, n).forEach(i -> {
                switch (in.nextInt()) {
                    case 0 -> heap.insert(in.nextInt());
                    case 1 -> System.out.println(heap.extract());
                }
            });
        }
    }

    private static class MaxHeap {
        private int[] heap;
        private int capacity;
        private int size;

        public MaxHeap() {
            capacity = 512;
            heap = new int[capacity];
            size = 0;
        }

        private int parent(int root) {
            return root / 2;
        }

        private int left(int root) {
            return root * 2;
        }

        private int right(int root) {
            return root + 1;
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void swim(int k) {
            while (k > 1 && heap[k] > heap[parent(k)]) {
                swap(k, parent(k));
                k = parent(k);
            }
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

        public void insert(int x) {
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

        public int extract() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            int max = heap[1];
            swap(1, size);
            size--;
            sink(1);
            return max;
        }
    }
}
