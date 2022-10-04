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
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    public static class Pair implements Comparable<Pair> {
        private final int key;
        private final int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            return this.value != o.value ? o.value - this.value : this.key - o.key;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Pair[] array = new Pair[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Pair(in.nextInt(), in.nextInt());
        }
        mergeSort(array);
        for (Pair element : array) {
            out.println(element.key + " " + element.value);
        }
    }

    private static void mergeSort(Pair[] arr) {
        if (arr.length < 2) {
            return;
        }
        int midIndex = arr.length / 2;
        Pair[] leftArray = new Pair[midIndex];
        Pair[] rightArray = new Pair[arr.length - midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftArray[i] = arr[i];
        }
        for (int i = midIndex; i < arr.length; i++) {
            rightArray[i - midIndex] = arr[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(arr, leftArray, rightArray);
    }

    private static void merge(Pair[] array, Pair[] leftArray, Pair[] rightArray) {
        int i = 0, j = 0, k = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i].compareTo(rightArray[j]) < 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
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
