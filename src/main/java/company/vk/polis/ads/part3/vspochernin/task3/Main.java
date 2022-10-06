package company.vk.polis.ads.part3.vspochernin.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11685138
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

        public Heap(int[] a) {
            this.size = a.length - 1; // Подразумевается, что нам будет подан массив с "лишним" нулевым элементом.
            this.a = a;

            for (int k = size / 2; k >= 1; k--) {
                sink(k);
            }
        }

        public void sort() {
            while (size > 1) {
                swap(1, size--);
                sink(1);
            }
        }

        public void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        public void sink(int index) {
            int k = index;

            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j + 1] > a[j]) {
                    j++;
                }
                if (a[k] >= a[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        Heap heap = new Heap(a);
        heap.sort();
        for (int i = 1; i < n; i++) {
            out.print(a[i] + " ");
        }
        out.println(a[n]);
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
