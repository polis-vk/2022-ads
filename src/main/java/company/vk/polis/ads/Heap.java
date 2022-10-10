package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

//https://www.eolymp.com/ru/submissions/11743114
public final class Heap {
    private List<Integer> a;
    private int n;

    public Heap() {
        a = new ArrayList<>();
        a.add(-1);
        n = 0;
    }

    public void insert(int value) {
        a.add(value);
        swim(++n);
    }

    public int extract() {
        int max = a.get(1);
        swap(1, n);
        a.remove(n);
        n--;
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && a.get(k) > a.get(k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a.get(j) < a.get(j + 1)) j++;
            if (a.get(k) >= a.get(j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int m, int n) {
        int temp = a.get(m);
        a.set(m, a.get(n));
        a.set(n, temp);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < n; i++) {
            int command = in.nextInt();
            if (command == 0) {
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
