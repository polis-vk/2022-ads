package company.vk.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Heap {

    // https://www.eolymp.com/ru/submissions/11729012
    private final List<Integer> a;

    public Heap() {
        a = new ArrayList<>();
        a.add(0);
    }

    public void insert(int x) {
        a.add(x);
        swim(size());
    }

    public int extract() {
        int max = a.get(1);
        swap(1, size());
        a.remove(size());
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (2 * k <= size()) {
            int j = 2 * k; // left child
            if (j < size() && a.get(j) < a.get(j + 1)) {
                j++; //right child
            }
            if (a.get(k) >= a.get(j)) {
                break; // invariant holds
            }
            swap(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && a.get(k) > a.get(k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void swap(int k1, int k2) {
        int temp = a.get(k1);
        a.set(k1, a.get(k2));
        a.set(k2, temp);
    }

    private int size() {
        return a.size() - 1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < n; i++) {
            int cmd = in.nextInt();
            switch (cmd) {
                case (0) -> heap.insert(in.nextInt());
                case (1) -> out.println(heap.extract());
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
