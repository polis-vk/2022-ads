package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// Submission link: https://www.eolymp.com/ru/submissions/11659599
public class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] data;
        private int n;

        public Heap() {
            data = new int[16];
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

            while (current > 1 && data[current] > data[parent]) {
                swap(current, parent);
                current = parent;
                parent >>= 1;
            }
        }

        private void sink(int i) {
            int parent = i;
            int current = i << 1;

            while (current <= n) {
                if (current < n && data[current] < data[current + 1]) {
                    current++;
                }

                if (data[parent] >= data[current]) {
                    break;
                }

                swap(parent, current);

                parent = current;

                current <<= 1;
            }
        }

        public int delMax() {
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
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        int command;

        while (n > 0) {
            command = in.nextInt();

            switch (command) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    System.out.println(heap.delMax());
                    break;
            }

            n--;
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
