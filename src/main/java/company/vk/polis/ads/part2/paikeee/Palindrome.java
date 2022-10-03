package company.vk.polis.ads.part2.paikeee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Palindrome {

    private Palindrome() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int n = in.nextInt();
        String string = in.reader.readLine();
        TreeMap<Character, Integer> letters = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            letters.put(string.charAt(i), letters.getOrDefault(string.charAt(i), 0) + 1);
        }
        StringBuilder answer = new StringBuilder();
        Character minChar = null;
        for (Character key : letters.keySet()) {
            Integer value = letters.get(key);
            if (minChar == null && value % 2 == 1) {
                minChar = key;
                value--;
                letters.put(key, value);
            }
            if (value / 2 > 0) {
                if (value % 2 == 1) {
                    value--;
                }
                value /= 2;
                answer.append(String.valueOf(key).repeat(value));
                letters.put(key, value);
            } else {
                letters.put(key, 0);
            }
        }
        if (minChar != null) {
            answer.append(minChar);
        }
        for (Character key : letters.descendingKeySet()) {
            answer.append(String.valueOf(key).repeat(letters.get(key)));
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
