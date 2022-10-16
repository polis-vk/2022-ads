package company.vk.polis.ads.part4.tedbear;

import java.io.*;
import java.util.StringTokenizer;

public class QuantityOfInversions {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int sizeOfInputArray = in.nextInt();
        int[] inputArray = new int[sizeOfInputArray];

        for (int i = 0; i < sizeOfInputArray; i++) {
            inputArray[i] = in.nextInt();
        }

        out.write(countInv(inputArray, sizeOfInputArray) + "");

    }

    public static int countInv(int[] a, int n) {
        if (n <= 1) {
            return 0;
        }

        int mid = n / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[n - mid];
        System.arraycopy(a, 0, leftArray, 0, mid);
        if (a.length - mid >= 0) System.arraycopy(a, mid, rightArray, 0, a.length - mid);

        return countInv(leftArray, mid) +
                countInv(rightArray, n - mid) +
                countSplitInv(a, leftArray, rightArray, mid, n - mid);
    }

    private static int countSplitInv(int[] a, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        int inversionCounter = 0;

        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                a[k++] = leftArray[i++];
            } else {
                a[k++] = rightArray[j++];
                inversionCounter += leftArray.length - i;
            }
        }

        while (i < left) {
            a[k++] = leftArray[i++];
        }
        while (j < right) {
            a[k++] = rightArray[j++];
        }

        return inversionCounter;
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
