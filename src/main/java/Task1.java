import java.io.*;
import java.util.StringTokenizer;

public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int maxDiff = 107;
        int N = in.nextInt();
        int[] array = new int[N];
        int[] numCounts = new int[maxDiff + 1];
        int minNum = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int num = in.nextInt();
            array[i] = num;
            minNum = Math.min(minNum, num);
        }
        for (int num : array) {
            numCounts[num - minNum]++;
        }
        for (int i = 0; i < maxDiff + 1; i++) {
            for (int j = 0; j < numCounts[i]; j++) {
                out.println(minNum + i);
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
