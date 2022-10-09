package company.vk.polis.ads.part3.nikitry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
//https://www.eolymp.com/ru/submissions/11722966
public final class Heap {

    private final List<Integer> heap = new ArrayList<>();

    public Heap() {
        heap.add(0);
    }

    private void sink(int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && heap.get(j) < heap.get(j + 1)) j++;
            if (heap.get(k) > heap.get(j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int k, int j) {
        int temp = heap.get(k);
        heap.set(k, heap.get(j));
        heap.set(j, temp);
    }

    private void swim(int pos) {
        while (pos > 1 && heap.get(pos) > heap.get(pos / 2)) {
            swap(pos, pos / 2);
            pos = pos / 2;
        }
    }

    private void insert(int input) {
        heap.add(input);
        swim(heap.size() - 1);
    }

    private int extract() {
        int max = heap.get(1);
        swap(1, size());
        heap.remove(size());
        sink(1, size());
        return max;
    }

    private int size() {
        return heap.size() - 1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap heap = new Heap();
        int commandsAmount = in.nextInt();
        for (int i = 1; i <= commandsAmount; i++) {
            int command = in.nextInt();
            switch (command) {
                case 0 -> heap.insert(in.nextInt());
                case 1 -> out.write(heap.extract() + "\n");
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
