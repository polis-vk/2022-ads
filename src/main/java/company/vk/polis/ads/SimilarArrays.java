//package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

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
        final int N = in.nextInt();
        int[] firstArray = new int[N];
        for (int i = 0; i < N; i++) {
            firstArray[i] = in.nextInt();
        }
        final int K = in.nextInt();
        int[] secondArray = new int[K];
        for (int i = 0; i < K; i++) {
            secondArray[i] = in.nextInt();
        }
        quickSort(firstArray);
        quickSort(secondArray);
        out.println(isSimilar(firstArray, secondArray) ? "YES" : "NO");
    }

    public static boolean isSimilar(int[] firstArray, int[] secondArray) {
        int i = 0;
        int j = 0;
        while (i < firstArray.length && j < secondArray.length) {
            i = lastEqualIndex(firstArray, i);
            j = lastEqualIndex(secondArray, j);
            if (firstArray[i] != secondArray[j]) {
                return false;
            }
            i++;
            j++;
        }
        if (i < firstArray.length || j < secondArray.length) {
            return false;
        }
        return true;
    }

    public static int lastEqualIndex(int[] array, int fromInclusive) {
        int lastIndex = fromInclusive;
        while (lastIndex < array.length - 1 && array[lastIndex] == array[lastIndex + 1]) {
            lastIndex++;
        }
        return lastIndex;
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1, new Random());
    }

    private static void quickSort(int[] array, int fromInclusive,
                                  int toInclusive, Random random) {
        if (toInclusive <= fromInclusive)
            return;
        int partitionIndex = partition(array, fromInclusive, toInclusive, random);
        quickSort(array, fromInclusive, partitionIndex - 1, random);
        quickSort(array, partitionIndex + 1, toInclusive, random);
    }

    private static int partition(int[] array, int fromInclusive,
                                 int toInclusive, Random random) {
        int pivotIndex = random.nextInt(toInclusive - fromInclusive + 1) + fromInclusive;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, toInclusive);
        int i = fromInclusive - 1;
        for (int j = fromInclusive; j <= toInclusive; j++) {
            if (array[j] <= pivot) {
                swap(array, ++i, j);
            }
        }
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
