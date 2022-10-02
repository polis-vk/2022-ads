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
public final class SimilarArrays {
    private static void qSort(int[] array, int low, int high) {
        int i = low;
        int j = high;
        int x = array[low + (high - low) / 2];
        do {
            while (array[i] < x) i++;
            while (array[j] > x) j--;
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (low < j) qSort(array, low, j);
        if (i < high) qSort(array, i, high);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array1 = new int[in.nextInt()];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = in.nextInt();
        }
        int[] array2 = new int[in.nextInt()];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = in.nextInt();
        }
        qSort(array1, 0, array1.length - 1);
        qSort(array2, 0, array2.length - 1);
        if (array1[0] != array2[0] || array1[array1.length - 1] != array2[array2.length - 1]) {
            out.write("NO");
            return;
        }
        int[] longArray;
        int[] shortArray;
        if (array1.length >= array2.length) {
            longArray = array1;
            shortArray = array2;
        } else {
            longArray = array2;
            shortArray = array1;
        }
        int indexForShortArray = 0;
        int indexForLongArray = 0;
        boolean flag = false;
        int lastDigit = 0;
        for (int i = 0; i < longArray.length; i++) {
            if (longArray[indexForLongArray] == shortArray[indexForShortArray]) {
                flag = true;
                if (indexForShortArray == shortArray.length - 1) {
                    indexForLongArray++;
                    break;
                }
                lastDigit = longArray[indexForLongArray];
                indexForLongArray++;
                indexForShortArray++;
            } else if (longArray[indexForLongArray] < shortArray[indexForShortArray]) {
                flag = false;
                if (lastDigit != longArray[indexForLongArray]) {
                    break;
                }
                indexForLongArray++;
            } else {
                flag = false;
                if (lastDigit != shortArray[indexForShortArray]) {
                    break;
                }
                indexForShortArray++;
            }
        }
        if (flag) {
            out.write("YES");
            return;
        }
        out.write("NO");
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
