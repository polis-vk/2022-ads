package company.vk.polis.ads.part3.kirill06344;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import company.vk.polis.ads.Heap;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class SimpleSorting {
    private SimpleSorting() {
        // Should not be instantiated
    }

    public static class Ascend implements Comparator<Integer> {

        @Override
        public int compare(Integer t1, Integer t2) {
            return t1 - t2;
        }
    }


    private static void heapSort(int[] arr) {
        Heap heap = new Heap(new Ascend());
        heap.buildHeapOnArray(arr);
        for (int i = arr.length - 1; i >= 0; --i) {
            arr[i] = heap.extract();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
        }
        heapSort(arr);
        for (int el : arr) {
            out.print(el + " ");
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
