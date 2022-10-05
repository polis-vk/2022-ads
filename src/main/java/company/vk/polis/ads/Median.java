package company.vk.polis.ads;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Median {
    private Median() {
        // Should not be instantiated
    }

    private static class IntHeap {
        private static final int INITIAL_SIZE = 16;
        private static final int EXPANSION_COEFFICIENT = 2;
        private Comparator<Integer> comparator;
        private int[] heap;
        private int n;
        public IntHeap(Comparator<Integer> comparator) {
            n = 0;
            heap = new int[INITIAL_SIZE];
            this.comparator = comparator;
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void sink(int k) {
            while (k * 2 <= n) {
                int j = k * 2;
                if (j < n && comparator.compare(heap[j + 1], heap[j]) > 0) {
                    j++;
                }
                if (comparator.compare(heap[k], heap[j]) >= 0) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
        private void swim(int k) {
            while (k > 1 && comparator.compare(heap[k >> 1], heap[k]) < 0) {
                swap(k >> 1, k);
                k >>= 1;
            }
        }
        public int popRoot() {
            int root = heap[1];
            swap(1, n--);
            sink(1);
            return root;
        }
        public int peekRoot() {
            return heap[1];
        }
        public void add(int value) {
            if (n == heap.length - 1) {
                heap = Arrays.copyOf(heap, heap.length * EXPANSION_COEFFICIENT);
            }
            heap[++n] = value;
            swim(n);
        }
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        IntHeap minHeap = new IntHeap((x, y) -> y - x);
        IntHeap maxHeap = new IntHeap((x, y) -> x - y);
        int median = Integer.MIN_VALUE;
        int t = 0;
        while (in.hasNextInt()) {
            int number = in.nextInt();
            if ((t & 1) == 0) {
                if (number <= median) {
                    maxHeap.add(number);
                    median = maxHeap.popRoot();
                } else {
                    minHeap.add(number);
                    median = minHeap.popRoot();
                }
            } else {
                if (number <= median) {
                    maxHeap.add(number);
                    minHeap.add(median);
                } else {
                    maxHeap.add(median);
                    minHeap.add(number);
                }
                median = (minHeap.peekRoot() + maxHeap.peekRoot()) >> 1;
            }
            System.out.println(median);
            t++;
        }
    }
}