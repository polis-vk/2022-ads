package company.vk.polis.ads.part4.artemokky;

import java.io.*;
import java.util.*;


public class Mouse {
    static void solve(FastScanner in, PrintWriter out) {
        int height = in.nextInt();
        int width = in.nextInt();

        int[][] field = new int[height + 1][width + 1];
        ArrayDeque<Character> path = new ArrayDeque<>();


        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                field[i][j] = -1;
            }
        }

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                field[height - i + 1][j] = in.nextInt();
            }
        }

        field[0][1] = 0;

        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                field[i][j] = Math.max(field[i - 1][j], field[i][j - 1]) + field[i][j];
            }
        }

        int i = height;
        int j = width;
        while (i + j > 2) {
            if (field[i - 1][j] > field[i][j - 1]) {
                path.push('F');
                i--;
            } else {
                path.push('R');
                j--;
            }
        }
        while (!path.isEmpty()) {
            out.print(path.pop());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}