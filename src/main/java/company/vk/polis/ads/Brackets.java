package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Brackets {
    private Brackets() {
        // Should not be instantiated
    }

    private static final int MAX_SEQ = 105;
    private static int[][] dp;
    private static int [][] inx;

    private static String seq;

    private static boolean isOpposite(char l, char r) {
        return (l == '(' && r == ')') || (l == '[' && r == ']');
    }

    private static int fillTable(int i, int j) {
        if (i < j && dp[i][j] == MAX_SEQ) {
            if (isOpposite(seq.charAt(i), seq.charAt(j))) {
                dp[i][j] = Math.min(dp[i][j], fillTable(i + 1, j - 1));
            }
            for (int k = i; k < j; ++k) {
                int tmp = fillTable(i, k) + fillTable(k + 1, j);
                if (tmp < dp[i][j]) {
                    dp[i][j] = tmp;
                    inx[i][j] = k;
                }
            }
        }
        return dp[i][j];
    }

    private static void printSequence(StringBuilder sb, int i, int j) {
        if (i > j) {
            return;
        }
        if (i == j) {
            if (seq.charAt(i) == '(' || seq.charAt(j) == ')') {
                sb.append("()");
            } else {
                sb.append("[]");
            }
        } else if(inx[i][j] == MAX_SEQ) {
            sb.append(seq.charAt(i));
            printSequence(sb, i + 1, j - 1);
            sb.append(seq.charAt(j));
        } else {
            printSequence(sb, i, inx[i][j]);
            printSequence(sb, inx[i][j] + 1, j);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        seq = in.next();
        if (seq.length() == 0) {
            out.println("");
            return;
        }
        dp = new int[seq.length()][seq.length()];
        for (int i = 0; i < seq.length(); ++i) {
            for (int j = 0; j < seq.length(); ++j) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (i > j) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = MAX_SEQ;
                }
            }
        }

        inx = new int[seq.length()][seq.length()];
        for (int i = 0; i < seq.length(); ++i) {
            Arrays.fill(inx[i], MAX_SEQ);
        }


        fillTable(0, seq.length() - 1);
        StringBuilder sb = new StringBuilder();
        printSequence(sb, 0 , seq.length() - 1);
        System.out.print(sb);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        return "";
                    }
                    tokenizer = new StringTokenizer(line);
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

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);

        }
    }
}
