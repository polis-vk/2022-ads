package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskD {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end - 1) {
            return;
        }
        int index = partition(arr, begin, end);
        quickSort(arr, begin, index);
        quickSort(arr, index + 1, end);
    }

    private static int partition(int[] arr, int begin, int end) {
        int i = begin;
        int buff = arr[begin];
        arr[begin] = arr[i];
        arr[i] = buff;
        int last = arr[begin];
        for (int j = begin + 1; j < end; j++) {
            if (arr[j] <= last) {
                i++;
                buff = arr[i];
                arr[i] = arr[j];
                arr[j] = buff;
            }
        }
        buff = arr[i];
        arr[i] = arr[begin];
        arr[begin] = buff;
        return i;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int lengthFirstArray = in.nextInt();
        int[] firstArray = new int[lengthFirstArray];
        for (int i = 0; i < lengthFirstArray; i++) {
            firstArray[i] = in.nextInt();
        }
        int lengthSecondArray = in.nextInt();
        int[] secondArray = new int[lengthSecondArray];
        for (int i = 0; i < lengthSecondArray; i++) {
            secondArray[i] = in.nextInt();
        }
        quickSort(firstArray, 0, lengthFirstArray);
        quickSort(secondArray, 0, lengthSecondArray);
        int i = 0;
        int j = 0;
        while (i < firstArray.length && j < secondArray.length) {
            while (i + 1 < firstArray.length && firstArray[i + 1] == firstArray[i]) {
                i++;
            }
            while (j + 1 < secondArray.length && secondArray[j + 1] == secondArray[j]) {
                j++;
            }
            if (firstArray[i] != secondArray[j]) {
                System.out.println("NO");
                return;
            }
            i++;
            j++;
        }
        if (i < firstArray.length || j < secondArray.length) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
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
