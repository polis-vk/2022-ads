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

//https://www.eolymp.com/ru/submissions/11743119
public final class HeapSort {
    private List<Integer> a;
    private int n;

    public HeapSort() {
        a = new ArrayList<>();
        a.add(-1);
        n = 0;
    }

    public void insert(int value) {
        a.add(value);
        n++;
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

    public List<Integer> getA() {
        return a;
    }

    private void swap(int m, int n) {
        int temp = a.get(m);
        a.set(m, a.get(n));
        a.set(n, temp);
    }

    public void heapSort() {
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        while (n > 1) {
            swap(1, n--);
            sink(1);
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        HeapSort heap = new HeapSort();

        for (int i = 0; i < n; i++) {
            heap.insert(in.nextInt());
        }
        heap.heapSort();
        List<Integer> temp = heap.getA();
        for (int i = 1; i < temp.size(); i++) {
            System.out.print(temp.get(i) + " ");
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
