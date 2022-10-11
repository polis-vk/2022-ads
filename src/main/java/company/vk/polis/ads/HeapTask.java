package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

class Heap {
    private int a[];
    private int n;
    private final int cap;

    public Heap(int cap) {
        this.a = new int[cap + 1];
        this.cap = cap;
    }

    private void swim(int k) {
        while (k > 1 && a[k] > a[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;// left child
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[k] >= a[j]) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    public void insert(int v) {
        a[++n] = v;
        swim(n);
    }

    public int delMax() {
        int max = a[1];
        swap(1, n--);
        sink(1);
        return max;
    }

    private void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HeapTask {
    private HeapTask() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i++) {
            int input = in.nextInt();
            switch (input) {
                case 0 -> heap.insert(in.nextInt());
                case 1 -> out.println(heap.delMax());
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

