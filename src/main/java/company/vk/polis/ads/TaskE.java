package company.vk.polis.ads.homework;

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
    private TaskE() {
        // Should not be instantiated
    }

    private static char min(String s) {
        char min = s.charAt(0);
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c < min) {
                min = c;
            }
        }
        return min;
    }

    private static char max(String s) {
        char max = s.charAt(0);
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c > max) {
                max = c;
            }
        }
        return max;
    }

    private static int[] count(String s, int min, int max) {
        int[] counter = new int[max - min + 1];
        char value;

        for (int i = 0; i < s.length(); i++) {
            value = s.charAt(i);
            counter[value - min] += 1;
        }

        return counter;
    }

    private static char[] make(int[] counter, int startInclusive) {
        int size = 0;
        char oddChar = '0';

        for (int k = 0; k < counter.length; k++) {
            if ((counter[k] & 1) == 0) {
                size += counter[k];
            } else {
                if (oddChar == '0') {
                    size += counter[k];
                    oddChar = (char) (startInclusive + k);
                } else {
                    size += counter[k] - 1;
                }
                counter[k] -= 1;
            }
        }

        char[] p = new char[size];
        int i = 0, j = p.length - 1;

        for (int k = 0; k < counter.length; k++) {
            int c = counter[k];
            char b = (char) (startInclusive + k);

            if (c > 1) {
                while (c > 0) {
                    p[i] = p[j] = b;
                    i++;
                    j--;
                    c -= 2;
                }
            }
        }

        if ((p.length & 1) != 0) {
            p[i] = oddChar;
        }

        return p;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        in.nextInt();
        String s = in.next();

        int startInclusive = min(s);
        int endInclusive = max(s);

        int[] counter = count(s, startInclusive, endInclusive);

        char[] p = make(counter, startInclusive);

        out.println(new String(p));
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
