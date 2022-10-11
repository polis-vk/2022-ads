package company.vk.polis.ads.part3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HeapSort {
    private HeapSort() {
        // Should not be instantiated
    }

    private static class Heap {
        private final ArrayList<Integer> arr = new ArrayList<>();
        private int n = 0;

        public void insert(int value) {
            while (arr.size() <= n * 2 + 1) {
                arr.add(-1);
            }
            arr.set(++n, value);
            swim(n);
        }

        public int extract() {
            int result = arr.get(1);
            int j = (arr.get(n) > arr.get(n + 1)) ? n : n + 1;
            arr.set(1, arr.get(j));
            arr.set(j, -1);
            n--;
            sink(1);
            return result;
        }

        public void swim(int k) {
            while (k > 1 && arr.get(k) < arr.get(k / 2)) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        public void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                int left = arr.get(j);
                int right = arr.get(j + 1);
                if (j < n && left > right && right >= 0) {
                    j++;
                }
                if (arr.get(k) <= arr.get(j)) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        private void swap(int k, int j) {
            int temp = arr.get(k);
            arr.set(k, arr.get(j));
            arr.set(j, temp);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < count; i++) {
            int currElem = in.nextInt();
            heap.insert(currElem);
        }
        for (int i = 0; i < count; i++) {
            System.out.print(heap.extract() + ((i < count - 1) ? " ": ""));
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
    } // https://www.eolymp.com/ru/submissions/11706863
}
