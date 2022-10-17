import java.io.*;
import java.util.StringTokenizer;

public class Ladder {
    public static int getMaxSumOfRange(int[] mas, int fromIndex, int toIndex) {
        int maxValue = Integer.MIN_VALUE;

        if (fromIndex < 0) {
            fromIndex = 0;
        }

        for (int i = fromIndex; i <= toIndex; i++) {
            if (mas[i] > maxValue) {
                maxValue = mas[i];
            }
        }

        return maxValue;
    }

    private static int ladderCost(int[] mas, int step) {
        int sizeResultMas = mas.length;
        int[] resultMas = new int[sizeResultMas];

        resultMas[1] = mas[1];

        for (int i = 2; i < sizeResultMas; i++) {
            resultMas[i] = getMaxSumOfRange(resultMas, i - step, i - 1) + mas[i];
        }

        return resultMas[sizeResultMas - 1];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeMasDigits = in.nextInt() + 2;
        int[] masDigits = new int[sizeMasDigits];

        for (int i = 1; i < sizeMasDigits - 1; i++) {
            masDigits[i] = in.nextInt();
        }

        int step = in.nextInt();

        out.println(ladderCost(masDigits, step));
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