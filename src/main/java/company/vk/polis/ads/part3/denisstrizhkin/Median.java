package company.vk.polis.ads.part3.denisstrizhkin;

import java.io.*;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11697756
public final class Median {
    private Median() {
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

        public Integer max() {
            if (size == 0) {
                return null;
            }

            return array[1];
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

    private static void solve(final Median.FastScanner in, final PrintWriter out) {
        IntHeap heapMax = new IntHeap();
        IntHeap heapMin = new IntHeap();

        int num;
        int median = 0;
        int n = 0;
        while (true) {
            try {
                num = in.nextInt();
            } catch (NullPointerException e) {
                break;
            }

            if (n == 0) {
                median = num;
            } else if (n % 2 == 1) {
                heapMax.insert(Math.min(median, num));
                heapMin.insert(-Math.max(median, num));
                median = heapMax.max() + (-heapMin.max() - heapMax.max()) / 2;
            } else if (num > median) {
                heapMin.insert(-num);
                median = -heapMin.delMax();
            } else {
                heapMax.insert(num);
                median = heapMax.delMax();
            }

            n++;
            out.println(median);
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
        final Median.FastScanner in = new Median.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}