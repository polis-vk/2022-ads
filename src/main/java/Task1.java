import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        IntStream streamForSearchMaxNum = Arrays.stream(array);
        IntStream streamForSearchMinNum = Arrays.stream(array);
        OptionalInt optionalMax = streamForSearchMaxNum.max();
        OptionalInt optionalMin = streamForSearchMinNum.min();
        int maxNumberArr = optionalMax.getAsInt();
        int minNumberArr = optionalMin.getAsInt();
        int[] countArr = new int[maxNumberArr - minNumberArr + 1];
        for (int k : array) {
            countArr[k - minNumberArr]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++) {
                array[arrayIndex] = i + minNumberArr;
                arrayIndex++;
            }
        }
        for (int element : array) {
            out.print(element + " ");
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
