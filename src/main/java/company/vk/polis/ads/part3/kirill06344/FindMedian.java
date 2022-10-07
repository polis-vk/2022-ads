package company.vk.polis.ads.part3.kirill06344;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class FindMedian {
    private FindMedian() {
        // Should not be instantiated
    }

    private static final float GOLDEN_RATIO = (float) 1.62;

    public static class Ascend implements Comparator<Integer> {

        @Override
        public int compare(Integer t1, Integer t2) {
            return t1 - t2;
        }
    }

    public static class Descend implements Comparator<Integer> {

        @Override
        public int compare(Integer t1, Integer t2) {
            return t2 - t1;
        }
    }


    private static class HeapWithComparator {
        private int[] storage;
        private int heapSize;

        private int capacity;

        Comparator<Integer> cmp;

        public HeapWithComparator(Comparator<Integer> cmp) {
            this(10, cmp);
        }

        public HeapWithComparator(int size, Comparator<Integer> cmp) {
            capacity = size * 2;
            storage = new int[capacity];
            heapSize = 0;
            this.cmp = cmp;
        }

        public void insert(int x) {
            if (heapSize + 1 > capacity) {
                resize();
            }
            storage[heapSize++] = x;
            swim(heapSize - 1);
        }

        public int extract() {
            int max = storage[0];
            swap(--heapSize, 0);
            sink(0);
            return max;
        }

        public int getTop() {
            return storage[0];
        }

        private void sink(int k) {
            int left = getLeft(k);
            int right = getRight(k);
            int largest = k;

            if (left < heapSize && cmp.compare(storage[left], storage[k]) > 0) {
                largest = left;
            }

            if (right < heapSize && cmp.compare(storage[right], storage[largest]) > 0) {
                largest = right;
            }

            if (largest != k) {
                swap(largest, k);
                sink(largest);
            }
        }

        private void swim(int k) {
            while (k > 0 && cmp.compare(storage[k], storage[getParent(k)]) > 0) {
                swap(k, getParent(k));
                k = getParent(k);
            }
        }

        private int getParent(int k) {
            return (k - 1) / 2;
        }

        private int getLeft(int k) {
            return 2 * k + 1;
        }

        private int getRight(int k) {
            return 2 * k + 2;
        }

        private void swap(int i, int j) {
            int tmp = storage[i];
            storage[i] = storage[j];
            storage[j] = tmp;
        }

        private void resize() {
            int newCapacity = (int) (capacity * GOLDEN_RATIO);
            int[] tmp = new int[newCapacity];
            for (int i = 0; i < capacity; ++i) {
                tmp[i] = storage[i];
            }
            capacity = newCapacity;
            storage = tmp;
        }
    }

    private static class SequenceMedian {
        private HeapWithComparator minHeap;
        private HeapWithComparator maxHeap;
        private int currentMedian;
        private int size;

        public SequenceMedian() {
            minHeap = new HeapWithComparator(new Descend());
            maxHeap = new HeapWithComparator(new Ascend());
            currentMedian = 0;
            size = 0;
        }

        public void add(int elem) {
            ++size;
            if (size % 2 == 0) {
                if (elem <= currentMedian) {
                    maxHeap.insert(elem);
                    minHeap.insert(currentMedian);
                } else {
                    minHeap.insert(elem);
                    maxHeap.insert(currentMedian);
                }
                currentMedian = (maxHeap.getTop() + minHeap.getTop()) / 2;
            } else {
                if (elem <= currentMedian) {
                    maxHeap.insert(elem);
                    currentMedian = maxHeap.extract();
                } else {
                    minHeap.insert(elem);
                    currentMedian = minHeap.extract();
                }
            }
        }

        public int getCurrentMedian() {
            return currentMedian;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        SequenceMedian sequenceMedian = new SequenceMedian();
        while (scanner.hasNextInt()) {
            int el = scanner.nextInt();
            sequenceMedian.add(el);
            out.println(sequenceMedian.getCurrentMedian());
        }
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }


    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

