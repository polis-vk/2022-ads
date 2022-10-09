import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main3 { //Heap sort

    private static int[] array;
    private static int size = 0;


    private static void swap(int k, int halfK) {
        int temp = array[k];
        array[k] = array[halfK];
        array[halfK] = temp;
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

    private static void makeHeap(int[] arrayMin) {
        size = arrayMin.length - 1;
        for (int k = size / 2; k >= 1; k--) {
            sink(k);
        }
    }

    private static void sort() {
        while (size > 1) {
            swap(1, size--);
            sink(1);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        array = new int[in.nextInt() + 1];

        for (int i = 1; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        makeHeap(array);
        sort();

        for (int i = 1; i < array.length; i++) {
            out.print(array[i] + " ");
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
