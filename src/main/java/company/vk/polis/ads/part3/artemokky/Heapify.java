package company.vk.polis.ads.part3.artemokky;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Heapify {

    private final static int INIT_SIZE = 32;
    private static final int IN = 0;
    private static final int OUT = 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap(INIT_SIZE);

        int commandNum = in.nextInt();

        int in_or_out;
        int number;

        for (int i = 0; i < commandNum; i++) {
            in_or_out = in.nextInt();

            if (in_or_out == IN) {
                number = in.nextInt();
                heap.insert(number);
            } else if (in_or_out == OUT) {
                out.println(heap.extract());
            }
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class Heap {

        private int[] a;
        private int size;

        public Heap(int size) {
            this.a = new int[size];
            this.size = 0;
        }

        void insert(int element) {
            if (size == a.length - 1) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[++size] = element;
            swim(size);
        }

        int extract() {
            int max = a[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        void swap(int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        void swim(int index) {
            int k = index;

            while (k > 1 && a[k] > a[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j] < a[j + 1]) {
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
}