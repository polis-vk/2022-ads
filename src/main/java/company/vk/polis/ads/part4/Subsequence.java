package company.vk.polis.ads.part4;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/11801734
 */
public final class Subsequence {
    private Subsequence() {
        // Should not be instantiated
    }

    private static void initArr(int count, int[] arr, final FastScanner in) {
        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count1 = in.nextInt();
        int[] arr1 = new int[count1];
        initArr(count1, arr1, in);

        int count2 = in.nextInt();
        int[] arr2 = new int[count2];
        initArr(count2, arr2, in);

        int[] commonArr = new int[count1];
        for (int i = 0; i < count2; i++) {
            boolean oneIterFlag = true;
            int prevMax = 0;
            for (int j = 0; j < count1; j++) {
                if (arr1[j] == arr2[i] && commonArr[j] <= j && oneIterFlag) {
                    commonArr[j] = prevMax + 1;
                    oneIterFlag = false;
                } else {
                    if (oneIterFlag) {
                        if (j == 0 || commonArr[j] > prevMax) {
                            prevMax = commonArr[j];
                        }
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < count1; i++) {
            if (max < commonArr[i]) {
                max = commonArr[i];
            }
        }

        out.println(max);
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
