package company.vk.polis.ads.part2.tedbear;

import java.io.*;
import java.util.StringTokenizer;

public class Palindrome {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int quantity = in.nextInt();
        String sequenceOfLetters = in.next();
        int[] freqArray = new int[26];
        int shift = 65;
        char letter;

        for (int i = 0; i < quantity; i++) {
            letter = sequenceOfLetters.charAt(i);
            freqArray[letter - shift]++;
        }

        for (int i = 0; i < freqArray.length; i++) {
            for (int j = 1; j <= freqArray[i] / 2; j++) {
                out.write((char) (i + shift) + "");
            }
        }

        for (int i = 0; i < freqArray.length; i++) {
            if (freqArray[i] % 2 == 1) {
                out.write((char) (i + shift) + "");
                break;
            }
        }

        for (int i = freqArray.length - 1; i >= 0; i--) {
            for (int j = 1; j <= freqArray[i] / 2; j++) {
                out.write((char) (i + shift) + "");
            }
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
