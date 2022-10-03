package company.vk.polis.ads.part2.tedbear;

import java.io.*;
import java.util.StringTokenizer;

public class Scatter {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int quantityOfElements = in.nextInt();
        int[] sortedArray = new int[quantityOfElements];
        int[] freqArray = new int[106];

        for (int i = 0; i < quantityOfElements; i++) {
            sortedArray[i] = in.nextInt();
        }

        int minValue = sortedArray[0];

        for (int i = 0; i < quantityOfElements; i++) {
            if (sortedArray[i] < minValue) {
                minValue = sortedArray[i];
            }
        }

        for (int i : sortedArray) {
            freqArray[i - minValue]++;
        }

        int count;
        int sortedIndex = 0;


        for (int i = 0; i < freqArray.length; i++) {
            count = freqArray[i];

            for (int k = 0; k < count; k++) {
                sortedArray[sortedIndex] = i + minValue;
                sortedIndex++;
            }
        }

        for (int num : sortedArray) {
            out.write(num + " ");
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
