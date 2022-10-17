import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Stairs {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] stairs = new int[in.nextInt() + 1];
        for (int i = 1; i < stairs.length; i++) {
            stairs[i] = in.nextInt();
        }

        int step = in.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(stairs[0]);

        for (int i = 1; i < stairs.length; i++) {
            Integer max = list.stream().max(Comparator.naturalOrder()).get();
            if (list.size() >= step) {
                list.remove(0);
            }
            list.add(max + stairs[i]);
        }

        out.println(list.stream().max(Comparator.naturalOrder()).get());
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
