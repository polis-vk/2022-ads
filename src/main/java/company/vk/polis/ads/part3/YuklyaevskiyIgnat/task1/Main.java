package company.vk.polis.ads.part3.YuklyaevskiyIgnat.task1;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11739020
 * @author Dmitry Schitinin
 */
public final class Main {

    private Main() {
        // Should not be instantiated
    }
    private static class MyHeap {
        private int[] values;
        int size;
        MyHeap(int maxSize) {
            this.values = new int[maxSize + 1];
            this.size = 0;
        }
        void swim(int k) {
            while(k > 1 && values[k] > values[k/2]) {
                swap(values, k , k/2);
                k = k / 2;
            }
        }
        void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if(j < size && values[j] < values[j+1]) j++;
                if(values[k] >= values[j]) {
                    break;
                }
                swap(values, k, j);
                k = j;
            }
        }
        void insert(int v) {
            values[++size] = v;
            swim(size);
        }
        int extract() {
            int max = values[1];
            swap(values, 1, size--);
            sink(1);
            return max;
        }
        void swap(int[] array, int son, int father) {
            int temp = array[son];
            array[son] = array[father];
            array[father] = temp;
        }

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberCommands = in.nextInt();
        MyHeap heap = new MyHeap(numberCommands);
        for (int i = 0; i < numberCommands; i++) {
            int command = in.nextInt();

            if (command != 0) {
                out.println(heap.extract());
            } else {
                heap.insert(in.nextInt());
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