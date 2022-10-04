import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public final class TaskA {
    private TaskA() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeCntMas = 215; // 107 * 2 + 1;
        int[] cntMas = new int[sizeCntMas]; // массив для сортировки

        int cntNumbers = in.nextInt(); // для цикла
        int currentDigit = in.nextInt(); // считываем опорный элемент
        int valueBringBackNumber = currentDigit - sizeCntMas / 2;
        cntMas[currentDigit - valueBringBackNumber]++; // впихиваем первый элемент

        for (int i = 1; i < cntNumbers; i++) {
            currentDigit = in.nextInt();
            cntMas[Math.abs(currentDigit - valueBringBackNumber)]++;
        }

        for (int i = 0; i < sizeCntMas; i++) {
            for (int j = 0; j < cntMas[i]; j++) {
                out.print(i + valueBringBackNumber + " ");
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
