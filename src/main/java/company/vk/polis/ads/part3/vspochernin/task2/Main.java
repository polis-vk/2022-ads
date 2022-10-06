package company.vk.polis.ads.part3.vspochernin.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11684905
 *
 * @author Dmitry Schitinin
 */
public final class Main {

    private Main() {
        // Should not be instantiated
    }

    private static class Heap {

        private final int[] a;
        private int size;
        private final boolean isMaxHeap;

        public Heap(int capacity, boolean isMaxHeap) {
            this.a = new int[capacity + 1];
            this.size = 0;
            this.isMaxHeap = isMaxHeap;
        }

        private void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        private void swim(int index) {
            int k = index;

            while (k > 1 && (isMaxHeap && a[k] > a[k / 2] || !isMaxHeap && a[k] < a[k / 2])) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int index) {
            int k = index;

            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && (isMaxHeap && a[j + 1] > a[j] || !isMaxHeap && a[j + 1] < a[j])) {
                    j++;
                }
                if (isMaxHeap && a[k] >= a[j] || !isMaxHeap && a[k] <= a[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        public void insert(int x) {
            a[++size] = x;
            swim(size);
        }

        public int extract() {
            int result = a[1];

            swap(1, size--);
            sink(1);

            return result;
        }

        public int peek() {
            if (size == 0) {
                return 0;
            }
            return a[1];
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap maxHeap = new Heap(500_000, true);
        Heap minHeap = new Heap(500_000, false);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int median = n;
        out.println(median);
        boolean isSeparatedMedian = true;
        int[] a = new int[3];

        while (scanner.hasNextInt()) {
            n = scanner.nextInt();
            if (isSeparatedMedian) {
                if (n > median) {
                    minHeap.insert(n);
                    maxHeap.insert(median);
                } else {
                    minHeap.insert(median);
                    maxHeap.insert(n);
                }
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                a[0] = maxHeap.extract();
                a[1] = n;
                a[2] = minHeap.extract();
                if (a[0] > a[1]) {
                    swap(a, 0, 1);
                }
                if (a[1] > a[2]) {
                    swap(a, 1, 2);
                }
                if (a[0] > a[1]) {
                    swap(a, 0, 1);
                }
                maxHeap.insert(a[0]);
                median = a[1];
                minHeap.insert(a[2]);
            }
            isSeparatedMedian = !isSeparatedMedian;
            out.println(median);
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
