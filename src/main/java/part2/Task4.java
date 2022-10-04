package part2;

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
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        int[] arrayTwo = new int[in.nextInt()];
        for (int i = 0; i < arrayTwo.length; i++) {
            arrayTwo[i] = in.nextInt();
        }
        int low = 0;
        int high = array.length - 1;
        int highTwo = arrayTwo.length - 1;
        quickSort(array, low, high);
        quickSort(arrayTwo, low, highTwo);
        contains(array, arrayTwo, out);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;
        int opElement = array[low + (high - low) / 2];
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opElement) {
                i++;
            }
            while (array[j] > opElement) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(array, low, j);
        }
        if (high > i) {
            quickSort(array, i, high);
        }
    }

    private static void contains(int[] array, int[] arrayTwo, final PrintWriter out) {
        int i = 0;
        int j = 0;
        while (i < array.length && j < arrayTwo.length) {
            while (i != array.length - 1 && array[i] == array[i + 1]) {
                i++;
            }
            while (j != arrayTwo.length - 1 && arrayTwo[j] == arrayTwo[j + 1]) {
                j++;
            }
            if (array[i] != arrayTwo[j]) {
                out.println("NO");
                return;
            }
            i++;
            j++;
        }
        if (i == array.length && j == arrayTwo.length) {
            out.println("YES");
        } else {
            out.println("NO");
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
