import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        HeapMax heapLeft = new HeapMax(1000000);
        HeapMin heapRight = new HeapMin(1000000);
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) return;
        int first = scanner.nextInt();
        out.println(first);

        if (!scanner.hasNextInt()) return;
        int second = scanner.nextInt();
        int median = (first + second) / 2;
        out.println(median);

        heapLeft.insert(Math.min(first, second));
        heapRight.insert(Math.max(first, second));
        int x = 0;
        boolean medianIsCalculated = true;

        while (scanner.hasNextInt()) {
            x = scanner.nextInt();
            if (medianIsCalculated) {
                if (x > median) {
                    heapRight.insert(x);
                    median = heapRight.extract();
                } else {
                    heapLeft.insert(x);
                    median = heapLeft.extract();
                }
            } else {
                if (x > median) {
                    heapRight.insert(x);
                    heapLeft.insert(median);
                } else {
                    heapLeft.insert(x);
                    heapRight.insert(median);
                }
                median = (heapLeft.peek() + heapRight.peek()) / 2;
            }
            medianIsCalculated = !medianIsCalculated;
            out.println(median);
        }
    }

    private static class HeapMax {
        int[] array;
        int n;

        public HeapMax(int capacity) {
            this.array = new int[capacity + 1];
        }

        public void insert(int x) {
            array[++n] = x;
            swim(n);
        }

        public int extract() {
            int max = array[1];
            swap(1, n--);
            sink(1);
            return max;
        }

        public int peek() {
            return array[1];
        }

        void swim(int k) {
            while (k > 1 && array[k] > array[k/2]) {
                swap(k, k/2);
                k = k/2;
            }
        }

        void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && array[j] < array[j + 1]) j++;
                if (array[k] >= array[j]) break;
                swap(k, j);
                k = j;
            }
        }

        void swap(int k1, int k2) {
            int temp = array[k1];
            array[k1] = array[k2];
            array[k2] = temp;
        }
    }

    private static final class HeapMin extends HeapMax {
        public HeapMin(int capacity) {
            super(capacity);
        }

        @Override
        void swim(int k) {
            while (k > 1 && array[k] < array[k/2]) {
                swap(k, k/2);
                k = k/2;
            }
        }

        @Override
        void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && array[j] > array[j + 1]) j++;
                if (array[k] <= array[j]) break;
                swap(k, j);
                k = j;
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
