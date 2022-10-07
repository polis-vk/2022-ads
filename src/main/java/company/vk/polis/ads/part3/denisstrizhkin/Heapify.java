package company.vk.polis.ads.part3.denisstrizhkin;

import java.io.*;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11697005
public final class Heapify {
    private Heapify() {
        // Should not be instantiated
    }

    private static class IntHeap {
        private int[] array;
        private int size;
        private int capacity;

        public IntHeap() {
            this.clear();
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.capacity = 10;
            this.size = 0;
            this.array = new int[this.capacity];
        }

        public Integer delMax() {
            if (size == 0) {
                return null;
            }

            int max = array[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        public void insert(int num) {
            if (this.size + 1 == this.capacity) {
                this.resize();
            }

            array[++size] = num;
            swim(size);
        }

        private void resize() {
            int[] oldArray = this.array;
            this.capacity = (int) Math.ceil((double) this.capacity * 1.5);
            this.array = new int[this.capacity];

            for (int i = 0; i < this.size + 1; i++) {
                this.array[i] = oldArray[i];
            }
        }

        private void swim(int k) {
            while (k > 1 && array[k] > array[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && array[j] < array[j + 1]) {
                    j++;
                }

                if (array[k] >= array[j]) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void solve(final Heapify.FastScanner in, final PrintWriter out) {
        IntHeap heap = new IntHeap();

        int n = in.nextInt();
        while (n-- != 0) {
            int command = in.nextInt();
            switch (command) {
                case 0:
                    heap.insert(in.nextInt());
                    break;
                case 1:
                    out.println(heap.delMax());
                    break;
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
        final Heapify.FastScanner in = new Heapify.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}