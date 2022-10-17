package company.vk.polis.ads.part4;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/11808961
 */
public final class MaxCostPath {
    private MaxCostPath() {
        // Should not be instantiated
    }

    private static String restorePath(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        int x = arr[0].length - 1;
        int y = 0;
        while (x > 0 || y < arr.length - 1) {
            if (y == arr.length - 1 || x > 0 && arr[y][x - 1] >= arr[y + 1][x]) {
                sb.append("R");
                x--;
            } else {
                sb.append("F");
                y++;
            }
        }
        return sb.reverse().toString();
    }

    private static int dynamicSolve(int y, int x, int[][] arr, int[][] subArr) {
        if (y > arr.length - 1 || x < 0) {
            return 0;
        } else if (subArr[y][x] != 0) {
            return subArr[y][x];
        } else {
            int yInc = dynamicSolve(y + 1, x, arr, subArr);
            int xDec = dynamicSolve(y, x - 1, arr, subArr);
            subArr[y][x] = arr[y][x] + Math.max(yInc, xDec);
            return subArr[y][x];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = in.nextInt();
        int columns = in.nextInt();
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        int[][] subArr = new int[rows][columns];
        dynamicSolve(0, columns - 1, arr, subArr);
        System.out.println(restorePath(subArr));
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
