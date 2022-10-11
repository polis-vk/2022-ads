package company.vk.polis.ads.part3;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EolympMed {
    private EolympMed() {
        // Should not be instantiated
    }

    private abstract static class Heap {
        private final ArrayList<Integer> arr = new ArrayList<>();
        private int n = 0;
        private int size = 0;

        public int getSize() {
            return size;
        }

        public int getRoot() {
            return arr.get(1);
        }

        public void insert(int value) {
            while (arr.size() <= n * 2 + 1) {
                arr.add(-1);
            }
            arr.set(++n, value);
            swim(n);
            size++;
        }

        public int extract() {
            int result = arr.get(1);
            int j = (arr.get(n) > arr.get(n + 1)) ? n : n + 1;
            arr.set(1, arr.get(j));
            arr.set(j, -1);
            n--;
            size--;
            sink(1);
            return result;
        }

        abstract void swim(int k);

        abstract void sink(int k);

        private void swap(int k, int j) {
            int temp = arr.get(k);
            arr.set(k, arr.get(j));
            arr.set(j, temp);
        }
    }

    private static class MinHeap extends Heap {
        @Override
        public void swim(int k) {
            while (k > 1 && super.arr.get(k) < super.arr.get(k / 2)) {
                super.swap(k, k / 2);
                k = k / 2;
            }
        }

        @Override
        public void sink(int k) {
            while (2 * k <= super.n) {
                int j = 2 * k;
                int left = super.arr.get(j);
                int right = super.arr.get(j + 1);
                if (j < super.n && left > right && right >= 0) {
                    j++;
                }
                if (super.arr.get(k) <= super.arr.get(j)) {
                    break;
                }
                super.swap(k, j);
                k = j;
            }
        }
    }

    private static class MaxHeap extends Heap {
        @Override
        public void swim(int k) {
            while (k > 1 && super.arr.get(k) > super.arr.get(k / 2)) {
                super.swap(k, k / 2);
                k = k / 2;
            }
        }

        @Override
        public void sink(int k) {
            while (2 * k <= super.n) {
                int j = 2 * k;
                int left = super.arr.get(j);
                int right = super.arr.get(j + 1);
                if (j < super.n && left < right) {
                    j++;
                }
                if (super.arr.get(k) >= super.arr.get(j)) {
                    break;
                }
                super.swap(k, j);
                k = j;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();
        int index = 1;
        int currMed = -1;
        while (in.reader.ready()) {
            int element = in.nextInt();

            if (index == 1) {
                currMed = element;
            } else {
                if (element <= currMed) {
                    if (index % 2 == 0) {
                        minHeap.insert(currMed);
                    }
                    maxHeap.insert(element);
                } else {
                    if (index % 2 == 0) {
                        maxHeap.insert(currMed);
                    }
                    minHeap.insert(element);
                }
                if (index % 2 > 0) {
                    currMed = (minHeap.getSize() >= maxHeap.getSize()) ? minHeap.extract() : maxHeap.extract();
                } else {
                    currMed = (minHeap.getRoot() + maxHeap.getRoot()) / 2;
                }
            }

            index++;
            out.println(currMed);
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
    } // https://www.eolymp.com/ru/submissions/11706618
}