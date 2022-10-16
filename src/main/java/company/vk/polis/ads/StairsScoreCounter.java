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
public final class StairsScoreCounter {
    private StairsScoreCounter() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int quantity = in.nextInt();
        quantity += 2;                      //Увеличиваем место для 2-х ноликов(в конце и в начале)
        int[] stairs = new int[quantity];
        stairs[0] = 0;
        stairs[quantity - 1] = 0;
        for (int i = 1; i < quantity - 1; i++) {
            stairs[i] = in.nextInt();
        }
        int step = in.nextInt();
        System.out.println(countMaxScore(stairs, quantity, step));
    }

    private static int countMaxScore(int[] stairs, int quantity, int step) {
        int[] scores = new int[quantity];
        scores[quantity - 1] = 0;
        scores[0] = 0;
        scores[1] = stairs[1];
        int indexMax;
        indexMax = (scores[0] > scores[1]) ? 0 : 1;

        for (int i = 2; i < quantity; i++) {
            if (i - indexMax > step) {
                indexMax++;
                int max = indexMax + 1;
                while (max < i) {
                    if (scores[max] > scores[indexMax]) {
                        indexMax = max;
                    }
                    max++;
                }
            }
            scores[i] = scores[indexMax] + stairs[i];
            if (scores[i] > scores[indexMax]) {
                indexMax = i;
            }
        }
        return scores[quantity - 1];
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
