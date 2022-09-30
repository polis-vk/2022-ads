package company.vk.polis.ads;

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
public final class TaskE {
    private void SolveTemplate() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int[] letters = new int[26];
        int mid = -1;
        int middle;
        StringBuilder result = new StringBuilder();
        String str = in.next();
        char[] input = str.toCharArray();
        for (char c : input) {
            letters[c - 'A'] += 1;
        }
        for (int i = 0; i < letters.length; i++) {
            while (letters[i] / 2 != 0) {
                middle = result.length() / 2;
                letters[i] -= 2;
                result.insert(middle, (char) (i + 'A'));
                result.insert(middle, (char) (i + 'A'));
            }
            if (mid == -1 && letters[i] % 2 == 1) {
                mid = i;
            }
        }
        if (mid != -1) {
            middle = result.length() / 2;
            result.insert(middle, (char) (mid + 'A'));
        }
        System.out.println(result);
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
