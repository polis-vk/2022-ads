package company.vk.polis.ads.paikee.part4;

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
public final class Brackets {

    static int[][] table, result;

    private Brackets() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String sequence = in.reader.readLine();
        int sequenceLength = sequence.length();
        table = new int[sequenceLength][sequenceLength];
        result = new int[sequenceLength][sequenceLength];
        for (int i = 0; i < sequenceLength; i++) {
            for (int j = 0; j < sequenceLength; j++) {
                table[i][j] = Integer.MAX_VALUE;
                result[i][j] = -1;
            }
        }
        count(0, sequenceLength - 1, sequence);
        printResult(0, sequenceLength - 1, sequence, out);
    }

    private static int count(int l, int r, String s) {
        if (l == r) {
            return 1;
        }
        if (l > r) {
            return 0;
        }
        if (table[l][r] != Integer.MAX_VALUE) {
            return table[l][r];
        }
        if (s.charAt(l) == '(' && s.charAt(r) == ')' || s.charAt(l) == '[' && s.charAt(r) == ']') {
            table[l][r] = Math.min(table[l][r], count(l + 1, r - 1, s));
        }
        for (int k = l; k < r; k++) {
            int temp = count(l, k, s) + count(k + 1, r, s);
            if (temp < table[l][r]) {
                result[l][r] = k;
                table[l][r] = temp;
            }
        }
        return table[l][r];
    }

    static void printResult(int l, int r, String s, final PrintWriter out) {
        if (l > r) {
            return;
        }
        if (l == r) {
            if (s.charAt(l) == '(' || s.charAt(l) == ')') {
                out.print("()");
            } else {
                out.print("[]");
            }
        } else if (result[l][r] == -1) {
            out.print(s.charAt(l));
            printResult(l + 1, r - 1, s, out);
            out.print(s.charAt(r));
        } else {
            printResult(l, result[l][r], s, out);
            printResult(result[l][r] + 1, r, s, out);
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
