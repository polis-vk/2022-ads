package company.vk.polis.ads.part3;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EolympHeap {
    private EolympHeap() {
        // Should not be instantiated
    }

    private static class Heap {
        private int[] arr;
        private int n = 0;

        public Heap(int size) {
            this.arr = new int[size * 2];
        }

        public void insert(int value) {
            arr[++n] = value;
            swim(n);
        }

        public int extract() {
            int result = arr[1];
            int j = (arr[n] > arr[n + 1]) ? n : n + 1;
            arr[1] = arr[j];
            arr[j] = 0;
            n--;
            sink(1);
            return result;
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (arr[k] >= arr[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && arr[k] > arr[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void swap(int k, int j) {
            int temp = arr[k];
            arr[k] = arr[j];
            arr[j] = temp;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int count = in.nextInt();
        Heap heap = new Heap(count);
        for (int i = 0; i < count; i++) {
            String[] currStr = in.reader.readLine().split(" ");
            if (Integer.parseInt(currStr[0]) == 0) { // insert
                heap.insert(Integer.parseInt(currStr[1]));
            } else { // extract
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    } // https://www.eolymp.com/ru/submissions/11690265
}