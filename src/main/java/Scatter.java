import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Scatter {

    private static final int RANGE = 107;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];

        array[0] = in.nextInt();
        int min = array[0];
        int i = 1;
        while (i < array.length) {
            array[i] = in.nextInt();
            if (min > array[i]) {
                min = array[i];
            }
            i++;
        }

        int[] countArray = new int[RANGE + 1];
        for (int num : array) {
            countArray[num - min]++;
        }

        printSortArray(countArray, min, out);
    }

    private static void printSortArray(int[] array, int min, final PrintWriter out) {
        for (int i = 0; i < array.length; ++i) {
            int buff = array[i];
            while (buff > 0) {
                out.println(min + i);
                buff--;
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
