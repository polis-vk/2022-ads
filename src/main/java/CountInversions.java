import java.io.*;
import java.util.StringTokenizer;

public class CountInversions {
    private static int getCountInversions(int[] mas, int fromIndex, int toIndex) {
        if (fromIndex == toIndex - 1) {
            return 0;
        }

        int mid = toIndex - (toIndex - fromIndex) / 2;

        return getCountInversions(mas, fromIndex, mid) +
                getCountInversions(mas, mid, toIndex) +
                getSplitInversions(mas, fromIndex, mid, toIndex);
    }

    private static int getSplitInversions(int[] mas, int fromIndex, int mid, int toIndex) {
        int sizeLeftMas = mid - fromIndex;
        int sizeRightMas = toIndex - mid;
        int[] masLeft = new int[sizeLeftMas];
        int[] masRight = new int[sizeRightMas];

        int countInversions = 0;

        System.arraycopy(mas, fromIndex, masLeft, 0, sizeLeftMas);
        System.arraycopy(mas, mid, masRight, 0, sizeRightMas);

        int i = 0;
        int j = 0;
        int index = fromIndex;

        while (i < sizeLeftMas && j < sizeRightMas) {
            if (masLeft[i] < masRight[j]) {
                mas[index++] = masLeft[i++];
            } else {
                mas[index++] = masRight[j++];
                countInversions += sizeLeftMas - i;
            }
        }

        while (i < sizeLeftMas) {
            mas[index++] = masLeft[i++];
        }

        while (j < sizeRightMas) {
            mas[index++] = masRight[j++];
        }

        return countInversions;
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int sizeArrayDigits = in.nextInt();
        int[] digitArray = new int[sizeArrayDigits];

        for (int i = 0; i < sizeArrayDigits; i++) {
            digitArray[i] = in.nextInt();
        }

        System.out.println(getCountInversions(digitArray, 0, sizeArrayDigits));

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