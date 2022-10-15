package company.vk.polis.ads.part4.denisstrizhkin.inversions;

import java.io.*;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11802185
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int[] arr;

    private static int countSplitInv(int i, int j, int mid) {
        int numInv = 0;

        int[] arrL = new int[mid - i + 1];
        System.arraycopy(arr, i, arrL, 0, arrL.length);

        int[] arrR = new int[j - mid];
        System.arraycopy(arr, mid + 1, arrR, 0, arrR.length);

        int iL = 0;
        int iR = 0;
        while(iL < arrL.length && iR < arrR.length) {
            if (arrL[iL] <= arrR[iR]) {
                arr[i++] = arrL[iL++];
            } else {
                numInv += arrL.length - iL;
                arr[i++] = arrR[iR++];
            }
        }

        while(iL < arrL.length) {
            arr[i++] = arrL[iL++];
        }

        while(iR < arrR.length) {
            arr[i++] = arrR[iR++];
        }

        return numInv;
    }

    private static int countInv(int i, int j) {
        if (i >= j) {
            return 0;
        }

        int mid = i + (j - i) / 2;
        return countInv(i, mid) + countInv(mid + 1, j) + countSplitInv(i, j, mid);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        out.println(countInv(0, arr.length - 1));
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