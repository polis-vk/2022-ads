package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/11669248
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] data;
        private int n;
        private final Comparator<Integer> comparator;

        public Heap(Comparator<Integer> comparator) {
            data = new int[16];
            this.comparator = comparator;
        }


        private void expand() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        private void swap(int i, int j) {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }

        private void swim(int i) {
            int current = i;
            int parent = i >> 1;

            while (current > 1 && comparator.compare(data[current], data[parent]) > 0) {
                swap(current, parent);
                current = parent;
                parent >>= 1;
            }
        }

        private void sink(int i) {
            int parent = i;
            int current = i << 1;

            while (current <= n) {
                if (current < n && comparator.compare(data[current], data[current + 1]) < 0) {
                    current++;
                }

                if (comparator.compare(data[parent], data[current]) >= 0) {
                    break;
                }

                swap(parent, current);

                parent = current;

                current <<= 1;
            }
        }

        public int get() {
            return data[1];
        }

        public int delete() {
            int max = data[1];
            swap(1, n);
            n--;
            sink(1);
            return max;
        }

        public void insert(int value) {
            if (n >= data.length - 1) {
                expand();
            }
            n++;
            data[n] = value;
            swim(n);
        }

        public int size() {
            return n;
        }
    }

    private static int equaliseAndGetMedian(Heap minHeap, Heap maxHeap) {
        int n;

        if (minHeap.size() > maxHeap.size() + 1) {
            while (minHeap.size() > maxHeap.size() + 1) {
                n = minHeap.delete();
                maxHeap.insert(n);
            }
        } else if (minHeap.size() < maxHeap.size() - 1) {
            while (minHeap.size() < maxHeap.size() - 1) {
                n = maxHeap.delete();
                minHeap.insert(n);
            }
        }

        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.get() + maxHeap.get()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.get();
        } else {
            return maxHeap.get();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);

        Heap minHeap = new Heap((o1, o2) -> o2 - o1);
        Heap maxHeap = new Heap((o1, o2) -> o1 - o2);

        int n;
        int median = 0;

        while (scanner.hasNext()) {
            n = scanner.nextInt();

            if (n <= median) {
                maxHeap.insert(n);
            } else {
                minHeap.insert(n);
            }

            median = equaliseAndGetMedian(minHeap, maxHeap);

            System.out.println(median);
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
