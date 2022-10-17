package company.vk.polis.ads.part4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/11803834
 */
public final class InvNumb {
    private InvNumb() {
        // Should not be instantiated
    }

    public static int counter = 0;

    private static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return;
        }
        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        sort(array, fromInclusive, mid);
        sort(array, mid, toExclusive);
        merge(array, fromInclusive, mid, toExclusive);
    }

    private static void merge(int[] array, int fromInclusive, int mid, int toExclusive) {
        int halfIndex1 = fromInclusive;
        int halfIndex2 = mid;
        List<Integer> tempList = new ArrayList<>();
        while (halfIndex1 < mid || halfIndex2 < toExclusive) {
            if (halfIndex1 < mid &&
                    (halfIndex2 >= toExclusive || array[halfIndex1] < array[halfIndex2])) {
                tempList.add(array[halfIndex1]);
                halfIndex1++;
            } else {
                tempList.add(array[halfIndex2]);
                halfIndex2++;
                counter += (mid - halfIndex1);
            }
        }
        for (int i = 0; i < (toExclusive - fromInclusive); i++) {
            int currIndex = fromInclusive + i;
            array[currIndex] = tempList.get(i);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = in.nextInt();
        }

        int mid = array.length >> 1;
        sort(array, 0, array.length);
        out.println(counter);
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
