import java.io.*;
import java.util.StringTokenizer;

public class Scatter {
    private Scatter() {
        // Should not be instantiated
    }

    private static void solve(final Scatter.FastScanner in, final PrintWriter out) {
        int N = in.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        int minNum = arr[0];
        int maxNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            minNum = Math.min(minNum, arr[i]);
            maxNum = Math.max(maxNum, arr[i]);
        }

        int[] countArr = new int[maxNum - minNum + 1];
        for (int value : arr) {
            countArr[value - minNum]++;
        }

        for (int i = 0, j = 0; i < countArr.length; i++) {
            for (int k = 0; k < countArr[i]; k++) {
                arr[j++] = i + minNum;
            }
        }

        for (int num : arr) {
            out.print(num + " ");
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
        final Scatter.FastScanner in = new Scatter.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
