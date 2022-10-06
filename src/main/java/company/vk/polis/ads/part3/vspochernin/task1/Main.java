package company.vk.polis.ads.part3.vspochernin.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Main {

    private Main() {
        // Should not be instantiated
    }

    private static class Heap {

        private int[] a;
        private int size;

        public Heap(int capacity) {
            this.a = new int[capacity + 1];
            this.size = 0;
        }

        public void insert(int x) {
            a[++size] = x;
            swim(size);
        }

        public int extract() {
            int max = a[1];

            swap(1, size--);
            sink(1);

            return max;
        }

        public void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        public void swim(int index) {
            int k = index;

            while (k > 1 && a[k] > a[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        public void sink(int index) {
            int k = index;

            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j + 1] > a[j]) {
                    j++;
                }
                if (a[k] >= a[j]) break;
                swap(k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            int number = in.nextInt();
            if (number == 0) {
                heap.insert(in.nextInt());
            } else {
                out.println(heap.extract());
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
