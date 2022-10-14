import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    Main() {

    }


    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static void sink(int[] a, int i, int n) {

        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && a[j] < a[j + 1]) {
                j++;
            }
            if (a[i] >= a[j]) {
                break;
            }

            swap(a, i, j);
            i = j;
        }
    }

    static void heapSort(int[] a) {
        int n = a.length - 1;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }
        while (n > 1) {
            swap(a, 1, n--);
            sink(a, 1, n);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int arrSize = in.nextInt() + 1;
        int[] arr = new int[arrSize];

        for (int i = 1; i < arrSize; i++) {
            arr[i] = in.nextInt();
        }

        heapSort(arr);

        for (int i = 1; i < arrSize; i++) {
            out.print(arr[i]);
            out.print(" ");
        }

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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