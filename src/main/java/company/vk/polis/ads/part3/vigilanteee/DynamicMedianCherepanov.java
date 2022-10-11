package company.vk.polis.ads.part3.vigilanteee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class DynamicMedianCherepanov {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();
        int median;
        int number;
        while (in.reader.ready()) {
            number = in.nextInt();
            if (!minHeap.isEmpty() && minHeap.peek() > number) {
                maxHeap.insert(number);
                if (maxHeap.getSize() > minHeap.getSize() + 1) {
                    minHeap.insert(maxHeap.extract());
                }
            } else {
                minHeap.insert(number);
                if (minHeap.getSize() > maxHeap.getSize() + 1) {
                    maxHeap.insert(minHeap.extract());
                }
            }
            if (maxHeap.getSize() > minHeap.getSize()) {
                median = maxHeap.peek();
            } else if (minHeap.getSize() > maxHeap.getSize()) {
                median = minHeap.peek();
            } else {
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            }
            out.println(median);
        }
    }

    static class MinHeap {

        private static final int DEFAULT_CAPACITY = 10;
        private int[] minHeap;
        private int size;

        public MinHeap() {
            this.minHeap = new int[DEFAULT_CAPACITY];
        }

        public int getSize() {
            return size;
        }

        public int peek() {
            return minHeap[0];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(int x) {
            if (this.size >= this.minHeap.length) {
                this.minHeap = this.resize();
            }
            size++;
            minHeap[size - 1] = x;
            siftUp(size - 1);
        }

        public int extract() {
            int min = minHeap[0];
            minHeap[0] = minHeap[size - 1];
            size--;
            siftDown(0);
            return min;
        }

        private int[] resize() {
            int newCapacity = minHeap.length * 3 / 2 + 1;
            return Arrays.copyOf(minHeap, newCapacity);
        }

        private void swap(int a, int b) {
            int temp = minHeap[a];
            minHeap[a] = minHeap[b];
            minHeap[b] = temp;
        }

        private void siftUp(int k) {
            while (k > 0 && minHeap[k] < minHeap[(k - 1) / 2]) {
                swap(k, (k - 1) / 2);
                k = (k - 1) / 2;
            }
        }

        private void siftDown(int k) {
            while (2 * k + 1 < size) {
                int left = 2 * k + 1;
                int right = 2 * k + 2;
                int j = left;
                if (right < size && minHeap[j] > minHeap[right]) {
                    j++;
                }
                if (minHeap[k] <= minHeap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

    }

    static class MaxHeap {

        private static final int DEFAULT_CAPACITY = 10;
        private int[] maxHeap;
        private int size;

        public MaxHeap() {
            this.maxHeap = new int[DEFAULT_CAPACITY];
        }

        public int getSize() {
            return size;
        }

        public int peek() {
            return maxHeap[0];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insert(int x) {
            if (this.size >= this.maxHeap.length) {
                this.maxHeap = this.resize();
            }
            size++;
            maxHeap[size - 1] = x;
            siftUp(size - 1);
        }

        public int extract() {
            int min = maxHeap[0];
            maxHeap[0] = maxHeap[size - 1];
            size--;
            siftDown(0);
            return min;
        }

        private int[] resize() {
            int newCapacity = maxHeap.length * 3 / 2 + 1;
            return Arrays.copyOf(maxHeap, newCapacity);
        }

        private void swap(int a, int b) {
            int temp = maxHeap[a];
            maxHeap[a] = maxHeap[b];
            maxHeap[b] = temp;
        }

        private void siftUp(int k) {
            while (k > 0 && maxHeap[k] > maxHeap[(k - 1) / 2]) {
                swap(k, (k - 1) / 2);
                k = (k - 1) / 2;
            }
        }

        private void siftDown(int k) {
            while (2 * k + 1 < size) {
                int left = 2 * k + 1;
                int right = 2 * k + 2;
                int j = left;
                if (right < size && maxHeap[j] < maxHeap[right]) {
                    j++;
                }
                if (maxHeap[k] >= maxHeap[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
