package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class TaskA {

    private static void radixSort(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n < 1) {
            return;
        }
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = in.nextInt();
        }

        int max = numbers[0];
        for (int i = 1; i < n; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(n, numbers, exp);
        }
        for (int i = 0; i < n; i++) {
            out.println(numbers[i]);
        }
    }

    private static void countSort(int n, int[] numbers, int exp) {
        int[] count = new int[10];
        int[] output = new int[n];
        for (int i = 0; i < n; i++) {
            count[(numbers[i] / exp) % 10]++;
        }
        // thus count array will contain the position of number in output array
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int digit = (numbers[i] / exp) % 10;
            output[count[digit] - 1] = numbers[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, numbers, 0, n);
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
            radixSort(in, out);
        }
    }
}
