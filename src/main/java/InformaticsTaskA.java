import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskA {
    private InformaticsTaskA() {
        // Should not be instantiated
    }

    private static final int CAPACITY = 107;

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int count = in.nextInt();
        int minNumber = Integer.MAX_VALUE;
        ArrayList<Integer> inputList = new ArrayList<>();
        ArrayList<Integer> helpList = new ArrayList<>(CAPACITY);
        for (int i = 0; i < count; i++) { // T(N) = 0(N)
            int currNumber = in.nextInt();
            if (currNumber < minNumber) {
                minNumber = currNumber;
            }
            inputList.add(currNumber);
        }
        for (int i = 0; i < CAPACITY; i++) {
            helpList.add(i, 0);
        }
        for (int element : inputList) { // T(N) = 0(N)
            int index = element - minNumber;
            int newValue = helpList.get(index) + 1;
            helpList.set(index, newValue);
        }
        for (int i = 0; i < CAPACITY; i++) {              //--\
            for (int j = 0; j < helpList.get(i); j++) {   //---| T(N) = 0(N)
                out.println(i + minNumber);               //--/
            }                                             //-/
        }                                                 ///
    }
    // T(N) = 0(N) + 0(N) + 0(N) = 0(N)

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}