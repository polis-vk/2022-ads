import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {

    private final static int INIT_SIZE = 32;


    Main() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        HeapMax heapMax = new HeapMax(INIT_SIZE);
        HeapMin heapMin = new HeapMin(INIT_SIZE);

        int number;
        int median;

        while (in.hasNext()) {
            number = in.nextInt();

            if (heapMax.getSize() == 0 || heapMax.getRoot() > number) {
                heapMax.insert(number);
            } else {
                heapMin.insert(number);
            }

            if (heapMax.getSize() - heapMin.getSize() > 1) {
                heapMin.insert(heapMax.extract());
            } else if (heapMin.getSize() - heapMax.getSize() > 1) {
                heapMax.insert(heapMin.extract());
            }

            if (heapMax.getSize() == heapMin.getSize()) {
                median = heapMax.getRoot() + (heapMin.getRoot() - heapMax.getRoot()) / 2;
            } else {
                median = heapMax.getSize() > heapMin.getSize() ? heapMax.getRoot() : heapMin.getRoot();
            }

            out.println(median);
        }

    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class HeapMax {

        private int[] a;
        private int size;

        public HeapMax(int size) {
            this.a = new int[size];
            this.size = 0;
        }

        public void insert(int element) {
            if (size == a.length - 1) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[++size] = element;
            swim(size);
        }

        public int extract() {
            int max = a[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        void swap(int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        public int getRoot() {
            return a[1];
        }

        public int getSize() {
            return size;
        }

        void swim(int index) {
            int k = index;

            while (k > 1 && a[k] > a[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j] < a[j + 1]) {
                    j++;
                }
                if (a[k] >= a[j]) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }
    }

    private static class HeapMin {

        private int[] a;
        private int size;

        public HeapMin(int size) {
            this.a = new int[size];
            this.size = 0;
        }

        public void insert(int element) {
            if (size == a.length - 1) {
                a = Arrays.copyOf(a, a.length * 2);
            }
            a[++size] = element;
            swim(size);
        }

        public int extract() {
            int min = a[1];
            swap(1, size--);
            sink(1);
            return min;
        }

        void swap(int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        void swim(int index) {
            int k = index;

            while (k > 1 && a[k] < a[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        void sink(int index) {
            int k = index;
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j] > a[j + 1]) {
                    j++;
                }
                if (a[k] <= a[j]) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }

        public int getRoot() {
            return a[1];
        }

        public int getSize() {
            return size;
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
}