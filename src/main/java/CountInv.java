import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountInv {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] array = new int[in.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }

        out.println(countInv(array, 0, array.length));
    }

    private static int countInv(int[] a, int i, int j) {
        if (j - i <= 1) {
            return 0;
        }

        int mid = i + ((j - i) >> 1);
        return countInv(a, i, mid) + countInv(a, mid, j) + countSplitInv(a, i, j, mid);
    }

    private static int countSplitInv(int[] a, int fromInclusive, int toExclusive, int mid) {
        int[] sortMembers = new int[a.length];
        int result = 0;
        int i = fromInclusive;
        int j = mid;
        int index = 0;
        while (i != mid && j != toExclusive) {
            if (a[i] < a[j]) {
                sortMembers[index] = a[i];
                index++;
                i++;
            } else {
                sortMembers[index] = a[j];
                index++;
                j++;
                result += mid - i;
            }
        }

        while (j != toExclusive) {
            sortMembers[index] = a[j];
            index++;
            j++;
        }
        while (i != mid) {
            sortMembers[index] = a[i];
            index++;
            i++;
        }

        for (i = 0; i < index; i++) {
            a[i + fromInclusive] = sortMembers[i];
        }

        return result;
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
