package company.vk.polis.ads;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class TaskE {
    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int n = in.nextInt();
        String line = in.reader.readLine();

        int[] count = new int[26];
        final int OFFSET = 65;
        for (int i = 0; i < line.length(); i++) {
            count[line.charAt(i) - OFFSET]++;
        }
        StringBuilder result = new StringBuilder();
        Deque<Integer> rest = new ArrayDeque<>();

        int center = 91; // ascii value after 'Z'
        for (int i = 0; i < count.length; i++) {
            if (i + OFFSET < center && count[i] % 2 != 0) {
                // choosing a letter for middle position
                center = i;
            }
            if (count[i] >= 2) {
                for (int k = 0; k < count[i] / 2; k++) {
                    result.append((char) (i + OFFSET));
                    rest.addFirst(i);
                }
            }
        }
        if (result.isEmpty() || !rest.isEmpty() && center != 91) {
            // either center is the only letter or it should be placed in between the others
            result.append((char) (center + OFFSET));
        }
        while (!rest.isEmpty()) {
            result.append((char) (rest.removeFirst() + OFFSET));
        }
        out.println(result);
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
