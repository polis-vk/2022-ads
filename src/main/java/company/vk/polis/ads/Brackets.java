package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Brackets {

    // https://www.eolymp.com/ru/submissions/11797703

    private static int[][] d;
    private static int[][] bestDiv;
    private static String line;

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        line = in.reader.readLine();
        int n = line.length();
        d = new int[n][n];


        if (line.equals("")) {
            out.println("");
            return;
        }
        bestDiv = new int[n][n];

        for (int i = 0; i < n; i++) {
            d[i][i] = 1;
        }

        for (int k = 1; k < n; k++) {
            for (int i = 0; i + k < n; i++) {
                int j = i + k;
                int min = d[i][i] + d[i + 1][j];
                bestDiv[i][j] = i;

                if (isCorrect(i, j)) {
                    min = d[i + 1][j - 1];
                    bestDiv[i][j] = -1;
                }
                for (int l = i; l < j; l++) {
                    int newVal = (d[i][l] + d[l + 1][j]);
                    if (min > newVal) {
                        min = newVal;
                        bestDiv[i][j] = l;
                    }
                }
                d[i][j] = min;
            }
        }
        out.println(restoreBrackets(0, n - 1));
    }

    private static boolean isCorrect(int i, int j) {
        return line.charAt(i) == '(' && line.charAt(j) == ')'
                || line.charAt(i) == '[' && line.charAt(j) == ']';
    }

    private static String restoreBrackets(int i, int j) {
        if (i > j) {
            return "";
        } else if (i == j) {
            if (line.charAt(i) == '(' || line.charAt(i) == ')') {
                return "()";
            } else if (line.charAt(i) == '[' || line.charAt(i) == ']') {
                return "[]";
            }
        }
        if (d[i][j] == 0) {
            return line.substring(i, j + 1);
        }
        if (bestDiv[i][j] == -1) {
            return line.charAt(i) + restoreBrackets(i + 1, j - 1) + line.charAt(j);
        }
        return restoreBrackets(i, bestDiv[i][j]) + restoreBrackets(bestDiv[i][j] + 1, j);
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
        } catch (IOException ignored) {

        }
    }
}
