import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main1 { // heap

    private static int[] array;
    private static int size = 0;

    private static void swim(int k) {
        while (k > 1 && array[k] > array[k / 2]) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private static void sink(int k) {
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

    private static void swap(int k, int halfK) {
        int temp = array[k];
        array[k] = array[halfK];
        array[halfK] = temp;
    }

    public static void insert(int elem) {
        array[++size] = elem;
        swim(size);
    }

    private static int extract() {
        int max = array[1];
        swap(1, size--);
        sink(1);
        return max;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeCommand = in.nextInt();
        array = new int[sizeCommand + 1];
        while (sizeCommand > 0) {
            int nextInt = in.nextInt();
            if (nextInt == 0) {
                insert(in.nextInt());
            } else {
                out.println(extract());
            }
            sizeCommand--;
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
