package company.vk.polis.ads.part2.tedbear;

import java.io.*;
import java.util.StringTokenizer;

public class SimilarArrays {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me

        int[] firstArray = fillArray(in);
        int[] secondArray = fillArray(in);

        sort(firstArray, 0, firstArray.length);
        sort(secondArray, 0, secondArray.length);

        int[] firstArrayWithoutRepeats = deleteRepeats(firstArray);
        int[] secondArrayWithoutRepeats = deleteRepeats(secondArray);

        out.write(similar(firstArrayWithoutRepeats, secondArrayWithoutRepeats));
    }

    public static int[] deleteRepeats(int[] array) {
        int[] arrayWithoutRepeats = new int[array.length];
        int element = array[0];
        int i = 0;

        if (array.length == 1) {
            arrayWithoutRepeats[i] = element;
        }

        for (int j = 1; j < array.length; j++) {
            if (j == array.length - 1 && element != array[j]) {
                arrayWithoutRepeats[i++] = element;
                arrayWithoutRepeats[i] = array[j];
            }else if (j == array.length - 1 && element == array[j]) {
                arrayWithoutRepeats[i] = element;
            } else if (array[j] != element) {
                arrayWithoutRepeats[i++] = element;
                element = array[j];
            }
        }

        return arrayWithoutRepeats;
    }

    public static String similar(int[] firstArray, int[] secondArray) {
        int n = Math.min(firstArray.length, secondArray.length);

        if (firstArray[n - 1] != secondArray[n - 1] || firstArray[0] != secondArray[0]) {
            return "NO";
        }

        for (int i = 0; i < n; i++) {
            if (firstArray[i] != secondArray[i]) {
                return "NO";
            }
        }

        return "YES";
    }

    public static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }

        int i = partition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, i);
        sort(array, i + 1, toExclusive);
    }

    public static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;

        for(int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);

        return i;
    }

    public static void swap(int[] array, int firstElementIndex, int secondElementIndex) {
        int temp = array[firstElementIndex];
        array[firstElementIndex] = array[secondElementIndex];
        array[secondElementIndex] = temp;
    }

    public static int[] fillArray(final FastScanner in) {
        int quantity = in.nextInt();
        int[] filledArray = new int[quantity];
        for(int i = 0; i < quantity; i++){
            filledArray[i] = in.nextInt();
        }

        return filledArray;
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
