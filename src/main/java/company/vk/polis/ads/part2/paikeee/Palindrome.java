package company.vk.polis.ads.part2.paikeee;

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
public final class Palindrome {

    private static final int ALPHABET_SIZE = 26;
    private static final int CHAR_TO_INT = 65;

    private Palindrome() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int n = in.nextInt();
        String string = in.reader.readLine();
        int[] charCount = new int[ALPHABET_SIZE];
        for (int i = 0; i < n; i++) {
            charCount[string.charAt(i) - CHAR_TO_INT]++;
        }
        StringBuilder answer = new StringBuilder();
        int minChar = ALPHABET_SIZE;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (minChar == ALPHABET_SIZE && charCount[i] % 2 == 1) {
                minChar = i;
                charCount[i]--;
            }
            if (charCount[i] / 2 > 0) {
                if (charCount[i] % 2 == 1) {
                    charCount[i]--;
                }
                charCount[i] /= 2;
                answer.append(String.valueOf((char) (i + CHAR_TO_INT)).repeat(charCount[i]));
            } else {
                charCount[i] = 0;
            }
        }
        if (minChar != ALPHABET_SIZE) {
            answer.append((char) (minChar + CHAR_TO_INT));
        }
        for (int i = ALPHABET_SIZE - 1; i >= 0; i--) {
            if (charCount[i] != 0) {
                answer.append(String.valueOf((char) (i + CHAR_TO_INT)).repeat(charCount[i]));
            }
        }
        out.println(answer);
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
