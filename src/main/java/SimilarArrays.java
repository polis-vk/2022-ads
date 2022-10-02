import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class SimilarArrays {
    private SimilarArrays() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] arrayOne;
        int[] arrayTwo;
        int arrayOneSize = in.nextInt();
        arrayOne = new int[arrayOneSize];
        fillArray(arrayOne, in);
        int arrayTwoSize = in.nextInt();
        arrayTwo = new int[arrayTwoSize];
        fillArray(arrayTwo, in);
        quickSort(arrayOne, 0, arrayOneSize - 1);
        quickSort(arrayTwo, 0, arrayTwoSize - 1);
        arrayOneSize = filterArray(arrayOne, arrayOneSize);
        arrayTwoSize = filterArray(arrayTwo, arrayTwoSize);
        int[] newArrayOne = Arrays.copyOfRange(arrayOne, 0, arrayOneSize);
        int[] newArrayTwo = Arrays.copyOfRange(arrayTwo, 0, arrayTwoSize);
        out.println(check(newArrayOne, newArrayTwo));
    }

    private static void fillArray(int[] array, final FastScanner in) {
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
    }

    private static int filterArray(int[] array, int size) {
        if (size == 0 || size == 1) {
            return size;
        }
        int j = 0;
        for (int i = 0; i < size - 1; i++) {
            if (array[i] != array[i + 1]) {
                array[j++] = array[i];
            }
        }
        array[j++] = array[size - 1];
        return j;
    }

    private static String check(int[] arrayOne, int[] arrayTwo) {
        return Arrays.equals(arrayOne, arrayTwo) ? "YES" : "NO";
    }

    private static void quickSort(int[] array, int start, int end) {
        int divideIndex;
        if (start < end) {
            divideIndex = split(array, start, end);
            quickSort(array, start, divideIndex - 1);
            quickSort(array, divideIndex, end);
        }
    }

    private static int split(int[] array, int start, int end) {
        int leftIndex = start;
        int rightIndex = end;
        int pivot = array[start + (end - start) / 2];
        while (leftIndex <= rightIndex) {
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }
            while (array[rightIndex] > pivot) {
                rightIndex--;
            }
            if (leftIndex <= rightIndex) {
                swap(array, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(int[] array, int leftIndex, int rightIndex) {
        int temp;
        temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
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