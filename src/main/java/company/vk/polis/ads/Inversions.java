package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Inversions {

    // https://www.eolymp.com/ru/submissions/11781843

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        temp = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        out.println(countInv(a, 0, n));
    }

    private static int countInv(int[] a, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }
        int mid = i + ((j - i) >> 1);
        return countInv(a, i, mid) +
                countInv(a, mid, j) +
                countSplitInv(a, i, j, mid);
    }

    private static int[] temp;

    private static int countSplitInv(int[] a, int i, int j, int mid) {
        int leftBorder = i;
        int rightBorder = mid;

        int inversions = 0;

        while (leftBorder < mid && rightBorder < j) {
            if (a[leftBorder] >= a[rightBorder]) {
                temp[leftBorder + rightBorder - i - mid] = a[rightBorder];
                rightBorder++;
                inversions += mid - leftBorder; // длина левого подмассива - leftBorder

            } else {
                temp[leftBorder + rightBorder - i - mid] = a[leftBorder];
                leftBorder++;
            }
        }
        while (leftBorder < mid) {
            temp[leftBorder + rightBorder - i - mid] = a[leftBorder];
            leftBorder++;
        }
        while (rightBorder < j) {
            temp[leftBorder + rightBorder - i - mid] = a[rightBorder];
            rightBorder++;
        }
        for (leftBorder = i; leftBorder < j; leftBorder++) {
            a[leftBorder] = temp[leftBorder - i];
        }
        return inversions;
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
