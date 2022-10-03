package company.vk.polis.ads.part2.tedbear;

import java.io.*;
import java.util.StringTokenizer;

public class MergeOfSequences {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int x = in.nextInt();
        int quadIndex = 1;
        int cubicIndex = 1;
        long  result = 0;

        for(int i = 1; i <= x; i++) {
            if (Math.pow(quadIndex, 2) > Math.pow(cubicIndex, 3)) {
                result = (long) Math.pow(cubicIndex, 3);
                cubicIndex++;
            }
            else if (Math.pow(quadIndex, 2) < Math.pow(cubicIndex, 3)) {
                result = (long) Math.pow(quadIndex, 2);
                quadIndex++;
            }
            else {
                result = (long) Math.pow(quadIndex, 2);
                cubicIndex++;
                quadIndex++;
            }
        }

        out.write(result + "");
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
